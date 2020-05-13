package com.geekerit.springbootredisnotification.domain;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Aaryn
 * @version 1.0
 * @date 2019/02/26
 */
@Data
public class User implements Delayed {
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

    /**
     * 用户到期通知时间
     */
    private Long expireTime;

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime * 1000 + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long l = this.expireTime - System.currentTimeMillis();
        return unit.convert(l,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.expireTime < ((User)o).expireTime) {
            return -1;
        }
        if (this.expireTime > ((User)o).expireTime) {
            return 1;
        }
        return 0;
    }
}
