package cn.cai.workflow.service;

import cn.cai.workflow.pojo.req.LoginReq;
import com.fasterxml.jackson.core.type.TypeReference;
import org.casbin.casdoor.config.Config;
import org.casbin.casdoor.service.UserService;
import org.casbin.casdoor.util.http.CasdoorResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CasdoorUserService extends UserService {

    public CasdoorUserService(Config config) {
        super(config);
    }

    /**
     * 登录获取授权码
     * @param loginReq 登录请求
     * @return 授权码
     */
    public String login(LoginReq loginReq) {
        // 转换为casdoor的参数
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("clientId",config.clientId);
        paramsMap.put("responseType", "code");
        paramsMap.put("redirectUri", "http://localhost:8080/test/callback");
        paramsMap.put("scope", "read");
        paramsMap.put("state", "camunda");
        paramsMap.put("code_challenge_method", "");
        paramsMap.put("code_challenge", "");

        Map<String,String> bodyMap = new HashMap<>();
        bodyMap.put("application",config.applicationName);
        bodyMap.put("autoSignin", "true");
        bodyMap.put("organization",config.organizationName);
        bodyMap.put("password",loginReq.getPassword());
        bodyMap.put("signinMethod", "Password");
        bodyMap.put("type", "code");
        bodyMap.put("username",loginReq.getUsername());

        CasdoorResponse<String, String> response = null;
        try {
            response = doPost("login", paramsMap, bodyMap, new TypeReference<CasdoorResponse<String, String>>() {
            });
            // 获取授权码
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.getData();
    }

}
