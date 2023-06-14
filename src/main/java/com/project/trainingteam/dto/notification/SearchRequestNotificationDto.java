package com.project.trainingteam.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestNotificationDto {

    private String notificationTitle;

    private String notificationContent;

    private String categoryName;

    private String facultyName = "";

    private String departCenterName = "";

    private Date startedDate;

    private Date EndedDate;

    private boolean checkImportant;


}
