package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.entities.letter.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SemesterService {
    SemesterDto createdSemester(Semester req) throws Exception;

    SemesterDto updatedSemester(Semester req) throws Exception;

    Page<SemesterDto> getAllSemester(Pageable pageable) throws Exception;

    String deletedSemester(Long id);


}
