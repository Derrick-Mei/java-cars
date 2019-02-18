package com.dkm.javacars;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
// tells spring to run this constantly in background
@Service
public class AbLogConsumer
{
    @RabbitListener(queues = AaJavacarsApplication.QUEUE_NAME)
    public void consumeMessage(final Message cm)
    {
        log.info("\n\n****** Msg from Ab Listener: {} ******\n\n", cm.toString());
    }
}
