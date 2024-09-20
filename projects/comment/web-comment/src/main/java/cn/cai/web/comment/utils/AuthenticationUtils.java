package cn.cai.web.comment.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.casbin.casdoor.entity.User;
import org.casbin.casdoor.exception.AuthException;
import org.casbin.casdoor.service.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 权限相关工具
 */
@Slf4j
public class AuthenticationUtils {
    protected static final String BASIC_AUTH_HEADER_PREFIX = "Basic ";

    protected static final String BASIC_AUTH_HEADER = HttpHeaders.AUTHORIZATION;

    /**
     * 获取当前登录用户
     * @return 当前登录用户
     */
    public static User getCurrentUser() {
        AuthService authService = BeanUtils.getBean(AuthService.class);
        return authService.parseJwtToken(getToken());
    }

    /**
     * 获取token
     * @return 请求对象中的token
     */
    public static String getToken() throws AuthException {
        // 1. 静态方法获取请求
        String authorizationHeader = getRequest().getHeader(BASIC_AUTH_HEADER);
        if (StringUtils.isBlank(authorizationHeader)) {
            log.error("Authorization header is empty");
            throw new AuthException("Authorization header is empty");
        }
        // 去除Basic
        return authorizationHeader.substring(BASIC_AUTH_HEADER_PREFIX.length());
    }

    /**
     * 获取当前请求
     * @return 当前请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }

    /**
     * 获取请求头中的内容
     * @param headerName 要获取的请求头名称
     * @return 请求头中的内容
     */
    public static String getHeader(String headerName) {
        return getRequest().getHeader(headerName);
    }
}
