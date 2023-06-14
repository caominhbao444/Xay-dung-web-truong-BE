package com.project.trainingteam.service.inf.file;

import com.project.trainingteam.dto.file.LetterFileDto;
import com.project.trainingteam.entities.file.LetterFile;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface LetterFileService {

    List<LetterFile> savedMultiLetterFile(MultipartFile[] multipartFiles, Long letterId, String letterTypeName) throws Exception;
    
    List<LetterFileDto> findLetterFileUserByUserNameAndLetterId(@Param("letterId") Long letterId) throws Exception;

    LetterFile downloadLetterFile(@Param("id")Long id);
}
