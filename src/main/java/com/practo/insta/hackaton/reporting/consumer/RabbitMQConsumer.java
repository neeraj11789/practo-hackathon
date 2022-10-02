package com.practo.insta.hackaton.reporting.consumer;


import com.practo.insta.hackaton.reporting.request.PatientVisitEventRequest;
import com.practo.insta.hackaton.reporting.service.PatientVisitService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "${rabbitmq.queue}", id = "listener")
public class RabbitMQConsumer {

    private static Logger logger = LogManager.getLogger(RabbitMQConsumer.class.toString());

    @Autowired
    PatientVisitService patientVisitService;

    @RabbitHandler
    public void receiver(final PatientVisitEventRequest patientVisitEventRequest) {
        logger.info("Processing the PatientVisitEvent {}:", patientVisitEventRequest);
        try {
            patientVisitService.index(patientVisitEventRequest);
        }catch (Exception e){
            logger.info("Exception while processing the message {} {}", patientVisitEventRequest, ExceptionUtils.getMessage(e));
        }
    }

}
