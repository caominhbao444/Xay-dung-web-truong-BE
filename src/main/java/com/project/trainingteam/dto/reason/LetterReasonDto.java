package com.project.trainingteam.dto.reason;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LetterReasonDto {

    private Long id;

    private String letterTypeName;

    private String letterReasonName;

    private String letterReasonDesc;

    private boolean action;
}
