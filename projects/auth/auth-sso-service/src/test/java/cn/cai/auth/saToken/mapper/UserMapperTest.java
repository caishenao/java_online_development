package cn.cai.auth.saToken.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void test() {
        String jsonString = JSONObject.toJSONString(userMapper.selectList(new LambdaQueryWrapper<>()));
        System.out.println(jsonString);
    }
}
