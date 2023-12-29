package com.kafkaservices.springboot.service;

import com.kafkaservices.springboot.utility.WikimediaChangesHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recent_change";
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // to read real time stream data from wikimedia we use event source
        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        EventSource.Builder eventSource = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, eventSource);

        BackgroundEventSource backgroundEventSource = builder.build();
        backgroundEventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
