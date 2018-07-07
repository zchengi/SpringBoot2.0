package com.cheng.routers;

import com.cheng.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author cheng
 *         2018/7/7 15:42
 */
@Configuration
public class AllRouters {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {
        return RouterFunctions.nest(
                // 相当于类上面的 @RequestMapping("/user")
                RequestPredicates.path("/user"),
                // 下面的相当于类里面的 @RequestMapping
                // 得到所有用户
                RouterFunctions.route(RequestPredicates.GET("/"), userHandler::getAllUser)
                        // 创建用户
                        .andRoute(RequestPredicates.POST("/")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), userHandler::createUser)
                        // 删除用户
                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::deleteUserById));
    }
}
