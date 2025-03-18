package cn.cai.auth.casdoor.controller;

import cn.cai.auth.casdoor.pojo.dto.LoginDTO;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.casbin.casdoor.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginConfigTest {

    @Resource
    private Config config;

    LoginDTO loginDTO = LoginDTO.BuildPassword("admin", "123456", config);

    @Test
    public void test() {
        String jsonString = JSONObject.toJSONString(loginDTO);
        HttpResponse execute = HttpRequest.post(config.getEndpoint() + "/api/login").body(jsonString).execute();
        String body = execute.body();
    }
}
