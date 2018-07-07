package com.cheng.controller;

import com.cheng.domain.User;
import com.cheng.repository.UserRepository;
import com.cheng.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author cheng
 *         2018/7/6 11:48
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // 官方推荐使用构造函数注入
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 以数组形式一次性返回数据
     *
     * @return
     */
    @GetMapping("/")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * 以文本事件流形式多次返回数据
     *
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return userRepository.findAll();
    }

    /**
     * 新增数据
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {

        user.setId(null);
        CheckUtil.checkName(user.getName());

        // spring data jpa 里面，新增和修改都是save
        // 有id为修改，id为空为新增
        // 根据实际情况是否置空id
        return this.userRepository.save(user);
    }

    /**
     * 根据id删除用户
     * 存在的时候返回 200，不存在返回 404
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {

        // deleteById() 没有返回值，不能判断数据是否存在
        // userRepository.deleteById(id);

        return userRepository.findById(id)
                // 当要操作数据，并返回一个Mono，这个时候使用flatMap
                // 如果不操作数据，只是转换数据，使用map
                .flatMap(user -> userRepository.delete(user)
                        //数据存在返回 200
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                // 数据不存在返回 404
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 修改数据
     * 存在的时候返回200和修改后的数据，不存在的时候返回404
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {

        CheckUtil.checkName(user.getName());

        // flatMap操作数据
        return userRepository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                // map：转换数据
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id查找用户，
     * 存在放回用户信息，不存在返回404
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id) {

        return userRepository.findById(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
        return userRepository.findByAgeBetween(start, end);
    }

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
        return userRepository.findByAgeBetween(start, end);
    }

    /**
     * 得到20-30岁的用户
     *
     * @return
     */
    @GetMapping("/old")
    public Flux<User> oldUser() {
        return userRepository.oldUser();
    }

    /**
     * 得到20-30岁的用户
     *
     * @return
     */
    @GetMapping(value = "/stream/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser() {
        return userRepository.oldUser();
    }
}
