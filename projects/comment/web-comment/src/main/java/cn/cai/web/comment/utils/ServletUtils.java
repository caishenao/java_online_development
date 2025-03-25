package cn.cai.web.comment.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.ResponseContent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Servlet 工具类
 * @author 蔡
 */
public class ServletUtils {


    /**
     * 获取token
     * @param header 请求头名称
     * @param prefix 前缀
     * @return token
     */
    public static String getToken(String header, String prefix) {
        // 1. 获取对应的请求头
        String headerValue = getHeader(header);
        if (StringUtils.isBlank(headerValue)) {
            return null;
        }

        // 2. 判断请求头是否为空
        headerValue = headerValue.replace(prefix, "");
        return headerValue;
    }

    /**
     * 获取请求头
     * @param name 请求头名称
     * @return 请求头值
     */
    public static String getHeader(String name){
        // 1. 获取请求体
        HttpServletRequest request = ServletUtils.getRequest();
        if (request == null) {
            return null;
        }
        // 2. 获取请求头
        return request.getHeader(name);
    }

    /**
     * 获取请求体
     *
     * @return 请求头
     */
    public static HttpServletRequest getRequest(){
        // 1. 获取请求体
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        }
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse(){
        // 1. 获取请求响应


        return null;
    }


}
