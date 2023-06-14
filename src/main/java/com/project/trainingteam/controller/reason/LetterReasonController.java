package com.project.trainingteam.controller.reason;

import com.project.trainingteam.dto.reason.LetterReasonDto;
import com.project.trainingteam.entities.reason.LetterReason;
import com.project.trainingteam.service.inf.reason.LetterReasonService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/letter-reason")
public class LetterReasonController {

    private LetterReasonService letterReasonService;

    @PostMapping("/create")
    public ResponseEntity<LetterReasonDto> createdLetterReason(@RequestBody LetterReason letterReason) throws Exception {
        try {
            LetterReasonDto letterReasonDto = letterReasonService.createdLetterReason(letterReason);
            return new ResponseEntity<>(letterReasonDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };


    @PutMapping("/update/{id}")
    public ResponseEntity<LetterReasonDto> updatedLetterReason(@PathVariable("id") Long id, @RequestBody LetterReason letterReason) throws Exception {
        try {
            letterReason.setId(id);
            LetterReasonDto letterReasonDto = letterReasonService.updatedLetterReason(letterReason);
            return new ResponseEntity<>(letterReasonDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };

    @GetMapping("/all")
    public ResponseEntity<Page<LetterReasonDto>> getAllLetterReason(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                    @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        try {
            Page<LetterReasonDto> getAllLetterReason = letterReasonService.getAllLetterReason(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
            return new ResponseEntity<>(getAllLetterReason, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedLetterReason (@PathVariable("id") Long id) throws Exception{
        try{
            letterReasonService.deletedLetterReason(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };
}
