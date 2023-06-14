package com.project.trainingteam.entities.letter;


import com.project.trainingteam.entities.base.Auditable;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_letter_type")
public class LetterType extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_type_id")
    private Long id;

    @Column(unique = true)
    private String letterTypeName;

    private String semesterName ;

    private boolean checkSemesterName = false;

    private String examName ;

    private boolean checkExamName = false;

    private Integer printedQuantity;

    private boolean checkPrintedQuantity = false;

    private String reason;

    private String handlePart;

    private Integer total;

    private boolean checkTotal = false;

    private boolean checkScoreBoard = false;

    @Column(columnDefinition = "boolean default null")
    private boolean isDeleted;

    private boolean action = true ;

}
