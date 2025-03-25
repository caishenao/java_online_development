package cn.cai.auth.saToken.config;

import cn.cai.auth.saToken.service.impl.AuthServiceImpl;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sso.config.SaSsoServerConfig;
import com.dtflys.forest.Forest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SsoConfig {

    private final AuthServiceImpl authService;
    /**
     * 配置SSO相关参数
     */
    @Autowired
    private void configSso(SaSsoServerConfig ssoServer) {
        // 配置：未登录时返回的View
        ssoServer.notLoginView = () -> {
            // 重定向到登录页面
            String redirect = SaHolder.getRequest().getParam("redirect");
            SaHolder.getResponse().redirect("http://localhost:5666/auth/login?redirect=" + redirect);
            return null;
        };


        // 配置：登录处理函数
//        ssoServer.doLoginHandle = (name, pwd) -> {
//            LoginResp loginResp = authService.loginByUsername(name, pwd);
//            if (loginResp != null) {
//                StpUtil.login(loginResp.getAccessToken());
//                return SaResult.ok("登录成功！");
//            }
//            // 此处仅做模拟登录，真实环境应该查询数据进行登录
//            return SaResult.error("登录失败！");
//        };

        // 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉）
        ssoServer.sendHttp = url -> {
            try {
                System.out.println("------ 发起请求：" + url);
                String resStr = Forest.get(url).executeAsString();
                System.out.println("------ 请求结果：" + resStr);
                return resStr;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };
    }
}
