package com.project.trainingteam.entities.user;

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
@Table(name="um_depart_center")
public class DepartCenter extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depart_center_id")
    private Long id;

    @Column(unique = true)
    private String departCenterName;

    private String departCenterCode;

    private String departCenterDesc;

    private boolean action = true;
}
