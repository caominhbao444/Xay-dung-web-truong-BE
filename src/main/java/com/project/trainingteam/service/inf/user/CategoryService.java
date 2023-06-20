package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.CategoryDto;
import com.project.trainingteam.entities.user.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    CategoryDto createdCategory(Category category) throws Exception;

    CategoryDto updatedCategory(Category category) throws Exception;

    Page<CategoryDto> getAllCategory(Pageable pageable)throws Exception;

    String deletedCategory(Long id)throws Exception;
}
