package com.practo.insta.hackaton.reporting.producer;

import com.practo.insta.hackaton.reporting.request.PatientVisitEventRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    private static Logger logger = LogManager.getLogger(RabbitMQProducer.class.toString());


    public void send(final PatientVisitEventRequest patientVisitEventRequest) {
        rabbitTemplate.convertAndSend(exchange, routingkey, patientVisitEventRequest);
//        rabbitTemplate.convertAndSend(queue.getName(), patientVisitEventRequest);
        logger.info("Sending Message to the Queue : " + patientVisitEventRequest.toString());
    }
}