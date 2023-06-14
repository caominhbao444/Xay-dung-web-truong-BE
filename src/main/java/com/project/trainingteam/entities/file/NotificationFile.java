package com.project.trainingteam.entities.file;

import com.project.trainingteam.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_notification_file")
public class NotificationFile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_file_id")
    private Long id;

    private long notificationId;

    private String notificationTitle;

    private String fileName;

    private String fileType;

    private String downloadUrl;

    @Lob
    private byte[] data;
}
