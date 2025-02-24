package cn.cai.web.comment.constant;

/**
 * 切面常量
 * @author 蔡
 */
public abstract class AopConstant {


    /**
     * 日志切面
     */
    public static final String LOG_EXCEPTION = "execution(* cn.cai.*.controller.*Controller.*(..))";

}
