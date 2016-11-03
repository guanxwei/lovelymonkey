package com.lovelymonkey.core.activity.widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.stream.core.component.Activity;
import org.stream.core.component.ActivityResult;
import org.stream.core.execution.WorkFlowContext;
import org.stream.core.resource.Resource;

import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.template.TemplateUtils;
import com.lovelymonkey.core.widget.Widget;
import com.lovelymonkey.core.widget.WidgetRegister;
import com.lovelymonkey.core.widget.data.Input;

import lombok.extern.slf4j.Slf4j;

/**
 * Internal activity executed by work flow engine.
 * Mainly used to check the widget the customer is visiting.
 * @author guanxwei
 *
 */
@Slf4j
public class CheckWidgetActivity extends Activity {

    @Autowired
    private WidgetRegister widgetRegister;

    @Override
    public ActivityResult act() {
        Input input = (Input) WorkFlowContext.getPrimary().getValue();
        Resource returnPage;

        /* Check if the widget exists, if not direct customers to the default error page! */
        Widget widget = widgetRegister.getWidget(input.getWidgetName());
        if (widget == null) {
            returnPage = Resource.builder()
                    .resourceReference(TemplateUtils.RETURN_PAGE)
                    .value(TemplateUtils.DEFAULT_WIDGET_NOT_FOUND_PAGE)
                    .build();
             WorkFlowContext.attachResource(returnPage);
             return ActivityResult.SUCCESS;
        }

        /* Check the customer information if exists */
        User user = input.getUser();
        if (user != null) {
            if (user.getLevel() < widget.getWidgetAuthority()) {
                log.warn("Customer [{}] is trying to visit confidential resource [{}]", user.getId(), input.getWidgetName());
                returnPage = Resource.builder()
                        .value(TemplateUtils.DEFAULT_WIDGET_NOT_GRANTED_PAGE)
                        .resourceReference(TemplateUtils.RETURN_PAGE)
                        .build();
                WorkFlowContext.attachResource(returnPage);
                return ActivityResult.SUCCESS;
            }
        } else {
            /* Customer has not login the system yet */
            if (widget.getWidgetAuthority() != 1) {
                log.warn("Anonymous visitor is trying to visit confidential resource [{}], the visitor's ip is [{}]",
                        input.getWidgetName(), input.getRemoteAddr());
                returnPage = Resource.builder()
                        .value(TemplateUtils.DEFAULT_WIDGET_NOT_GRANTED_PAGE)
                        .resourceReference(TemplateUtils.RETURN_PAGE)
                        .build();
                WorkFlowContext.attachResource(returnPage);
                return ActivityResult.SUCCESS;
            }
        }

        // Pass all the check progress, handle over control to the next activity.
        Resource widgetResource = Resource.builder()
                .resourceReference(WidgetUtil.WIDGET_RESOURCE_REFERENCE)
                .value(widget)
                .build();
        WorkFlowContext.attachResource(widgetResource);
        return ActivityResult.SUCCESS;
    }

}
