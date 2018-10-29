package com.wf.messaging.amqpService;

import com.wf.messaging.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @RabbitListener(queues = "${amqp.queue_name}")
    public void receiveMessage(Message message) {
        taskExecutor.execute(applicationContext.getBean(MessageWorker.class, message));
    }
}
