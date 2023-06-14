package com.project.trainingteam.entities.user;

import com.project.trainingteam.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_user")
public class User extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "user_id")
    private Long id ;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullname;

    private String classUser;

    private String facultyName;

    private String major;

    private String gender;

    @Column(columnDefinition = "TEXT", length = 10485760)
    private String avatar;

    private String nationality;

    private String hometown;

    private String address;

    private String email;

    private String phone;

    private String departCenterName;

    @Column(nullable = false)
    private String roleName;

    @Column(nullable = false)
    private String positionName;

    private boolean action = true;

    private boolean locked = false;


}
