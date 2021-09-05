package com.wk.mq.rocketmq.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderStep {
    private long orderId;

    private String desc;

    @Override
    public String toString() {
        return "OrderStep{orderId:" + orderId + ",desc:\"" + desc + "\"}";
    }

    public static List<OrderStep> buildOrderList() {
        ArrayList<OrderStep> orderSteps = new ArrayList<>();
        OrderStep orderStep = new OrderStep(1001, "创建订单");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1002, "创建订单");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1003, "创建订单");

        orderSteps.add(orderStep);
        orderStep = new OrderStep(1001, "付款");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1002, "付款");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1003, "付款");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1001, "发货");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1002, "发货");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1003, "发货");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1001, "完结");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1002, "完结");
        orderSteps.add(orderStep);
        orderStep = new OrderStep(1003, "完结");
        orderSteps.add(orderStep);
        return orderSteps;
    }
}
