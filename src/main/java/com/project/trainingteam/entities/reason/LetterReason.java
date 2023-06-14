package com.project.trainingteam.entities.reason;

import com.project.trainingteam.entities.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="um_letter_reason")
public class LetterReason extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_reason_id")
    private Long id;

    private String letterTypeName;

    private String letterReasonName;

    private String letterReasonDesc = "";

    private boolean action = true;
}
