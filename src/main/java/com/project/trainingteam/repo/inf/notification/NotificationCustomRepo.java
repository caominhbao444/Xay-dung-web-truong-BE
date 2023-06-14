package com.project.trainingteam.repo.inf.notification;

import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationCustomRepo {


    Page<Notification> searchNotificationByFacultyNameRepo (String facultyName,String notificationTitle,String notificationContent,String categoryName,Date startedDate, Date endedDate,Pageable pageable);

    Page<Notification> searchNotificationByDepartCenterName(String departCenterName,String notificationTitle,String notificationContent,String categoryName,Date startedDate, Date endedDate,Pageable pageable);

    Page<Notification> findNotificationByCategoryNameAndFacultyName(String categoryName,String notificationTitle,String notificationContent,String facultyName,Date startedDate,Date endedDate,Pageable pageable);

    Page<Notification> findNotificationByCategoryNameAndDepartCenterName(String categoryName,String notificationTitle,String notificationContent,String departCenterName,Date startedDate,Date endedDate,Pageable pageable);

    List<Notification> testByCategory (String facultyName,String notificationTitle,String notificationContent,String categoryName,Date startedDate, Date endedDate);


}
