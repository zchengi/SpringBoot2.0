package com.cheng.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * webflux 开发
 *
 * @author cheng
 *         2018/7/5 21:59
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/1")
    private String get1() {

        log.info("get1 start.");
        String result = createStr();
        log.info("get1 end.");

        return result;
    }

    @GetMapping("/2")
    private Mono<String> get2() {

        log.info("get1 start.");
        Mono<String> result = Mono.fromSupplier(this::createStr);
        log.info("get1 end.");

        return result;
    }

    /**
     * Flux：返回0-n个元素
     * 返回类型 produces = MediaType.TEXT_EVENT_STREAM_VALUE
     * @return
     */
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> flux() {

        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data --" + i;
        }));

        return result;
    }

    private String createStr() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ("some string");
    }
}
