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
@Table(name="um_role")
public class Role extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "role_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String roleName;

    @Column(nullable = false)
    private String roleCode;

    private String roleDesc;

    private boolean action = true;
}
