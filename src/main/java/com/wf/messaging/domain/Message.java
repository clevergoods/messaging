package com.wf.messaging.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@Builder
@Data
public class Message {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "messages_seq", sequenceName = "messages_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_seq")
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name="message_type")
    String messageType;

    @Column(name="message_from")
    private String messageFrom;

    @Column(name="message_content")
    private String messageContent;

    @Column(name="message_size")
    private Integer messageSize;

    @Column(name="meta_information")
    private String metaInformation;

    @OneToOne(orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "id")
    private MessageStatistics statistics;
}
