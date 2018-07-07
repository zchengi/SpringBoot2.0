package com.cheng.resthandlers;

import com.cheng.beans.MethodInfo;
import com.cheng.beans.ServerInfo;
import com.cheng.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

/**
 * @author cheng
 *         2018/7/7 18:00
 */
public class WebClientRestHandler implements RestHandler {

    private WebClient webClient;
    private RequestBodySpec requestBodySpec;

    /**
     * 初始化webclient
     *
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {


        // 返回结果
        Object result = null;
        RequestBodySpec request = this.webClient
                // 请求方法
                .method(methodInfo.getHttpMethod())
                // 请求url和参数
                .uri(methodInfo.getUrl(), methodInfo.getParams())
                //
                .accept(MediaType.APPLICATION_JSON);

        ResponseSpec retrieve = null;

        // 判断是否带了body 发送请求
        if (methodInfo.getBody() != null) {
            retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        } else {
            retrieve = request.retrieve();
        }

        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,
                respnse -> Mono.just(new RuntimeException("Not Found.")));

        // 处理body
        if (methodInfo.isReturnFlux()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }
        return result;
    }
}
