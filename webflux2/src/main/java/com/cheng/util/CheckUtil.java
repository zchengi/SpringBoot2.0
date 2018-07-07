package com.cheng.util;

import com.cheng.exceptions.CheckException;

import javax.validation.constraints.NotBlank;
import java.util.stream.Stream;

/**
 * @author cheng
 *         2018/7/6 16:12
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin", "guanliyuan"};

    /**
     * 校验名字，不成功抛出校验异常
     *
     * @param value
     */
    public static void checkName(@NotBlank String value) {
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new CheckException("name", value);
        });
    }
}
