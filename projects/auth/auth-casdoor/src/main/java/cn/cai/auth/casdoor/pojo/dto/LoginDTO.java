package cn.cai.auth.casdoor.pojo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.casbin.casdoor.config.Config;

/**
 * 登录DTO
 * @author 蔡
 */
@Data
@Schema(description = "登录DTO")
public class LoginDTO {

    private String application;

    private String organization;

    private String username;

    private boolean autoSignin;

    private String password;

    private String signinMethod;

    private String type;


    /**
     * 构建密码登录
     * @param username
     * @param password
     * @param config
     * @return
     */
    public static LoginDTO BuildPassword(String username, String password, Config config) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        loginDTO.setOrganization(config.getOrganizationName());
        loginDTO.setType("login");
        loginDTO.setApplication(config.getApplicationName());
        loginDTO.setSigninMethod("Password");
        return loginDTO;
    }
}
