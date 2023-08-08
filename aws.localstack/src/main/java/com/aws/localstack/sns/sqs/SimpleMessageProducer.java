package com.aws.localstack.sns.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
public class SimpleMessageProducer {
	
	@Value( "${order.topic}" )
	private String ORDER_CREATED_TOPIC;

    @Autowired
    private NotificationMessagingTemplate notificationMessagingTemplate;

    public void publish(Evento event) {
        notificationMessagingTemplate.convertAndSend(ORDER_CREATED_TOPIC, event);
    }
}
