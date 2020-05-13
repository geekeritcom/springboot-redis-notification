package com.geekerit.springbootredisnotification.schedule;

import com.geekerit.springbootredisnotification.domain.User;
import com.geekerit.springbootredisnotification.domain.UserMessageQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author geekerhub
 * @date 13/5/2020 上午11:13
 */
@Component
@Slf4j
public class UserMessageSchedule {


    @Resource
    private UserMessageQueue userMessageQueue;

    @Scheduled(fixedRate = 1L)
    public void consume() {
        User pool = userMessageQueue.pool();
        if (null != pool) {
            log.info("notify user {}", pool);
            userMessageQueue.remove(pool);
        }
    }

}
