package com.kafkaservices.springboot.service;

import com.kafkaservices.springboot.model.WikimediaData;
import com.kafkaservices.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private final WikimediaDataRepository wikimediaDataRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger( KafkaDatabaseConsumer.class);

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }

    @KafkaListener(
            topics = "wikimedia_recent_change",
            groupId = "myKafkaGroup"
    )
    private void consume(String eventMessage){
        LOGGER.info(String.format("Event message received => %s", eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikimediaDataRepository.save(wikimediaData);

    }

    public String getData(){
        return wikimediaDataRepository.findAll().toString();
    }
}
