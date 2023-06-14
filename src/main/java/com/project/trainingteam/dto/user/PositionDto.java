package com.project.trainingteam.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {
    private Long id;

    private String positionName;

    private String positionCode;

    private String positionDesc;

    private boolean action;
}
