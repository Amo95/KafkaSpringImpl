package com.kafkaservices.springboot;

import com.kafkaservices.springboot.service.WikimediaChangesProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootProducerApplication implements CommandLineRunner {

    @Autowired
    private WikimediaChangesProducer wikimediaChangesProducer;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootProducerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();
    }
}
