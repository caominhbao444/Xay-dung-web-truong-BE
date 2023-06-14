package com.project.trainingteam.service.inf.notification;

import com.project.trainingteam.dto.notification.DashBoardUnSeenNotificationDto;
import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.dto.notification.PageUnSeenNotificationDto;
import com.project.trainingteam.dto.notification.SearchRequestNotificationDto;
import com.project.trainingteam.entities.notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface NotificationService {

    NotificationDto createdNotification(Notification notification, MultipartFile[] multipartFiles)throws Exception;

    NotificationDto updatedNotification(Notification notification)throws Exception;

    NotificationDto getNotificationById(Notification notification)throws Exception;

    String seenByUser(Long notificationId) throws Exception;

    List<NotificationDto> getNewNotificationList()throws Exception;

    List<NotificationDto> getImportantNotificationList() throws Exception;

    List<DashBoardUnSeenNotificationDto> getUnseenCountNotificationByCategoryName()throws Exception;

    Page<NotificationDto> getAllNotification(Pageable pageable) throws Exception;

    Page<PageUnSeenNotificationDto> getUnSeenNotificationByCategoryName(String categoryName, Pageable pageable)throws Exception;

    Page<NotificationDto> getAllImportantNotification(Pageable pageable) throws Exception;

    Page<NotificationDto> getAllNewNotification(Pageable pageable) throws Exception;

    Page<NotificationDto> getAllNotificationByFacultyName(String facultyName, Pageable pageable)throws Exception;

    Page<NotificationDto> getAllNotificationByCategoryName(String categoryName,Pageable pageable)throws Exception;

    Page<NotificationDto> getAllNotificationByDepartCenterName(String departCenterName, Pageable pageable)throws Exception;

    Page<NotificationDto> searchNotification(SearchRequestNotificationDto searchRequestNotificationDto,Pageable pageable)throws Exception;

    Page<NotificationDto> searchNotificationByFacultyName(String facultyName, SearchRequestNotificationDto searchRequestNotificationDto,Pageable pageable) throws Exception;

    Page<NotificationDto> searchNotificationByDepartCenterName(String departCenterName, SearchRequestNotificationDto searchRequestNotificationDto, Pageable pageable)throws Exception;

    Page<NotificationDto> searchNotificationByCategoryName(String categoryName,SearchRequestNotificationDto searchRequestNotificationDto,Pageable pageable) throws Exception;
    List<Notification> test(String facultyName, SearchRequestNotificationDto searchRequestNotificationDto);
    String deletedNotification(Long id)throws Exception;
}
