package com.project.trainingteam.dto.letter;

import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LetterTypeDto {

    private Long id;

    private String letterTypeName;

    private String semesterName ;

    private String examName ;

    private Integer printedQuantity;

    private String reason;

    private List<ScoreBoardType> scoreBoardTypeList;

    private String handlePart;

    private Integer total;

    private boolean action;
}
