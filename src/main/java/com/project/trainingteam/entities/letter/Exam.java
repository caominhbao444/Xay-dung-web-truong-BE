package com.project.trainingteam.entities.letter;

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
@Table(name="um_exam")
public class Exam extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(nullable = false)
    private String examName;

    @Column(nullable = false)
    private String examCode;

    private String examDesc;

    private boolean action = true;
}
