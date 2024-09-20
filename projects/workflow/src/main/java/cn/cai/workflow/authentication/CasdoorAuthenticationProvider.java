package cn.cai.workflow.authentication;

import cn.cai.web.comment.utils.BeanUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationProvider;
import org.camunda.bpm.engine.rest.security.auth.AuthenticationResult;
import org.casbin.casdoor.service.UserService;

@Slf4j
public class CasdoorAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    protected static final String BASIC_AUTH_HEADER_PREFIX = "Basic ";

    public CasdoorAuthenticationProvider() {
        super();
        this.userService = BeanUtils.getBean(UserService.class);
    }

    /**
     * Checks the request for authentication. May not return null, but always an
     * {@link AuthenticationResult} that indicates, whether authentication was
     * successful, and, if true, always provides the authenticated user.
     * <p>
     * The result can only be successful if a valid user id was provided in the
     * request. It is not required to provide the group or tenant id, as they will
     * be resolved via the {@link IdentityService} (e.g.
     * {@link ProcessEngineAuthenticationFilter#setAuthenticatedUser}).
     *
     * @param request the request to authenticate
     * @param engine  the process engine the request addresses. May be used to
     *                authenticate against the engine's identity service.
     */
    @Override
    public AuthenticationResult extractAuthenticatedUser(HttpServletRequest request, ProcessEngine engine) {
        // 1. 获取请求的token
        String token = getBearerToken(request);
        // 2. 根据token获取
        return null;
    }

    private String getBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isBlank(authorizationHeader) || !authorizationHeader.startsWith(BASIC_AUTH_HEADER_PREFIX)){
            log.error("用户请求中没有");
            throw new RuntimeException("Authorization header is missing or invalid");
        }
        // 去除Basic
        return authorizationHeader.substring(BASIC_AUTH_HEADER_PREFIX.length());
    }

    /**
     * <p>
     * Callback to add an authentication challenge to the response to the client. Called in case of unsuccessful authentication.
     * </p>
     *
     * <p>
     * For example, a Http Basic auth implementation may set the WWW-Authenticate header to <code>Basic realm="engine name"</code>.
     * </p>
     *
     * @param response
     * @param engine   the process engine the request addressed. May be considered as an authentication realm to create a specific authentication
     *                 challenge
     */
    @Override
    public void augmentResponseByAuthenticationChallenge(HttpServletResponse response, ProcessEngine engine) {
        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, BASIC_AUTH_HEADER_PREFIX + "realm=\"" + engine.getName() + "\"");
    }
}
