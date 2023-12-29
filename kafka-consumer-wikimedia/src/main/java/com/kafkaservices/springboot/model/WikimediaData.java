package com.kafkaservices.springboot.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "wikimedia_recent_change")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class WikimediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "wiki_event_data", columnDefinition = "TEXT")
    private String WikiEventData;

}
