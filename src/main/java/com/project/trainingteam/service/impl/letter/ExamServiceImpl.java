package com.project.trainingteam.service.impl.letter;

import com.project.trainingteam.dto.letter.ExamDto;
import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.letter.Exam;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.repo.inf.letter.ExamRepo;
import com.project.trainingteam.service.inf.letter.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExamRepo examRepo;

    @Override
    public ExamDto createdExam(Exam req) throws Exception {
        boolean existExam = examRepo.existsByExamName(req.getExamName());
        if(existExam){
            throw new Exception("GroupLetter đã tồn tại");
        }else{
            Exam exam = new Exam();
            exam.setExamName(req.getExamName());
            exam.setExamCode(req.getExamCode());
            exam.setExamDesc(req.getExamDesc());
            Exam savedExam = examRepo.save(exam);
            return modelMapper.map(savedExam, ExamDto.class);
        }
    };

    @Override
    public ExamDto updatedExam(Exam req) throws Exception {

        Exam exam = examRepo.findById(req.getId()).get();
        if(exam != null){
            exam.setExamName(req.getExamName());
            exam.setExamCode(req.getExamCode());
            exam.setExamDesc(req.getExamDesc());
            Exam savedExam = examRepo.save(exam);
            return modelMapper.map(savedExam,ExamDto.class);
        }else{
            throw new Exception("Không thể update GroupLetter");
        }
    };

    @Override
    public Page<ExamDto> getAllExam(Pageable pageable) throws Exception {
        Page<Exam> examPage = examRepo.findAll(pageable);
        List<Exam> examList = examPage.getContent();
        List<ExamDto> examDtoList = examList.stream().map((result) -> modelMapper.map(result,ExamDto.class)).collect(Collectors.toList());
        if(examDtoList != null){
            return new PageImpl<>(examDtoList,pageable,examPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy Page Exam");
        }
    };

    @Override
    public String deletedExam(Long id) {
        examRepo.deleteById(id);
        return "Delete Thành Công";
    }

    @Override
    public List<Exam> createdListExam(List<Exam> examList) {
        List<Exam> examDtoList = new ArrayList<>();

        for (Exam exam : examList) {
            examDtoList.add(exam);
        }
        return examDtoList;
    };
}
