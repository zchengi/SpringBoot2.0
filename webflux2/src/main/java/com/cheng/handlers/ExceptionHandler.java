package com.cheng.handlers;

import com.cheng.exceptions.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author cheng
 *         2018/7/7 16:05
 */
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable ex) {

        ServerHttpResponse response = serverWebExchange.getResponse();
        // 设置响应头400
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        // 设置返回类型
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);

        // 异常信息
        String errorMsg = toStr(ex);

        DataBuffer db = response.bufferFactory().wrap(errorMsg.getBytes());

        return response.writeWith(Mono.just(db));
    }

    private String toStr(Throwable ex) {

        // 已知异常
        if (ex instanceof CheckException) {
            CheckException e = (CheckException) ex;
            return e.getFieldName() + ": invalid value " + e.getFieldValue();
        } else {
            // 未知异常，需要打印堆栈，方便定位
            ex.printStackTrace();
            return ex.toString();
        }
    }
}
