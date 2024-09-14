package cn.cai.workflow.filter.rest;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.HttpHeaders;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.digest._apacheCommonsCodec.Base64;
import org.camunda.bpm.engine.impl.identity.ReadOnlyIdentityProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.util.EngineUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = {"/engine-rest/*", "/customize/*"}, asyncSupported = true)
public class StatelessUserAuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // TODO 根据应用加载不同的引擎
        ProcessEngine engine = EngineUtil.lookupProcessEngine("default");

        // TODO 从token中获取用户的id和角色(用户组)
        // 直接从请求头中获取用户id和group
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取Authorization
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            // 放行，交由HttpBasicAuthenticationProvider处理
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String userId = request.getHeader("userId");
        String groupIds = request.getHeader("groupIds");
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(groupIds)) {
            throw new RuntimeException("userId or groupIds is null");
        }
        // 将字符串用,切割，并转换为List集合
        List<String> groupList = Arrays.stream(groupIds.split(",")).collect(Collectors.toList());

        // 创建临时用户和组
//        IdentityService identityService = engine.getIdentityService();
//        User user = identityService.newUser(userId);
//        user.setPassword("123456");
//        try {
//            identityService.saveUser(user);
//        } catch (Exception e) {
//            // 如果用户已经存在，则不创建
//        }
//        List<Group> groups = new ArrayList<>();
//        for (String s : groupList) {
//            Group group = identityService.newGroup(s);
//            group.setType("SYSTEM");
//            try {
//                identityService.saveGroup(group);
//            } catch (Exception e) {
//                // 如果组已经存在，则不创建
//            }
//            groups.add(group);
//        }
//
//        // 绑定用户和组
//        for (Group group : groups) {
//            try {
//                identityService.createMembership(userId, group.getId());
//            }catch (Exception e) {
//                // 如果用户和组已经绑定，则不创建
//            }
//        }

        try {
            engine.getIdentityService().setAuthentication(userId, groupList);
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