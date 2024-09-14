package cn.cai.web.comment.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
@EqualsAndHashCode
public class ResponseData<T> {


    /**
     * 响应编码
     */
    private Integer code;

    /**
     * 响应时间戳
     */
    private Long timestamp;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String message;


    public ResponseData(Integer code, Long timestamp, T data, String message) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
        this.message = message;
    }

    /**
     * 响应数据
     * @param data 数据
     * @return 统一响应体
     * @param <T> 泛型
     */
    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(HttpStatus.OK.value(), System.currentTimeMillis(), data, "请求成功");
    }

    /**
     * 成功，响应信息
     * @param message 响应信息
     * @return 统一响应体
     */
    public static ResponseData<Void> successMsg(String message) {
        return new ResponseData<>(HttpStatus.OK.value(), System.currentTimeMillis(), null, message);
    }

    /**
     * 成功，响应数据
     * @return 统一响应体
     */
    public static ResponseData<Void> success() {
        return new ResponseData<>(HttpStatus.OK.value(), System.currentTimeMillis(), null, "请求成功");
    }

    /**
     * 响应失败
     * @param message 失败信息
     * @return 统一响应体
     */
    public static ResponseData<Void> error(String message) {
        return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis(), null, message);
    }

    /**
     * 响应失败
     * @param status 状态码
     * @param message 失败信息
     * @return 统一响应体
     */
    public static ResponseData<Void> error(HttpStatus status, String message) {
        return new ResponseData<>(status.value(), System.currentTimeMillis(), null, message);
    }

    /**
     * 响应失败，带有数据
     * @param status 状态码
     * @param <T> 失败数据
     * @return 统一响应体
     */
    public static <T> ResponseData<T> error(HttpStatus status, T data) {
        return new ResponseData<>(status.value(), System.currentTimeMillis(), data, "请求失败");
    }




}
