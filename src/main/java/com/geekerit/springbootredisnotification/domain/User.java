package com.geekerit.springbootredisnotification.domain;

import lombok.Data;

/**
 * @author Aaryn
 * @version 1.0
 * @date 2019/02/26
 */
@Data
public class User {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户年龄
     */
    private Integer age;

}
