package cn.cai.web.comment.aop;

import cn.cai.web.comment.constant.AopConstant;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 记录日志的切面
 */
@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class LoggerAop {


    @Pointcut(AopConstant.LOG_EXCEPTION)
    public void logControllerMethods() {
    }

    @Around("logControllerMethods()")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) {
        // 1. 获取方法签名
        String methodName = joinPoint.getSignature().getName();
        // 2. 获取方法参数
        Object[] args = joinPoint.getArgs();
        // 3. 执行前日志记录
        log.info("请求方法: {}, 参数: {}", methodName, JSONObject.toJSONString(args));
        // 4. 执行方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("请求方法: {}, 异常: {}", methodName, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        // 5. 执行后日志记录
        log.info("请求方法: {}, 返回值: {}", methodName, JSONObject.toJSONString(result));
        return result;
    }
}