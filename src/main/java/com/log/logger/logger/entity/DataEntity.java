package com.log.logger.logger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "data")
public class DataEntity {
    @Id
    private String id;
    private String fileName;
    private String fileType;
    private int fileSize;
    private Date timeSent;
    private Date timeReceived;
    @Lob
    @Column(length = 500*1024)
    private byte[] content;
}
