package com.project.trainingteam.controller.user;

import com.project.trainingteam.dto.user.DepartCenterDto;
import com.project.trainingteam.entities.user.DepartCenter;
import com.project.trainingteam.service.inf.user.DepartCenterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/depart-center")
public class DepartCenterController {

    private DepartCenterService departCenterService;

    @PostMapping("/create")
    public ResponseEntity<DepartCenterDto> createdDepartCenter(@RequestBody DepartCenter departCenter)throws Exception{
        try{
            DepartCenterDto result = departCenterService.createdDepartCenter(departCenter);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartCenterDto> updatedDepartCenter(@PathVariable("id")Long id,@RequestBody DepartCenter departCenter)throws Exception{
        try{
            departCenter.setId(id);
            DepartCenterDto result = departCenterService.updatedDepartCenter(departCenter);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DepartCenterDto>> getAllDepartCenter (@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                     @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                     @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                     @RequestParam(name = "content", defaultValue = "id") String content) throws Exception{
        try{
            Page<DepartCenterDto> result = departCenterService.getAllDepartCenter(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedDepartCenter(@PathVariable("id")Long id){
        try{
            String result = departCenterService.deletedDepartCenter(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };
}
