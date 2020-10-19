package org.zhouhy.securityproject.async;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Slf4j
@Component
public class MockQueue {

    private String placeOrder; //这个值有值代表下单的消息

    private String completeOrder; //这个值有值代表订单完成的消息

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(()->{
                log.info("接到下单请求");
        try {
            Thread.sleep(1000); //模拟下单请求
            DeferredResult<String> result = new DeferredResult<>();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.completeOrder = placeOrder; //下单请求完成了赋值给它
        log.info("下单请求处理完毕");
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
