package com.wk.mq.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "ORDERLY_TOPIC", consumerGroup = "ORDERLY_CONSUMER_GROUP", selectorExpression = "ORDER",
        consumeMode = ConsumeMode.ORDERLY)
public class OrderlyConsumer implements RocketMQListener<Object> {
    @Override
    public void onMessage(Object obj) {
        log.info("received orderly message: {}", obj);
    }
}
