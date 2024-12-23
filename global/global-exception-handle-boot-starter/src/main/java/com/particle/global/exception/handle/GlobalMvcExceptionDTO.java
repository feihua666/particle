package com.particle.global.exception.handle;

import com.particle.global.dto.basic.DTO;
import com.particle.global.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/8/9 14:35
 */
@Data
public class GlobalMvcExceptionDTO extends DTO {

    /**
     * 异常对象
     */
    private Exception exception;
    /**
     * 异常栈字符串，已经提取好的异常栈字符串
     */
    private String stacktrace;
    /**
     * 请求对象
     */
    private HttpServletRequest request;
    /**
     * 响应内容
     */
    private Response responseBody;
    /**
     * 响应http状态码
     */
    private Integer responseHttpStatus;
}
