package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import jakarta.jms.Destination;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

/**
 * Author Jesús Fdez. Caraballo
 * Created on ene - 2024
 */
@AllArgsConstructor
@Component
public class JmsReceiverListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsMessageConfig.MY_QUEUE)
    public void receiveMessageFromQueue(@Payload JmsMessage messageFrontQueue,
                               @Headers MessageHeaders headers, Message message) {

        System.out.println("I Got a Message!!!!!");
        System.out.println(messageFrontQueue);
        System.out.println("Received <" + message + ">");
    }

    @JmsListener(destination = JmsMessageConfig.MY_SEND_RCV_QUEUE)
    public void receiveHelloMessage(@Payload JmsMessage messageFrontSendAndReceivedQueue,
                                    @Headers MessageHeaders headers, Message jmsMessage,
                                    org.springframework.messaging.Message springMessage) {

        System.out.println("I Got a Hello World Message!!!!!");

        JmsMessage payLoadMsg = JmsMessage.builder()
                .uuid(UUID.randomUUID())
                .message("Message received in service 2")
                .build();
        System.out.println(messageFrontSendAndReceivedQueue);
        System.out.println("Received Hello world <" + springMessage + ">");

        jmsTemplate.convertAndSend((Destination) Objects.requireNonNull(jmsMessage.getHeaders().get("jms_replyTo")), payLoadMsg);

    }

}
