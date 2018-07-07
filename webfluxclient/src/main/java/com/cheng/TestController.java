package com.cheng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng
 *         2018/7/7 16:40
 */
@RestController
public class TestController {

    // 直接注入定义的接口
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test() {

        // 测试信息提取
        // 不订阅，不会实际发出请求，但会进入代理类
        // userApi.getAllUser();
        // userApi.getUserById("111");
        // userApi.deleteUserById("222");
        // userApi.createUser(Mono.just(User.builder().name("cheng").age(22).build()));

        // 直接调用 实现调用rest接口的效果
        // userApi.getAllUser().subscribe(System.out::println);


        String id = "5b409ea77e2ba4145059f393";
        userApi.getUserById(id).subscribe(user -> System.out.println("getUserById:" + user)
                , user -> System.out.println("找不到用户：" + user));
        userApi.getUserById("zzs").subscribe(user -> System.out.println("getUserById:" + user)
                , user -> System.out.println("找不到用户：" + user));
        // userApi.deleteUserById(id).subscribe(/*user -> System.out.println("deleteUserById success")*/);

        // 创建用户
        // userApi.createUser(Mono.just(User.builder().name("zz").age(20).build())).subscribe(System.out::println);
    }
}
