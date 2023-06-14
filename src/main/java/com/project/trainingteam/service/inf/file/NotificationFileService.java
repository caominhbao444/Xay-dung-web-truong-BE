package com.project.trainingteam.service.inf.file;

import com.project.trainingteam.dto.file.NotificationFileDto;
import com.project.trainingteam.entities.file.NotificationFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface NotificationFileService {

    List<NotificationFile> savedMultiNewsFile(MultipartFile[] multipartFiles, Long notificationId, String notificationTitle)throws Exception;

    NotificationFile downloadNotificationFile(Long id);


    List<NotificationFileDto> getNotificationFileByNotificationId(Long notificationId)throws Exception;

}
