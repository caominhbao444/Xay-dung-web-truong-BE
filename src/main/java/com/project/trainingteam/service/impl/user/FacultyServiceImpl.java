package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.FacultyDto;
import com.project.trainingteam.entities.user.Faculty;
import com.project.trainingteam.repo.inf.user.FacultyRepo;
import com.project.trainingteam.service.inf.user.FacultyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FacultyRepo facultyRepo;

    @Override
    public FacultyDto createdFaculty(Faculty req) throws Exception {
        boolean existFaculty = facultyRepo.existsByFacultyName(req.getFacultyName());
        if(existFaculty){
            throw new Exception("Faculty đã tồn tại");
        }else{
            Faculty faculty = new Faculty();
            faculty.setFacultyName(req.getFacultyName());
            faculty.setFacultyCode(req.getFacultyCode());
            faculty.setFacultyDesc(req.getFacultyDesc());
            Faculty savedFaculty = facultyRepo.save(faculty);
            return modelMapper.map(savedFaculty,FacultyDto.class);
        }
    };

    @Override
    public FacultyDto updatedFaculty(Faculty req) throws Exception {
        Faculty faculty = facultyRepo.findById(req.getId()).get();
        if(faculty != null){
            faculty.setFacultyName(req.getFacultyName());
            faculty.setFacultyCode(req.getFacultyCode());
            faculty.setFacultyDesc(req.getFacultyDesc());
            Faculty savedFaculty = facultyRepo.save(faculty);
            return modelMapper.map(savedFaculty,FacultyDto.class);
        }else{
            throw new Exception("Không tìm thấy Faculty");
        }
    };

    @Override
    public Page<FacultyDto> getAllFaculty(Pageable pageable) throws Exception {
        Page<Faculty> facultyPage = facultyRepo.findAll(pageable);
        List<Faculty> facultyList = facultyPage.getContent();
        List<FacultyDto> facultyDtoList = facultyList.stream().map((result) -> modelMapper.map(result,FacultyDto.class)).collect(Collectors.toList());
        if(facultyDtoList != null){
            return new PageImpl<>(facultyDtoList,pageable,facultyPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Faculty");
        }
    };

    @Override
    public String deletedFaculty(Long id){
        facultyRepo.deleteById(id);
        return "Delete Thành Công";
    };

}
