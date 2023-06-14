package com.project.trainingteam.entities.request;

import com.project.trainingteam.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_score_board_request")
public class ScoreBoardRequest extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_board_request_id")
    private Long id;

    private Long letterId;

    private String letterTypeName;

    private String scoreBoardTypeName;

    private Integer scoreBoardTypeQuantity;

    private Integer scoreBoardTypePrice ;

    private String scoreBoardTypeDesc;

}
