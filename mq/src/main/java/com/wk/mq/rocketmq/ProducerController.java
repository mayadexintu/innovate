package com.wk.mq.rocketmq;

import org.apache.commons.lang3.RandomUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequestMapping("/produce")
@RestController
public class ProducerController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @PostMapping("/message")
    public Map<String, String> produceMessage() {
//        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
//        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        rocketMQTemplate.convertAndSend("TEST_TOPIC", "Hello, World!");
//        rocketMQTemplate.send("TEST_TOPIC", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        Map<String, String> map = new HashMap<>(2);
        int num = 3;
        for (int i = 0; i < num; i++) {
            int nextInt = RandomUtils.nextInt();
            SendResult result = rocketMQTemplate.syncSend("TEST_TOPIC", "my message-" + i + ":" + nextInt, 1000);
            System.out.println("send result is\t" + result);
        }

        map.put("isSend", "OK");
        return map;
    }
}
