package com.practo.insta.hackaton.reporting.consumer;


import com.practo.insta.hackaton.reporting.request.PatientVisitEventRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "${rabbitmq.queue}", id = "listener")
public class RabbitMQConsumer {

    private static Logger logger = LogManager.getLogger(RabbitMQConsumer.class.toString());

    @RabbitHandler
    public void receiver(final PatientVisitEventRequest patientVisitEventRequest) {
        logger.info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + patientVisitEventRequest.getVisitId());
    }

}
