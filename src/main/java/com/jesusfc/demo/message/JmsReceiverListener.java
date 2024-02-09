package com.jesusfc.demo.message;

import com.jesusfc.demo.config.JmsMessageConfig;
import com.jesusfc.demo.model.JmsMessage;
import jakarta.jms.Destination;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
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

    /*
    @JmsListener(destination = JmsMessageConfig.MY_QUEUE)
    public void receiveMessageFromQueue(@Payload JmsMessage messageFrontQueue,
                               @Headers MessageHeaders headers, Message<JmsMessage> message) {

        try {
            System.out.println("I Got a Message!!!!!");
            System.out.println(messageFrontQueue);
            System.out.println("Received <" + message + ">");
        } catch (JmsException e) {
            System.out.println(e.getMessage());
        }
    }
     */

     @JmsListener(destination = JmsMessageConfig.MY_QUEUE)
    public void messageFromQueueListen(JmsMessage jmsMessage) {
        try {
            System.out.println("Received Uuid: " + jmsMessage.getUuid());
            System.out.println(jmsMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @JmsListener(destination = JmsMessageConfig.MY_SEND_RCV_QUEUE)
    public void receiveAndPayBackFromQueueListen(@Payload JmsMessage jmsMessage,
                                                 @Headers MessageHeaders headers) {

        System.out.println("Uuid: " + jmsMessage.getUuid());
        System.out.println("Received Message <" + jmsMessage + ">");
        System.out.println("**************************************");

        // Send back response to the queue
        JmsMessage payLoadMsg = JmsMessage.builder()
                .uuid(UUID.randomUUID())
                .message("Message from service 1 received, Hello from Service 2")
                .body("Hello from Service 2")
                .to("Hello Service 1")
                .build();

        jmsTemplate.convertAndSend((Destination) Objects.requireNonNull(headers.get("jms_replyTo")), payLoadMsg);

    }

}
