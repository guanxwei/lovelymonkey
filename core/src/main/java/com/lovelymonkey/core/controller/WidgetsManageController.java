package com.lovelymonkey.core.controller;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.stream.core.execution.Engine;
import org.stream.core.execution.GraphContext;
import org.stream.core.resource.Resource;
import org.stream.core.resource.ResourceTank;
import org.stream.core.resource.ResourceType;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.service.LoadUserSpaceMenuService;
import com.lovelymonkey.core.template.TemplateUtils;
import com.lovelymonkey.core.utils.constants.controller.LoginAndRegisterControlerConstants;
import com.lovelymonkey.core.widget.data.Input;

import lombok.NonNull;
import lombok.Setter;

/**
 * Controller used to manage widgets.
 *
 * Provide the unique entry to load a widget from the server.
 * @author guanxwei
 *
 */
@Controller
@RequestMapping(value = "/widget")
public class WidgetsManageController {

    private static final String EXECUTE_GRAPH_NAME = "LoadWidgetGraph";

    @Autowired @Setter
    private LoadUserSpaceMenuService loadUserSpaceMenuService;

    @Autowired @Setter
    private Engine engine;

    @Autowired
    private GraphContext graphContext;

    /**
     * Load widget by widgetName.
     * @param widgetName widgetName.
     * @param session http session instance.
     * @param request http servlet request instance for exactly this customer request.
     * @return the widget template file path, currently written by jsp.
     */
    @RequestMapping(value = "/{widgetName}")
    public String loadWidget(@RequestParam @NonNull final String widgetName, final HttpSession session, final HttpServletRequest request) {

        User user = (User) session.getAttribute(LoginAndRegisterControlerConstants.CURRENT_USER);
        Input input = Input.builder()
                .method(request.getMethod())
                .headers(extractHeaders(request))
                .queryString(request.getQueryString())
                .parameters(request.getParameterMap())
                .requestID(UUID.randomUUID().toString())
                .user(user)
                .remoteAddr(request.getRemoteAddr())
                .build();
        Resource primaryResource = Resource.builder()
                .value(input)
                .resourceType(ResourceType.MIXED)
                .resourceReference(input.getRequestID())
                .build();
        ResourceTank resources = engine.executeOnce(graphContext, EXECUTE_GRAPH_NAME, primaryResource, true, ResourceType.MIXED);

        return (String) resources.resolve(TemplateUtils.RETURN_PAGE).getValue();
    }

    @SuppressWarnings({ "rawtypes" })
    private Map<String, List<String>> extractHeaders(final HttpServletRequest request) {
        Map<String, List<String>> headers = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            headers.put(name, Collections.list(request.getHeaders(name)));
        }
        return headers;
    }
}
