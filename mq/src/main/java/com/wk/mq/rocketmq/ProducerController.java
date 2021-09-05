package com.wk.mq.rocketmq;

import com.wk.mq.rocketmq.producer.ProducerService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/produce")
@RestController
public class ProducerController {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private ProducerService producerService;


    @PostMapping("/syncSend")
    public Map<String, String> syncSend() {
//        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
//        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
//        rocketMQTemplate.convertAndSend("TEST_TOPIC", "Hello, World!");
//        rocketMQTemplate.send("TEST_TOPIC", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());


//
//        int num = 3;
//        for (int i = 0; i < num; i++) {
//            int nextInt = RandomUtils.nextInt();
//            SendResult result = rocketMQTemplate.syncSend("RAFT_TOPIC", "my message-" + i + ":" + nextInt);
//            System.out.println("send result is：\t" + result);
//        }

        Map<String, String> map = new HashMap<>(2);
        producerService.syncSend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/asyncSend")
    public Map<String, String> asyncSend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.asyncSend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/onewaySend")
    public Map<String, String> onewaySend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.onewaySend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/orderlySend")
    public Map<String, String> orderlySend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.orderlySend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/delaySend")
    public Map<String, String> delaySend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.delaySend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/batchSend")
    public Map<String, String> batchSend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.batchSend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/filterSend")
    public Map<String, String> filterSend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.filterSend();
        map.put("isSend", "OK");
        return map;
    }

    @PostMapping("/transactionSend")
    public Map<String, String> transactionSend() {
        Map<String, String> map = new HashMap<>(2);
        producerService.transactionSend();
        map.put("isSend", "OK");
        return map;
    }
}
