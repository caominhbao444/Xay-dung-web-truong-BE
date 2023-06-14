package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.DepartCenterDto;
import com.project.trainingteam.entities.user.DepartCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DepartCenterService {

    DepartCenterDto createdDepartCenter(DepartCenter departCenter) throws Exception;

    DepartCenterDto updatedDepartCenter(DepartCenter departCenter) throws Exception;

    Page<DepartCenterDto> getAllDepartCenter(Pageable pageable) throws Exception;

    String deletedDepartCenter(Long id) throws Exception;
}
