package cn.cai.workflow.filter.rest;


import cn.cai.web.comment.utils.AuthenticationUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.util.EngineUtil;
import org.casbin.casdoor.entity.Role;
import org.casbin.casdoor.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = {"/engine-rest/*", "/customize/*"}, asyncSupported = true)
@Slf4j
public class StatelessUserAuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO 根据应用加载不同的引擎
        ProcessEngine engine = EngineUtil.lookupProcessEngine("default");
        User currentUser = null;
        try {
            currentUser = AuthenticationUtils.getCurrentUser();
        }catch (Exception e) {
            log.error("获取用户信息失败", e);
            throw new RuntimeException("获取用户信息失败!");
        }
        List<Role> roles = currentUser.roles;
        String userId = currentUser.id;
        List<String> groupNameList = roles.stream().filter(item -> item.isEnabled).map(item -> item.name).collect(Collectors.toList());
        try {
            engine.getIdentityService().setAuthentication(userId, groupNameList);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            clearAuthentication(engine);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 清除认证信息
     *
     * @param engine
     */
    private void clearAuthentication(ProcessEngine engine) {
        engine.getIdentityService().clearAuthentication();
    }


}