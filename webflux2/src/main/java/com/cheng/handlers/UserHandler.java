package com.cheng.handlers;

import com.cheng.domain.User;
import com.cheng.repository.UserRepository;
import com.cheng.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author cheng
 *         2018/7/7 15:34
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 得到所有数据
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.findAll(), User.class);
    }

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> createUser(ServerRequest request) {

        // 2.0.0 可以工作，2.0.1 这个模式报异常
        // User user = request.bodyToMono(User.class).block();

        Mono<User> user = request.bodyToMono(User.class);
        return user.flatMap(u -> {
            // 校验代码需要放在这里
            CheckUtil.checkName(u.getName());
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.userRepository.save(u), User.class);
        });
    }

    /**
     * 根据id删除用户
     *
     * @param request
     * @return
     */
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {

        String id = request.pathVariable("id");
        return userRepository.findById(id).flatMap(user ->
                userRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
