package com.wk.mq.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
@RocketMQMessageListener(topic = "RAFT_TOPIC", consumerGroup = "RAFT_CONSUMER_GROUP", selectorExpression = "*",
        messageModel = MessageModel.CLUSTERING)
public class MyConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("received message: {}", s);
    }
}
