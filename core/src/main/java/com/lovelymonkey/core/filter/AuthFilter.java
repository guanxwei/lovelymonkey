package com.lovelymonkey.core.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lovelymonkey.core.utils.ControllerConstant;
import com.lovelymonkey.core.utils.FilterUtils;

import lombok.extern.log4j.Log4j;

/**
 * Filter to interfere the requests that are not authorized to visit some resources.
 * This filter is optional to the system, if this system is intended to integrate
 * with other Auto-Authority management system like CAS, then they don't need it.
 * If the system is planed to be deployed as single point fully managed by the system
 * itself, then the system administrator may need to plug this filter into
 * the system to help manage the resource permission stuffs.
 *
 * For example<p> if the system administrator want to specify some pages that should be
 * visited under granted permission, They can simply configure this filter in the project's web.xml,
 * and add all the confidential page paths in a stand-alone configuration file. This filter will
 * automatically pick up the configuration information and monitor all the incoming requests
 * to make sure that all the resources(mostly confidential pages) are visited under
 * surveillance. The configuration file should be put in the folder "src/main/resources/**" so that
 * the filter can load the file properly.
 *
 * The way to configure the guarded pages is to add a filter in web.xml with initiate parameter "propertyLocation",
 * whose value is the path of the java property file. In the java property file customers can add pages
 * that need to be authed before visiting.
 * Each line in the property file contains a key, value pair string like:
 * pageA=Auth.
 * Except the auth configuration, customers should also add one line at the top of property file, which
 * specify the login page that customers will be redirected to.
 */
@Log4j
public class AuthFilter implements Filter {

    private HashSet<String> protectedResources;

    private static final String FILE_LOCATION = "propertyLocation";
    private static final String LOGIN_PORTAL = "loginPortal";
    private static final String AUTH = "Auth";
    private static final String VISIT_SOURCE = "return";

    private String portal;

    /**
     * Load authority page configuration information.
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        protectedResources = new HashSet<>();
        String configFileLocation = filterConfig.getInitParameter(FILE_LOCATION);
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(configFileLocation));
            properties.keySet().forEach(key -> {
                if (key.equals(LOGIN_PORTAL)) {
                    this.portal = (String) properties.get(key);
                }
                if (properties.get(key).equals(AUTH)) {
                    protectedResources.add((String) key);
                }

            });
        } catch (IOException e) {
            log.info("Fail to load the congiguration for protected pages!");
        }
        log.info("AutoFilter is pluged into the system.");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        if (protectedResources.contains(uri)) {
            HttpSession session = httpRequest.getSession(false);
            if (session.getAttribute(ControllerConstant.LoginAndRegisterControlerConstants.CURRENT_USER) == null) {
                log.info("Customer has not logined yet, will direct him to login portal page!");
                String contructedDirectedURL = httpRequest.getRequestURL().toString() + httpRequest.getQueryString();
                httpResponse.sendRedirect(FilterUtils.constructRedirectURL(portal, VISIT_SOURCE, contructedDirectedURL));
            }
        } else {
            chain.doFilter(httpRequest, response);
        }
    }

    @Override
    public void destroy() {
        protectedResources.clear();
    }

}
