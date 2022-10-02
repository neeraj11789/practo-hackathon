package com.practo.insta.hackaton.reporting.resource;

import com.practo.insta.hackaton.reporting.producer.RabbitMQProducer;
import com.practo.insta.hackaton.reporting.request.PatientVisitEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQDemoResource {

    @Autowired
    RabbitMQProducer rabbitMQSender;

    @RequestMapping(value = "/sender", method = RequestMethod.POST, produces = "application/json")
    public String producer(@RequestBody PatientVisitEventRequest request) {
        rabbitMQSender.send(request);
        return "Message sent to the RabbitMQ Queue Successfully";
    }
}