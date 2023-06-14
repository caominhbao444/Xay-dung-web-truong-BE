package com.project.trainingteam.entities.file;

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
@Table(name="um_letter_file")
public class LetterFile extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_file_id")
    private Long id;

    private String fileName;

    private Long letterId;

    private String letterTypeName;

    private String fileType;

    private String downloadUrl;
    @Lob
    private byte[] data;


}
