package cn.cai.auth.saToken.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 认证类型
 * @author 蔡
 */
@Getter
public enum CertificationTypeEnum {


    USERNAME("USERNAME");


    @EnumValue
    private final String value;


    CertificationTypeEnum(String value){
        this.value = value;
    }

    public static CertificationTypeEnum getEnum(String value){
        for(CertificationTypeEnum e: CertificationTypeEnum.values()){
            if(e.getValue().equals(value)){
                return e;
            }
        }
        return null;
    }
}
