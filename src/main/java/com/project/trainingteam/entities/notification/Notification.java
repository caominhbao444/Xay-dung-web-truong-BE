package com.project.trainingteam.entities.notification;

import com.project.trainingteam.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_notification")
public class Notification extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private String notificationTitle;

    private String notificationContent;

    private String facultyName;

    private String departCenterName;

    private String categoryName;

    private boolean checkNew = true;

    private boolean checkImportant = false;

    @ElementCollection
    private Set<String> seenByUser = new HashSet<>();

    private boolean checkDeleted = false;
}
