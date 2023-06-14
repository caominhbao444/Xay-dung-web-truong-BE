package com.project.trainingteam.repo.inf.notification;

import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE n.id = :id")
    Notification findNotificationById(Long id);


    @Query("SELECT n FROM Notification n WHERE n.checkNew = true AND n.checkImportant = false ORDER BY n.createdDate LIMIT 5")
    List<Notification> findNewNotification();


    @Query("SELECT n FROM Notification n WHERE n.checkImportant = true ORDER BY n.createdDate LIMIT 10")
    List<Notification> findImportantNotification();



    @Query(value = "SELECT c.category_name, COUNT(n.category_name) AS count " +
                "FROM UM_CATEGORY c " +
                "LEFT JOIN UM_NOTIFICATION n ON c.category_name = n.category_name " +
                "LEFT JOIN notification_seen_by_user m ON n.notification_Id = m.notification_notification_Id " +
                "    AND m.seen_by_user = :username " +
                "WHERE m.notification_notification_id IS NULL " +
                "GROUP BY c.category_name", nativeQuery = true)
    List<Object[]> findUnseenNewsCountByCategory(String username);


    @Query(value = "SELECT n.notification_id as id,n.notification_title, n.notification_content, n.faculty_name, n.depart_center_name, n.created_date,n.last_modified_date " +
            "FROM um_notification n " +
            "LEFT JOIN notification_seen_by_user m ON n.notification_id = m.notification_notification_id " +
            "WHERE m.notification_notification_id IS NULL " +
            "AND n.category_name = :categoryName " +
            "GROUP BY id,n.notification_title, n.notification_content, n.faculty_name, n.depart_center_name, n.created_date,n.last_modified_date " +
            "ORDER BY n.created_date", nativeQuery = true)
    Page<Object[]> getUnSeenNotificationByCategoryName(String categoryName, Pageable pageable);


    @Query("SELECT n FROM Notification n WHERE n.checkImportant = true ORDER BY n.createdDate")
    Page<Notification> getAllImportantNotification(Pageable pageable);


    @Query("SELECT n FROM Notification n WHERE n.checkImportant = false ORDER BY n.createdDate")
    Page<Notification> getAllNewNotification(Pageable pageable);


    @Query("SELECT n FROM Notification n WHERE n.facultyName = :facultyName ORDER BY n.createdDate")
    Page<Notification> getAllNotificationByFacultyName(String facultyName,Pageable pageable);


    @Query("SELECT n FROM Notification n WHERE n.departCenterName = :departCenterName ORDER BY n.createdDate")
    Page<Notification> getAllNotificationByDePartCenterName(String departCenterName,Pageable pageable);


    @Query("SELECT n FROM Notification n WHERE n.categoryName = :categoryName ORDER BY n.createdDate")
    Page<Notification> getAllNotificationByCategoryName(String categoryName,Pageable pageable);


    @Query("SELECT n " +
            "FROM Notification n " +
            "WHERE (n.notificationTitle LIKE %:notificationTitle%) " +
            "AND (n.notificationContent LIKE %:notificationContent% ) " +
            "AND (n.categoryName LIKE %:categoryName%) " +
            "AND (n.facultyName LIKE %:facultyName%) " +
            "AND (n.checkImportant = :checkImportant)"+
            "AND (n.createdDate BETWEEN :startedDate AND :endedDate)")
    Page<Notification> searchNotification1(String notificationTitle,String notificationContent,String categoryName, String facultyName,Boolean checkImportant,Date startedDate,Date endedDate,Pageable pageable);

    @Query("SELECT n " +
            "FROM Notification n " +
            "WHERE (n.notificationTitle LIKE %:notificationTitle%) " +
            "AND (n.notificationContent LIKE %:notificationContent% ) " +
            "AND (n.categoryName LIKE %:categoryName%) " +
            "AND (n.departCenterName LIKE %:departCenterName%) " +
            "AND (n.checkImportant = :checkImportant)"+
            "AND (n.createdDate BETWEEN :startedDate AND :endedDate)")
    Page<Notification> searchNotification2(String notificationTitle,String notificationContent,String categoryName, String departCenterName,Boolean checkImportant,Date startedDate,Date endedDate,Pageable pageable);

    @Query("SELECT MIN(n.createdDate) FROM Notification n")
    Date theLastDateNotification();

    @Query("SELECT MAX(n.createdDate) FROM Notification n")
    Date theNewDateNotification();




}
