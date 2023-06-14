package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.FacultyDto;
import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.entities.user.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface FacultyService {

    FacultyDto createdFaculty(Faculty req) throws Exception;

    FacultyDto updatedFaculty(Faculty req) throws Exception;

    Page<FacultyDto> getAllFaculty(Pageable pageable) throws Exception;

    String deletedFaculty(Long id);
}
