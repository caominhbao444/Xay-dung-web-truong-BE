package com.project.trainingteam.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private Long id;

    private String notificationTitle;

    private String notificationContent;

    private String facultyName;

    private String departCenterName;

    private String categoryName;

    private boolean checkNew;

    private boolean checkImportant ;

    private Set<String> SeenByUser;

    private Long countNotificationUnSeen;

    private boolean checkDeleted;


    public NotificationDto(String categoryName, Long countNotificationUnSeen){
        this.categoryName = categoryName;
        this.countNotificationUnSeen = countNotificationUnSeen;
    }
}
