package com.wk.mq.rocketmq.producer;

import com.wk.mq.rocketmq.order.OrderStep;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产者服务
 */
@Slf4j
@Service
public class ProducerService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送同步消息
     */
    public void syncSend() {
        for (int i = 0; i < 3; i++) {
            Message<String> message = new GenericMessage<>("sync_message:" + i);
            SendResult sendResult = rocketMQTemplate.syncSend("RAFT_TOPIC:RAFT_TAG", message);
            log.info("同步发送结果：" + sendResult.toString());
        }
    }

    /**
     * 发送异步消息
     */
    public void asyncSend() {
        for (int i = 0; i < 3; i++) {
            Message<String> message = new GenericMessage<>("async_message:" + i);
            rocketMQTemplate.asyncSend("RAFT_TOPIC:RAFT_TAG_ASYNC", message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("异步发送结果：" + sendResult.toString());
                }

                @SneakyThrows
                @Override
                public void onException(Throwable throwable) {
                    throw new Exception(throwable.getMessage());
                }
            });
        }
    }

    /**
     * 发送单向消息
     */
    public void onewaySend() {
        for (int i = 0; i < 3; i++) {
            Message<String> message = new GenericMessage<>("oneway_message:" + i);
            rocketMQTemplate.sendOneWay("RAFT_TOPIC:RAFT_TAG_ONEWAY", message);
            log.info("单项消息发送成功");
        }
    }

    /**
     * 发送顺序消息
     */
    public void orderlySend() {
        List<OrderStep> orderList = OrderStep.buildOrderList();
        for (int i = 0; i < orderList.size(); i++) {

            rocketMQTemplate.setMessageQueueSelector((List<MessageQueue> mqs, org.apache.rocketmq.common.message.Message message, Object arg) -> {
                /*
                 * mqs：要发送消息的topic下的所有消息队列集合
                 * msg：发送的消息
                 * arg：发送消息时传递的参数 通过该参数指定发送到哪个队列
                 */
                int queueNum = Integer.parseInt(String.valueOf(arg)) % mqs.size();
                log.info("queueId:" + queueNum + ",message:" + new String(message.getBody()));
                return mqs.get(queueNum);
            });
            OrderStep orderStep = orderList.get(i);
            Message<OrderStep> message = new GenericMessage<>(orderStep);
            SendResult sendResult = rocketMQTemplate.syncSendOrderly("ORDERLY_TOPIC:ORDER", message, String.valueOf(orderStep.getOrderId()));
            log.info("顺序消息发送结果：" + sendResult);
        }
    }

    /**
     * 发送延时消息
     */
    public void delaySend() {
        for (int i = 0; i < 3; i++) {
            Message<String> message = new GenericMessage<>("delay_message:" + i);
            SendResult sendResult = rocketMQTemplate.syncSend("DELAY_TOPIC:DELAY", message, 1000, 2);
            log.info("延迟发送结果：" + sendResult.toString());
        }
    }


    /**
     * 发送批量消息
     */
    public void batchSend() {
        List<Message<?>> msgList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            msgList.add(MessageBuilder.withPayload("batch_message:" + i).build());
        }

        SendResult sendResult = rocketMQTemplate.syncSend("BATCH_TOPIC:BATCH", msgList);
        log.info("批量发送结果：" + sendResult.toString());
    }

    /**
     * 发送自定义过滤消息
     */
    public void filterSend() {
        for (int i = 0; i < 1; i++) {
            Message<String> message = MessageBuilder.withPayload("filter_message:" + i)
                    .setHeader("age", 3).build();
            SendResult sendResult = rocketMQTemplate.syncSend("FILTER_TOPIC", message, 1000, 2);
            log.info("自定义过滤消息发送结果：" + sendResult.toString());
        }
    }

    public void transactionSend() {
        for (int i = 0; i < 3; i++) {
            Message<String> message = MessageBuilder.withPayload("transaction_message:" + i).build();
            TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("TRANSACTION_TOPIC", message, i);
            log.info("事务消息发送结果：" + result.toString());
        }
    }
}
