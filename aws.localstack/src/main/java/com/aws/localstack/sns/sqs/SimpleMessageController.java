package com.aws.localstack.sns.sqs;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin({"*"})
@Log4j2
public class SimpleMessageController {
	
	@Autowired
	SimpleMessageProducer simpleMessageProducer;
	
	@PostMapping("/publicaSns")
    public String addScore(@RequestBody Evento evento) {
		UUID uuid = UUID.randomUUID();
		evento.setEventId(uuid.toString());
		log.info("uuid {}",uuid);
		simpleMessageProducer.publish(evento);
		return  "OK";
    }

}
