package com.wf.messaging.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "messages_statistics")
@Builder
@Data
public class MessageStatistics {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "messages_statistics_seq", sequenceName = "messages_statistics_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_statistics_seq")
    private Long id;

    @Column(name = "message_uuid")
    private String uuid;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name = "processing_time_ms")
    private Integer proccessingTime;

    @Column(name = "processed_date")
    private String processedDate;

    @Column(name = "process_name")
    private String processName;
}
