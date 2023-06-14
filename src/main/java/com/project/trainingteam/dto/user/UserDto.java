package com.project.trainingteam.dto.user;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id ;

    private String username;

    private String password;

    private String fullname;

    private String classUser;

    private String faculty;

    private String major;

    private String gender;

    private String avatar;

    private String nationality;

    private String hometown;

    private String address;

    private String email;

    private String phone;

    private String groupName;

    private String roleName;

    private String positionName;

    private boolean action ;

    private boolean locked ;
}
