package com.wk.mq.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/produce")
@RestController
public class ProducerController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @PostMapping("/message")
    public void produceMessage(){
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        rocketMQTemplate.convertAndSend("TEST_TOPIC", "Hello, World!");
//        rocketMQTemplate.send("TEST_TOPIC", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
    }
}
