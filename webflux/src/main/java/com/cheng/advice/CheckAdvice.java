package com.cheng.advice;

import com.cheng.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 异常处理切面
 *
 * @author cheng
 *         2018/7/6 15:59
 */
@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity handleBindException(WebExchangeBindException e) {
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity handleCHeckException(CheckException e) {
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    /**
     * 把校验异常转换为字符串
     *
     * @param ex
     * @return
     */
    private String toStr(WebExchangeBindException ex) {

        // 异常 -> 字符串数组 map
        // 数组 -> 字符串 reduce
        return ex.getFieldErrors().stream().
                map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

    private String toStr(CheckException e) {
        return e.getFieldName() + ": 错误的值 " + e.getFieldValue();
    }
}
