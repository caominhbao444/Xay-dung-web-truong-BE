package com.project.trainingteam.entities.scoreboard;


import com.project.trainingteam.entities.base.Auditable;
import com.project.trainingteam.entities.letter.Semester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_score_board_type")
public class ScoreBoardType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_board_type_id")
    private Long id;

    private String scoreBoardTypeName;

    private Integer scoreBoardTypeQuantity = 0;

    private Integer scoreBoardTypePrice = 3000;

    private String scoreBoardTypeDesc = "";

    private boolean action = true;
}
