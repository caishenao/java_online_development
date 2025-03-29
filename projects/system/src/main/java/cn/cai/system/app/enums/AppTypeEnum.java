package cn.cai.system.app.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 应用枚举类型
 */
@Getter
public enum AppTypeEnum {

    WEB,
    APP,
    APPLET;

    @EnumValue
    private final String value;

    AppTypeEnum() {
        this.value = this.name();
    }
}
