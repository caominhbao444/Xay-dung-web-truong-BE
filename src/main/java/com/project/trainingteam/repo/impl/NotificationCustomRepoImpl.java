package com.project.trainingteam.repo.impl;

import com.project.trainingteam.entities.notification.Notification;
import com.project.trainingteam.repo.inf.notification.NotificationCustomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class NotificationCustomRepoImpl implements NotificationCustomRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Notification> searchNotificationByFacultyNameRepo(String facultyName,String notificationTitle, String notificationContent,String categoryName,Date startedDate,Date endedDate,Pageable pageable){
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE n.faculty_name LIKE :facultyName " +
                "AND n.notification_title LIKE :notificationTitle " +
                "AND n.notification_content LIKE :notificationContent " +
                "AND n.category_name LIKE :categoryName " +
                "AND DATE_TRUNC('MONTH',n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("facultyName",facultyName);
        query.setParameter("notificationTitle",notificationTitle);
        query.setParameter("notificationContent",notificationContent);
        query.setParameter("categoryName", categoryName);
        query.setParameter("startedDate",startedDate);
        query.setParameter("endedDate",endedDate);

        List<Notification> resultList = query.getResultList();

//        System.out.println(resultList);
//        return resultList;

        System.out.println(resultList);
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }



    public Page<Notification> searchNotificationByDepartCenterName(String departCenterName, String notificationTitle, String notificationContent, String categoryName, Date startedDate, Date endedDate, Pageable pageable) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE n.depart_center_name LIKE :departCenterName " +
                "AND n.notification_title LIKE :notificationTitle " +
                "AND n.notification_content LIKE :notificationContent " +
                "AND n.category_name LIKE :categoryName " +
                "AND DATE_TRUNC('MONTH',n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("departCenterName",departCenterName);
        query.setParameter("notificationTitle",notificationTitle);
        query.setParameter("notificationContent",notificationContent);
        query.setParameter("categoryName", categoryName);
        query.setParameter("startedDate",startedDate);
        query.setParameter("endedDate",endedDate);

        List<Notification> resultList = query.getResultList();
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }

    @Override
    public Page<Notification> findNotificationByCategoryNameAndFacultyName(String categoryName, String notificationTitle, String notificationContent, String facultyName, Date startedDate, Date endedDate, Pageable pageable) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE n.category_name LIKE :categoryName " +
                "AND n.notification_title LIKE :notificationTitle " +
                "AND n.notification_content LIKE :notificationContent " +
                "AND n.faculty_name LIKE :facultyName " +
                "AND DATE_TRUNC('MONTH',n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("categoryName",categoryName);
        query.setParameter("notificationTitle",notificationTitle);
        query.setParameter("notificationContent",notificationContent);
        query.setParameter("facultyName", facultyName);
        query.setParameter("startedDate",startedDate);
        query.setParameter("endedDate",endedDate);

        List<Notification> resultList = query.getResultList();
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }

    @Override
    public Page<Notification> findNotificationByCategoryNameAndDepartCenterName(String categoryName, String notificationTitle, String notificationContent, String departCenterName, Date startedDate, Date endedDate, Pageable pageable) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE n.category_name LIKE :categoryName " +
                "AND n.notification_title LIKE :notificationTitle " +
                "AND n.notification_content LIKE :notificationContent " +
                "AND n.depart_center_name LIKE :departCenterName " +
                "AND DATE_TRUNC('MONTH',n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("categoryName",categoryName);
        query.setParameter("notificationTitle",notificationTitle);
        query.setParameter("notificationContent",notificationContent);
        query.setParameter("departCenterName", departCenterName);
        query.setParameter("startedDate",startedDate);
        query.setParameter("endedDate",endedDate);

        List<Notification> resultList = query.getResultList();
        int totalResults = resultList.size();

        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int toIndex = Math.min(offset + pageSize, totalResults);

        List<Notification> pageResults = resultList.subList(offset, toIndex);

        return new PageImpl<>(pageResults, pageable, totalResults);
    }

    @Override
    public List<Notification> testByCategory(String facultyName,String notificationTitle, String notificationContent,String categoryName,Date startedDate,Date endedDate) {
        String sqlQuery = "SELECT * " +
                "FROM um_notification n " +
                "WHERE n.faculty_name LIKE :facultyName " +
                "AND n.notification_title LIKE :notificationTitle " +
                "AND n.notification_content LIKE :notificationContent " +
                "AND n.category_name LIKE :categoryName " +
                "AND DATE_TRUNC('MONTH',n.created_date) BETWEEN DATE(:startedDate) AND DATE(:endedDate)";

        Query query = entityManager.createNativeQuery(sqlQuery, Notification.class);
        query.setParameter("facultyName",facultyName);
        query.setParameter("notificationTitle",notificationTitle);
        query.setParameter("notificationContent",notificationContent);
        query.setParameter("categoryName", categoryName);
        query.setParameter("startedDate",startedDate);
        query.setParameter("endedDate",endedDate);

        List<Notification> resultList = query.getResultList();

        System.out.println(resultList);
        return resultList;
    }

}
