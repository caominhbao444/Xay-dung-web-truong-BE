package com.project.trainingteam.dto.letter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LetterDto {


    private Long id;

    private String username;

    private String fullname;

    private String classUser;

    private String facultyName;

    private String major;

    private String phone;

    private Date processedDate;

    private Date resultDate;

    private String note ;

    private Integer status ;

    private Integer result;

    private String letterTypeName;

    private String semesterName;

    private String examName;

    private Integer printedQuantity;

    private String reason;

    private String handlePart;

    private Integer total;

    private boolean isDeleted;
}
