package com.project.trainingteam.dto.letter;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    private Long id;

    private String examName;

    private String examCode;

    private String descExam;

    private boolean action;
}
