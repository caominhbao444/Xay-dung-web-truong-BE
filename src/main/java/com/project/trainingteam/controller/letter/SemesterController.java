package com.project.trainingteam.controller.letter;

import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.entities.letter.Semester;
import com.project.trainingteam.service.inf.letter.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/semester")
public class SemesterController {

    private SemesterService semesterService;

    @PostMapping("/create")
    public ResponseEntity<SemesterDto> createdSemester(@RequestBody Semester req) throws Exception {
        SemesterDto createdSemester = semesterService.createdSemester(req);
        return new ResponseEntity<>(createdSemester, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<SemesterDto> updatedSemester(@PathVariable("id")Long id, @RequestBody Semester req) throws Exception {
        req.setId(id);
        SemesterDto updatedSemester = semesterService.updatedSemester(req);
        return new ResponseEntity<>(updatedSemester, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<SemesterDto>> getAllSemester(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<SemesterDto> semesterDtoPage = semesterService.getAllSemester(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(semesterDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedSemester(@PathVariable("id") Long id)throws Exception{
        semesterService.deletedSemester(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    }

}
