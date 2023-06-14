package com.project.trainingteam.dto.letter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDto {

    private Long id;

    private String semesterName;

    private boolean action;
}
