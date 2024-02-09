package com.jesusfc.demo.config;

import org.springframework.context.annotation.Configuration;

/**
 * Author Jesús Fdez. Caraballo
 * Created on Ene - 2024
 */
@Configuration
public class JmsMessageConfig {

    public static final String MY_QUEUE = "my-jms-queue";
    public static final String MY_SEND_RCV_QUEUE = "reply_back_to_me";

}

