package com.project.trainingteam.controller.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.request.ScoreBoardRequest;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.service.inf.letter.LetterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/letter")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @PostMapping("/create/{letterTypeName}")
    public ResponseEntity<Letter> createdLetterType(@PathVariable("letterTypeName") String letterTypeName, @RequestPart(value = "letter", required = false) Letter letter, @RequestPart(value = "scoreboard",required = false) ScoreBoardRequest[] scoreBoardRequest,@RequestPart(value = "files", required = false) MultipartFile[] multipartFiles) throws Exception {
        Letter createdLetterType = letterService.createdLetter(letterTypeName,letter,scoreBoardRequest,multipartFiles);
        return new ResponseEntity<>(createdLetterType, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<Letter> updatedLetter(@PathVariable("id")Long id , @RequestBody Letter letter){
        letter.setId(id);
        Letter updatedLetter = letterService.updateLetter(letter);
        return new ResponseEntity<>(updatedLetter,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<LetterDto>> getAllLetter(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                        @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                        @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                        @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDtoPage = letterService.getAllLetter(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDtoPage, HttpStatus.OK);
    };

    @GetMapping("/current")
    public ResponseEntity<Page<LetterDto>> getAllLetterUser(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                            @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                            @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDtoPage = letterService.getAllLetterUser(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDtoPage, HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<LetterDto> getLetterById(@PathVariable("id") Long id) throws Exception {
        LetterDto letterDto = letterService.getLetterById(id);
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-da-nop")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus0(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus0(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-dang-xu-li")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/don-da-xu-li")
    public ResponseEntity<Page<LetterDto>> getUserLetterByStatus2(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getUserLetterByStatus2(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    //////////////////////////////////////////////////////////////////////
    @GetMapping("/faculty/{facultyName}")
    public ResponseEntity<Page<LetterDto>> getLetterByFacultyName(@PathVariable("facultyName") String facultyName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                  @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                  @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getAllLetterFaculty(facultyName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);

    };

    @GetMapping("/faculty/don-chua-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus0And1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                       @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                       @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus0And1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-da-nop")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus0(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                   @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus0(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-dang-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus1(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                   @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus1(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };

    @GetMapping("/faculty/don-da-xu-li")
    public ResponseEntity<Page<LetterDto>> getFacultyLetterByFacultyNameAndStatus2(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                   @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<LetterDto> letterDto = letterService.getFacultyLetterByFacultyNameAndStatus2(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(letterDto, HttpStatus.OK);
    };


    /////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedLetter(@PathVariable("id") Long id) throws Exception {
        letterService.deletedLetter(id);
        return new ResponseEntity<>("Deleted Thành Công", HttpStatus.OK);
    };

    @GetMapping("/hello")
    public ResponseEntity<String> Hello(){
//        User result = letterService.hello();
////        return result;
//        return new ResponseEntity<>(result,HttpStatus.OK);
        String result = letterService.hello();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
