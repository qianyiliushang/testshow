package com.zombie.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zombie on 15/12/26.
 */
public class ParameterTool {
    public static final String REQUEST_IF_NONE_MATCH = "If-None-Match";
    public static final String RESPONSE_ETAG = "ETag";
    public static final String DEBUG_MODE = "nne_debug_mode";
    public static final String TURNON_DEBUG_MODE = "true";

    private ParameterTool() {
    }

    public static Integer getParameterInteger(HttpServletRequest request,
                                              String name, Integer defaultValue) {
        Integer i = defaultValue;
        try {
            i = Integer.parseInt(request.getParameter(name));
        } catch (Exception ignore) {
        }
        return i;
    }

    public static Boolean getParameterBoolean(HttpServletRequest request,
                                              String name, Boolean defaultValue) {
        Boolean b = defaultValue;
        try {
            String value = request.getParameter(name);
            if (StringUtils.isEmpty(value)) {
                b = Boolean.valueOf(value);
            }
        } catch (Exception ignore) {
        }
        return b;
    }

    public static Long getParameterLong(HttpServletRequest request,
                                        String name, Long defaultValue) {
        Long l = defaultValue;
        try {
            l = Long.parseLong(request.getParameter(name));
        } catch (Exception ignore) {
        }
        return l;
    }

    public static Float getParameterFloat(HttpServletRequest request,
                                          String name, Float defaultValue) {
        Float f = defaultValue;
        try {
            f = Float.parseFloat(request.getParameter(name));
        } catch (Exception ignore) {
        }
        return f;
    }

    public static String getParameterString(HttpServletRequest request,
                                            String name, String defaultValue) {
        String s = request.getParameter(name);
        if (StringUtils.isEmpty(s)) {
            s = defaultValue;
        } else {
            s = s.trim();
        }
        return s;
    }

    public static String getAttrString(HttpServletRequest request,
                                       String name, String defaultValue) {
        String s = (String) request.getAttribute(name);
        if (StringUtils.isEmpty(s)) {
            s = defaultValue;
        } else {
            s = s.trim();
        }

        return s;
    }

    public static String getRequestVersion(HttpServletRequest request) {
        return request.getHeader(REQUEST_IF_NONE_MATCH);
    }

    public static String[] getBatchRequestVersion(HttpServletRequest request, String split) {
        String str = getRequestVersion(request);
        if (!StringUtils.isEmpty(str)) {
            return str.split(split);
        }
        return new String[]{};
    }

    public static String getUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

    public static String getContentPath(HttpServletRequest request) {
        return request.getContextPath();
    }

}
