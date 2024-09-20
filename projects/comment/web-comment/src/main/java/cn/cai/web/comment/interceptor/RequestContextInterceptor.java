package cn.cai.web.comment.interceptor;

import cn.cai.web.comment.pojo.RequestContext;
import cn.cai.web.comment.utils.AuthenticationUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.casbin.casdoor.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 请求上下文拦截器
 */

public class RequestContextInterceptor implements HandlerInterceptor {

    /**
     * 请求前拦截器
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 1. 获取当前登录用户
        User currentUser = AuthenticationUtils.getCurrentUser();
        // 2. 将当前登录用户存储到ThreadLocal中
        RequestContext.setUser(currentUser);
        return true;
    }

    /**
     * 请求后拦截器
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param ex any exception thrown on handler execution, if any; this does not
     * include exceptions that have been handled through an exception resolver
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 1. 清除ThreadLocal中的用户信息
        RequestContext.clear();
    }
}
