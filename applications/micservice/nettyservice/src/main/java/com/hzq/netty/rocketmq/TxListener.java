package com.hzq.netty.rocketmq;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Huangzq
 * @title: TxListener
 * @projectName applications
 * @date 2020/4/22 17:20
 */
public class TxListener implements TransactionListener {

    Map<String,Boolean> map = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {

        System.out.println(message+"***"+o);

        if(o.hashCode()%2 ==0){
            map.put(message.getKeys(),true);
            System.out.println(message.getKeys()+"本地事务操作成功");
        }else{
            map.put(message.getKeys(),false);
            System.out.println(message.getKeys()+"本地事务操作失败");
        }

        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        Boolean flag = map.get(messageExt.getKeys());

        System.out.println("检查数据："+messageExt.getKeys()+"***"+flag);

        if(flag){
            return LocalTransactionState.COMMIT_MESSAGE;
        }
        return LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
