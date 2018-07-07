package com.cheng.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 方法调用信息类
 *
 * @author cheng
 *         2018/7/7 17:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {

    /**
     * 请求 url
     */
    private String url;

    /**
     * 请求方法
     */
    private HttpMethod httpMethod;

    /**
     * 请求参数(url)
     */
    private Map<String, Object> params;

    /**
     * 请求body
     */
    private Mono body;
    /**
     * 请求body
     */
    private Class<?> bodyElementType;

    /**
     * 返回是flux还是mono
     */
    private boolean returnFlux;

    /**
     * 返回对象的类型
     */
    private Class<?> returnElementType;
}


