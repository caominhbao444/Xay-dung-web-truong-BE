package com.project.trainingteam.dto.scoreboard;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreBoardTypeDto {

    private Long id;

    private String scoreBoardTypeName;

    private Integer scoreBoardTypeQuantity ;

    private Integer scoreBoardTypePrice ;

    private String scoreBoardTypeDesc;

    private boolean action;

}
