package com.project.trainingteam.controller.letter;

import com.project.trainingteam.dto.letter.ExamDto;
import com.project.trainingteam.entities.letter.Exam;
import com.project.trainingteam.service.inf.letter.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/exam")
public class ExamController {

    private ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<ExamDto> createdExam(@RequestBody Exam req) throws Exception {
        ExamDto createdExam = examService.createdExam(req);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    };

    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> updatedExam(@PathVariable("id")Long id, @RequestBody Exam req) throws Exception {
        req.setId(id);
        ExamDto updatedExam = examService.updatedExam(req);
        return new ResponseEntity<>(updatedExam, HttpStatus.CREATED);
    };


    @GetMapping("/all")
    public ResponseEntity<Page<ExamDto>> getAllGroupLetter(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<ExamDto> ExamDtoPage = examService.getAllExam(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(ExamDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedGroupLetter(@PathVariable("id") Long id)throws Exception{
        examService.deletedExam(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    };
}
