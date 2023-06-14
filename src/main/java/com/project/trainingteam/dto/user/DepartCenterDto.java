package com.project.trainingteam.dto.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartCenterDto {

    private Long id;

    private String departCenterName;

    private String departCenterCode;

    private String departCenterDesc;

    private boolean action;
}
