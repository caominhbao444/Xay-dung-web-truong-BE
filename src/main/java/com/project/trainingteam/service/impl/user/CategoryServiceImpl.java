package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.CategoryDto;
import com.project.trainingteam.entities.user.Category;
import com.project.trainingteam.repo.inf.user.CategoryRepo;
import com.project.trainingteam.service.inf.user.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDto createdCategory(Category category) throws Exception {
        try{
            Category savedCategory = categoryRepo.save(category);
            return modelMapper.map(savedCategory,CategoryDto.class);
        }catch (Exception e){
            throw new Exception("không thể tạo mới");
        }

    };

    @Override
    public CategoryDto updatedCategory(Category category) throws Exception {
        try{
            Category findCategory = categoryRepo.findById(category.getId()).get();
            findCategory.setCategoryName(category.getCategoryName());
            findCategory.setCategoryDesc(category.getCategoryDesc());
            Category savedCategory = categoryRepo.save(findCategory);
            return modelMapper.map(savedCategory,CategoryDto.class);
        }catch (Exception e){
            throw new Exception("không thể thay đổi");
        }
    }



    @Override
    public Page<CategoryDto> getAllCategory(Pageable pageable) throws Exception {
        try{
            Page<Category> categoryPage = categoryRepo.findAll(pageable);
            List<Category> categoryList = categoryPage.getContent();
            List<CategoryDto> categoryDtoList = categoryList.stream().map(result -> modelMapper.map(result,CategoryDto.class)).collect(Collectors.toList());
            if(categoryDtoList != null){
                return new PageImpl<>(categoryDtoList,pageable,categoryPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy Category Page");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm Category Page");
        }
    }

    @Override
    public String deletedCategory(Long id) throws Exception {
        try{
            categoryRepo.deleteById(id);
            return "DELETE Thành Công";
        }catch (Exception e){
            throw new Exception("Không thể xóa");
        }
    }
}
