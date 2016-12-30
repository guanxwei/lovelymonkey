package com.lovelymonkey.core.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stream.core.execution.Engine;
import org.stream.core.execution.GraphContext;
import org.stream.core.resource.Resource;
import org.stream.core.resource.ResourceTank;
import org.stream.core.resource.ResourceType;

import com.lovelymonkey.core.builder.UserBuilder;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.utils.constants.controller.LoginAndRegisterControlerConstants;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * DashBoard controller.
 * @author guanxwei
 *
 */
@Controller(value = "/dashboard")
@Slf4j
public class DashBoardController {

    private static final String EXECUTION_GRAPH = "LoadDashboardGraph";

    @Autowired @Setter private Engine engine;
    @Autowired @Setter private GraphContext graphContext;

    // CHECKSTYLE:OFF
    public static final String RETURN_PAGE = "return_page";
    public static final String MODEL_MAP = "model_map";
    public static final String CURRENT_USER = "current_user";
    public static final String VISITOR_NAME = "visitor::" + UUID.randomUUID().toString();
    //CHECKSTYLE:ON

    /**
     * Load user level specific contents. Will first check if the customer login or not.
     * If yes, will load the content according to the customer's user level. If not will load the default
     * contents that are granted to visitors.
     * @param map Model map used to store values so that JSP engine can use them to render the page.
     * @param session HTTP session instance.
     * @return Dashboard JSP page.
     */
    @RequestMapping(method = {RequestMethod.GET}, value = "/show.htm")
    public String showDashBoard(final ModelMap map, final HttpSession session) {

        User user = (User) session.getAttribute(LoginAndRegisterControlerConstants.CURRENT_USER);
        if (user == null) {
            log.info("Anonymous visitor visit the dashboard at time: [{}]", Calendar.getInstance().getTime().toString());
            user = UserBuilder.builder()
                    .userName(VISITOR_NAME)
                    .level(0)
                    .build();
        } else {
            log.info("Customer [{}] visit the dashboard at time [{}]", Calendar.getInstance().getTime().toString());
        }
        /* Mark the model map as the primary resource of this execution workflow */
        map.addAttribute(CURRENT_USER, user);
        Resource primaryResource = Resource.builder()
                .resourceType(ResourceType.MIXED)
                .value(map)
                .resourceReference(MODEL_MAP)
                .build();
        /* Submit the workflow to the engine and execute it */
        ResourceTank resourceTank = engine.execute(graphContext, EXECUTION_GRAPH, primaryResource, false, ResourceType.MIXED);

        String returnPage = (String) resourceTank.resolve(RETURN_PAGE).getValue();

        return returnPage;
    }
}
