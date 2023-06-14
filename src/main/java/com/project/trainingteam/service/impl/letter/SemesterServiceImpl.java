package com.project.trainingteam.service.impl.letter;

import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.entities.letter.Semester;
import com.project.trainingteam.repo.inf.letter.SemesterRepo;
import com.project.trainingteam.service.inf.letter.SemesterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SemesterRepo semesterRepo;

    @Override
    public SemesterDto createdSemester(Semester req) throws Exception {
        boolean existSemester = semesterRepo.existsBySemesterName(req.getSemesterName());
        if(existSemester){
            throw new Exception("Semester đã tồn tại");
        }else{
            Semester semester = new Semester();
            semester.setSemesterName(req.getSemesterName());
            Semester savedSemester = semesterRepo.save(semester);
            return modelMapper.map(savedSemester, SemesterDto.class);
        }
    };

    @Override
    public SemesterDto updatedSemester(Semester req) throws Exception {
        Semester semester = semesterRepo.findById(req.getId()).get();
        if(semester != null){
            semester.setSemesterName(req.getSemesterName());
            Semester savedSemester = semesterRepo.save(semester);
            return modelMapper.map(savedSemester,SemesterDto.class);
        }else{
            throw new Exception("Không thể update Semester");
        }
    };



    @Override
    public Page<SemesterDto> getAllSemester(Pageable pageable) throws Exception {
        Page<Semester> semesterPage = semesterRepo.findAll(pageable);
        List<Semester> semesterList = semesterPage.getContent();
        List<SemesterDto> semesterDtoList = semesterList.stream().map((result) -> modelMapper.map(result,SemesterDto.class)).collect(Collectors.toList());
        if(semesterDtoList != null){
            return new PageImpl<>(semesterDtoList,pageable,semesterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Semester");
        }
    };

    //findAll
    //
    @Override
    public String deletedSemester(Long id) {
        semesterRepo.deleteById(id);
        return "Delete Thành Công";
    };
}
