package com.wk.mq.rocketmq.consumer;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//rocketmq默认不支持过滤消息，需要修改服务配置：enablePropertyFilter=true
//@RocketMQMessageListener(topic = "FILTER_TOPIC", consumerGroup = "FILTER_CONSUMER_GROUP", selectorType = SelectorType.SQL92,
//        selectorExpression = "age=3", messageModel = MessageModel.CLUSTERING)
@RocketMQMessageListener(topic = "FILTER_TOPIC", consumerGroup = "FILTER_CONSUMER_GROUP", messageModel = MessageModel.CLUSTERING)
public class FilterConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("received filter message: {}", message);
    }
}
