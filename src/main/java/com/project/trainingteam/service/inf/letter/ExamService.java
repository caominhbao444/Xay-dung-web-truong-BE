package com.project.trainingteam.service.inf.letter;


import com.project.trainingteam.dto.letter.ExamDto;
import com.project.trainingteam.entities.letter.Exam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    ExamDto createdExam(Exam req) throws Exception;

    ExamDto updatedExam(Exam req) throws Exception;

    Page<ExamDto> getAllExam(Pageable pageable) throws Exception;

    String deletedExam(Long id);

    List<Exam> createdListExam(List<Exam> examList);
}
