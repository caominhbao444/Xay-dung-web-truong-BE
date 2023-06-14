package com.project.trainingteam.dto.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

    private Long id;

    private Long letterId;

    private String fileName;

    private String downloadUrl;

    private String fileType;

}
