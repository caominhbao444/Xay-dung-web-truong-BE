package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.PositionDto;
import com.project.trainingteam.dto.user.RoleDto;
import com.project.trainingteam.entities.user.Position;
import com.project.trainingteam.entities.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PositionService {

    PositionDto createdPosition(Position req) throws Exception;

    PositionDto updatedPosition(Position req) throws Exception;

    Page<PositionDto> getAllPosition(Pageable pageable) throws Exception;

    String deletedPosition(Long id);
}
