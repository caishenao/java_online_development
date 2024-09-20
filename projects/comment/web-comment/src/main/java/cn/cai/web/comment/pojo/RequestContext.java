package cn.cai.web.comment.pojo;

import org.casbin.casdoor.entity.User;

/**
 * 请求上下文
 */
public class RequestContext {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    /**
     * 清除线程池变量
     */
    public static void clear() {
        currentUser.remove();
    }

    /**
     * 设置当前用户
     * @param user 当前用户
     */
    public static void setUser(User user) {
        currentUser.set(user);
    }

    /**
     * 获取当前用户
     * @return 当前用户
     */
    public static User getCurrentUser() {
        return currentUser.get();
    }
}
