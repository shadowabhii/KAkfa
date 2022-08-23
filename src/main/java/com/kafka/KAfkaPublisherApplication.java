package com.kafka;

import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KAfkaPublisherApplication {
	
	@Autowired
private	KafkaTemplate<String, Object> template;

	private String topic = "spring-kafka";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name)
	{
		template.send(topic,"Hi"+name);
		
		return "Data has been published";
	}
	
	@GetMapping
	public String publishMessage()
	{
		User user = new User(123,"Abhishek", new String[] {"Banglore","KR PURAM","10TH CROSS STREET"});
		template.send(topic,user);
		return "JSON Data has been publsihed";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KAfkaPublisherApplication.class, args);
	}

}
