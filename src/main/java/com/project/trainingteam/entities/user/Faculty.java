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
@Table(name="um_faculty")
public class Faculty extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String facultyName;

    @Column(nullable = false)
    private String facultyCode;

    private String facultyDesc;

    private boolean action = true;

}
