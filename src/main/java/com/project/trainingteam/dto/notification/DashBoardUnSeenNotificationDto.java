package com.project.trainingteam.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardUnSeenNotificationDto {

    private String categoryName;

    private Long countNotificationUnSeen;
}
