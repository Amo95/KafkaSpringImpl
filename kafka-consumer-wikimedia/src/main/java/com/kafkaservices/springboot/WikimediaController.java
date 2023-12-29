package com.kafkaservices.springboot;

import com.kafkaservices.springboot.service.KafkaDatabaseConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class WikimediaController {


    private final KafkaDatabaseConsumer kafkaDatabaseConsumer;

    public WikimediaController(KafkaDatabaseConsumer kafkaDatabaseConsumer) {
        this.kafkaDatabaseConsumer = kafkaDatabaseConsumer;
    }

    @GetMapping("/getData")
    private String getData(){
        return kafkaDatabaseConsumer.getData();
    }
}
