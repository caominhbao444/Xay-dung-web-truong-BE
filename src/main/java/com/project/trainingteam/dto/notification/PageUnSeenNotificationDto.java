package com.project.trainingteam.dto.notification;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageUnSeenNotificationDto {

    private Long id;

    private String notificationTitle;

    private String notificationContent;

    private String facultyName;

    private String departCenterName;

    private Date createdDate;

    private Date lastModifiedDate;
}
