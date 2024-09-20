package cn.cai.web.comment.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {
    //全局异常处理
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception e){
        log.error("请求地址：{}，发生异常：{}",request.getRequestURL(),e.getMessage());
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
