package com.wk.mq.rocketmq.transaction;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 订单事务消息生产监听
 */
@Slf4j
@Component
@RocketMQTransactionListener
public class OrderTXMsgListener implements RocketMQLocalTransactionListener {
    /**
     * 步骤二：
     * 描述：mq收到事务消息后，开始执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info(">>>> TX message listener execute local transaction, message={},args={} <<<<", msg, arg);
        // 执行本地事务
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.COMMIT;
        try {
            String jsonString = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            log.info("执行本地事务成功：{}", jsonString);
        } catch (Exception e) {
            log.error(">>>> exception message={} <<<<", e.getMessage());
            result = RocketMQLocalTransactionState.UNKNOWN;
        }
        return result;
    }

    /**
     * 步骤四
     * 描述：mq回调检查本地事务执行情况
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info(">>>> TX message listener check local transaction, message={} <<<<", msg.getPayload());
        // 检查本地事务
        RocketMQLocalTransactionState result = RocketMQLocalTransactionState.COMMIT;
        try {
            String jsonString = new String((byte[]) msg.getPayload(), StandardCharsets.UTF_8);
            if (StringUtils.isBlank(jsonString)) {
                result = RocketMQLocalTransactionState.UNKNOWN;
            }
        } catch (Exception e) {
            // 异常就回滚
            log.error(">>>> exception message={} <<<<", e.getMessage());
            result = RocketMQLocalTransactionState.ROLLBACK;
        }

        return result;
    }
}
