package com.lovelymonkey.core.widget.data;

import java.util.List;
import java.util.Map;

import com.lovelymonkey.core.model.User;

import lombok.Builder;
import lombok.Data;

/**
 * Widget input holder.
 * Customers can send data to server via HTTP request, like a {@link RequestMethod#GET} request, additional information
 * can be transfered by queryString or HTTP form data.
 *
 * To provide an unique model abstraction, once the server receive request from the font-end widget, the server
 * will wrap the HTTP request's information including the request method, queryString, parameters, etc into an {@link WidgetInput}
 * instance.
 * @author guanxwei
 *
 */
@Data
@Builder
public class WidgetInput {

    private String requestID;
    private String method;
    private String queryString;
    private Map<String, String[]> parameters;
    private Map<String, List<String>> headers;
    private String widgetName;
    private User user;
    private String remoteAddr;
}
