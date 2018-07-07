package com.cheng;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名可以随意，字段需要保持一致
 *
 * @author cheng
 *         2018/7/7 16:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private int age;
}
