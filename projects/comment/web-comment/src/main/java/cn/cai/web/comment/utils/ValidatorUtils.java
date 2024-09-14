package cn.cai.web.comment.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;

/**
 * 参数校验非空认证处理工具类
 *
 * @author caishenao
 */

public class ValidatorUtils {

    private static Validator validator;


    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象-(有返回值)
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @return 如果返回空 全部校验通过
     */
    public static String returnValidateEntity(Object object, Class<?>... groups) throws RuntimeException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        StringBuilder msg = new StringBuilder();
        if (!constraintViolations.isEmpty()) {
            return msg.append(constraintViolations.iterator().next().getMessage()).append("；").toString();
        }
        return msg.toString();
    }

    /**
     * 校验对象-直接返回前端
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RuntimeException 校验不通过，则报ViedNotException异常  ViedNotException 异常自定义根据项目处理
     */
    public static void validateEntity(Object object, Class<?>... groups) throws RuntimeException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        StringBuilder msg = new StringBuilder();
        if (!constraintViolations.isEmpty()) {
            msg.append(constraintViolations.iterator().next().getMessage()).append("；");
//            log.error("{}", msg);
            throw new RuntimeException(msg.toString());
        }
    }
    /**
     * 返回是否符合数据格式的校验
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @return true: 符合数据格式；false: 不符合数据格式
     */
    public static boolean booleanValidateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        return constraintViolations.isEmpty();
    }
}
