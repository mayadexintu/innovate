package com.wk.mq.rocketmq.consumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RocketMQMessageListener(topic = "DELAY_TOPIC", consumerGroup = "DELAY_CONSUMER_GROUP", selectorExpression = "DELAY",
        messageModel = MessageModel.CLUSTERING)
public class DelayConsumer implements RocketMQListener<MessageExt> {


    @SneakyThrows
    @Override
    public void onMessage(MessageExt message) {
        long storeTime = message.getStoreTimestamp();
        long currentTime = System.currentTimeMillis();
        long costTime = currentTime - storeTime;
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received delay message: {},consume time delay：{}s", body, costTime/1000/1000);
    }
}
