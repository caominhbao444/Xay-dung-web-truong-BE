package com.project.trainingteam.dto.file;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationFileDto {

    private Long id;

    private long notificationId;

    private String notificationTitle;

    private String fileName;

    private String fileType;

    private String downloadUrl;


}
