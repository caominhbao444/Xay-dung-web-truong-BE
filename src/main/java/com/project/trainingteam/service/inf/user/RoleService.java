package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.FacultyDto;
import com.project.trainingteam.dto.user.RoleDto;
import com.project.trainingteam.entities.user.Faculty;
import com.project.trainingteam.entities.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleDto createdRole(Role req) throws Exception;

    RoleDto updatedRole(Role req) throws Exception;

    Page<RoleDto> getAllRole(Pageable pageable) throws Exception;

    String deletedRole(Long id);
}
