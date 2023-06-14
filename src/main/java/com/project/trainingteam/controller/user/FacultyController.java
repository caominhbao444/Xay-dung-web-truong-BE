package com.project.trainingteam.controller.user;


import com.project.trainingteam.dto.user.FacultyDto;
import com.project.trainingteam.entities.user.Faculty;
import com.project.trainingteam.service.inf.user.FacultyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/faculty")
public class FacultyController {

    private FacultyService facultyService;

    @PostMapping("/create")
    public ResponseEntity<FacultyDto> createdFaculty(@RequestBody Faculty req) throws Exception {
        FacultyDto createFaculty = facultyService.createdFaculty(req);
        return new ResponseEntity<>(createFaculty, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable("id")Long id,  @RequestBody Faculty req) throws Exception {
        req.setId(id);
        FacultyDto updatedFaculty = facultyService.updatedFaculty(req);
        return new ResponseEntity<>(updatedFaculty, HttpStatus.CREATED);
    };


    @GetMapping("/all")
    public ResponseEntity<Page<FacultyDto>> getAllFaculty(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                     @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                     @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<FacultyDto> faculty = facultyService.getAllFaculty(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedFaculty(@PathVariable("id") Long id)throws Exception{
        facultyService.deletedFaculty(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    }


}
