package com.project.trainingteam.controller.user;

import com.project.trainingteam.dto.user.CategoryDto;
import com.project.trainingteam.entities.user.Category;
import com.project.trainingteam.service.inf.user.CategoryService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/category")
public class CategoryController {


    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createdCategory(@RequestBody Category category) throws Exception{
        try{
            CategoryDto result = categoryService.createdCategory(category);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updatedCategory(@PathVariable("id") Long id,@RequestBody Category category) throws Exception{
        try{
            category.setId(id);
            CategoryDto result = categoryService.updatedCategory(category);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryDto>> getAllCategory (@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                             @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                             @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                             @RequestParam(name = "content", defaultValue = "id") String content) throws Exception{

        try{
            Page<CategoryDto> result = categoryService.getAllCategory(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedCategory(@PathVariable("id") Long id){
        try{
            String result = categoryService.deletedCategory(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
