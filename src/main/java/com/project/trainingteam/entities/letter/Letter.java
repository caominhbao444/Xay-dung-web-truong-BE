package com.project.trainingteam.entities.letter;

import com.project.trainingteam.entities.base.Auditable;

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
@Table(name="um_letter")
public class Letter extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private Long id;

    private String username;

    private String fullname;

    private String classUser;

    private String facultyName;

    private String major;

    private String phone;

    @Column(columnDefinition = "date default null")
    private Date processedDate;

    @Column(columnDefinition = "date default null")
    private Date resultDate;

    private String note = "";

    private Integer status = 0;

    private Integer result = 0;

    private String letterTypeName;

    private String semesterName;

    private String examName;

    private Integer printedQuantity;

    private String reason;

    private String handlePart;

    private Integer total;

    @Column(columnDefinition = "boolean default null")
    private boolean isDeleted;


}
