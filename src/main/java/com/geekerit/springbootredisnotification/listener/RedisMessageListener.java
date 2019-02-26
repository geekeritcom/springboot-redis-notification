package com.geekerit.springbootredisnotification.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Aaryn [wr@fordearme.com]
 * @version 1.0
 * @date 2019/02/26
 */
@Component
public class RedisMessageListener implements MessageListener {

    private static final String PREFIX = "expire";

    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 消息体
        String body = new String(message.getBody());
        // 渠道名称
        String topic = new String(pattern);
        System.out.println(body);
        System.out.println(topic);
        consume(body);
    }

    /**
     * 获取过期事件后进行处理
     * @param expireKey 过期键
     */
    private void consume(String expireKey){
        String[] split = expireKey.split(":");
        String prefix = split[0];
        String userId = split[1];
        if (PREFIX.equals(prefix)){
            System.out.println("用户ID为" + userId + "的用户订购期已到，提醒用户确认收货地址");
        }
    }
}
