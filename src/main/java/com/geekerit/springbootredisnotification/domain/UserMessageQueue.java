package com.geekerit.springbootredisnotification.domain;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @author geekerhub
 * @date 13/5/2020 上午11:15
 */
@Component
public class UserMessageQueue {


    private BlockingQueue<User> userBlockingQueue = new DelayQueue<>();


    public boolean add(User user) {
        return userBlockingQueue.add(user);
    }

    public User pool() {
        return userBlockingQueue.poll();
    }

    public boolean remove(User user) {
        return userBlockingQueue.remove(user);
    }
}
