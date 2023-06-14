package com.project.trainingteam.repo.inf.file;

import com.project.trainingteam.entities.file.NotificationFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationFileRepo extends JpaRepository<NotificationFile,Long> {

    @Query("SELECT n FROM NotificationFile n WHERE n.notificationId = :notificationId")
    List<NotificationFile> getNotificationFileByNotificationId(Long notificationId);
}
