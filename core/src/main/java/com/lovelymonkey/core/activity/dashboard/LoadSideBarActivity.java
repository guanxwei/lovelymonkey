package com.lovelymonkey.core.activity.dashboard;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.stream.core.component.Activity;
import org.stream.core.component.ActivityResult;
import org.stream.core.execution.WorkFlowContext;

import com.lovelymonkey.core.controller.DashBoardController;
import com.lovelymonkey.core.model.User;
import com.lovelymonkey.core.widget.Widget;
import com.lovelymonkey.core.widget.Widget.WidgetLevel;
import com.lovelymonkey.core.widget.WidgetRegister;

/**
 * Internal activity executed by stream workflow {@link Engine} instances.
 * Mainly used to set up customer specific side bar contents.
 * @author guanxwei
 *
 */
public class LoadSideBarActivity extends Activity {

    @Autowired
    private WidgetRegister widgetRegister;

    @Override
    public ActivityResult act() {

        ModelMap map = (ModelMap) WorkFlowContext.getPrimary().getValue();
        User user = (User) map.get(DashBoardController.CURRENT_USER);
        Map<String, String> widgets = widgetRegister.getAll().stream()
                .filter(widget -> widget.getWidgetAuthority() <= user.getLevel() && widget.getWidgetLevel() == WidgetLevel.MENU)
                .collect(Collectors.toMap(Widget::getSymbol, Widget::getName));

        map.put("menus", widgets);
        return ActivityResult.SUCCESS;
    }

}
