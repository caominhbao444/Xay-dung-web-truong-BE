package com.project.trainingteam.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

    private Long id;

    private String facultyName;

    private String facultyCode;

    private String facultyDesc;

    private boolean action ;
}
