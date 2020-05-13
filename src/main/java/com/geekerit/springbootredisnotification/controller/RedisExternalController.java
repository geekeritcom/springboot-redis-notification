package com.geekerit.springbootredisnotification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerhub
 * @date 9/3/2020 上午9:24
 * <p>
 * 使用扩展Redis服务存放消息
 */
@RestController
public class RedisExternalController {


    @Autowired
    @Qualifier(value = "ExternalRedisTemplate")
    private RedisTemplate externalRedisTemplate;


    @RequestMapping(value = "/external")
    public void listAllCompany2() {
        externalRedisTemplate.opsForValue().set("test", "test_external");
    }
}
