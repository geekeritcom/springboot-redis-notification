package com.geekerit.springbootredisnotification.controller;

import com.geekerit.springbootredisnotification.domain.User;
import com.geekerit.springbootredisnotification.domain.UserMessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Aaryn
 * @version 1.0
 * @date 2019/02/26
 */
@RestController
public class RedisController {


    private StringRedisTemplate stringRedisTemplate;

    private static final String PREFIX = "expire";

    @Resource
    private UserMessageQueue userMessageQueue;

    @Autowired
    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 订阅服务
     *
     * @param user 用户信息
     * @return 成功标识
     */
    @RequestMapping(path = "/service", method = RequestMethod.POST)
    public String insert(@RequestBody User user) {
        // 非空判断
        if (null == user) {
            return "fail";
        }
        // 定义过期键格式为 统一前缀+用户ID
        StringBuilder key = new StringBuilder();
        key.append(PREFIX).append(":").append(user.getUserId());
        String redisKey = key.toString();
        long expireTime = 10000;
        // 存储过期键
        stringRedisTemplate.opsForValue().set(redisKey, "");
        // 设置过期时间
        stringRedisTemplate.expire(redisKey, expireTime, TimeUnit.MILLISECONDS);
        // 操作标识
        return "success";
    }

    /**
     * 订阅服务
     *
     * @param user 用户信息
     * @return 成功标识
     */
    @RequestMapping(path = "/service/queue", method = RequestMethod.POST)
    public boolean insertByQueue(@RequestBody User user) {
        // 非空判断
        if (null == user) {
            return false;
        }
        return userMessageQueue.add(user);
    }

}
