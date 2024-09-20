package cn.cai.workflow.provider;

import cn.cai.web.comment.utils.AuthenticationUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.casbin.casdoor.entity.User;

/**
 * 自定义
 */
@Slf4j
public class AuthenticationProvider extends HttpBasicAuthenticationProvider {
    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        User currentUser = null;
        try {
            currentUser = AuthenticationUtils.getCurrentUser();
        }catch (Exception e) {
            log.error("获取当前用户失败", e);
        }
        if (currentUser != null) {
            return AuthenticationResult.successful(currentUser.id);
        }
        String authorization = AuthenticationUtils.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            // 调用上级
            return super.extractAuthenticatedUser(request, engine);
        }
        return AuthenticationResult.unsuccessful("用户未登录");
    }
}
