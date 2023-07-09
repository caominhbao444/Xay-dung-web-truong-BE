package com.project.trainingteam.controller.scoreboard;
import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.service.inf.scoreboard.ScoreBoardTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/score-board-type")
public class ScoreBoardController{

    private ScoreBoardTypeService scoreBoardTypeService;



    @PostMapping("/create")
    public ResponseEntity<ScoreBoardTypeDto> createdScoreBoardType(@RequestBody ScoreBoardType ScoreBoardType) throws Exception {
        ScoreBoardTypeDto createdScoreBoardType = scoreBoardTypeService.createdScoreBoardType(ScoreBoardType);
        return new ResponseEntity<>(createdScoreBoardType,HttpStatus.CREATED);
    };

    @PostMapping("/create-list/{letterTypeName}")
    public ResponseEntity<List<ScoreBoardType>> createdListScoreBoardType(@PathVariable("letterTypeId")Long letterTypeId,@PathVariable("letterTypeName")String letterTypeName,@RequestPart("scoreboard") ScoreBoardType[] scoreBoardTypeList) throws Exception {
        List<ScoreBoardType> createdScoreBoardTypeList = scoreBoardTypeService.createdListScoreBoardType(letterTypeId,letterTypeName,scoreBoardTypeList);
        return new ResponseEntity<>(createdScoreBoardTypeList, HttpStatus.CREATED);
    }

//    @GetMapping("/find/{letterTypeName}")
//    public ResponseEntity<List<ScoreBoardType>> findScoreBoardTypeByLetterTypeName(@PathVariable("letterTypeName")String letterTypeName){
//        List<ScoreBoardType> findScoreBoardTypeByLetterTypeName = scoreBoardTypeService.findScoreBoardTypeByLetterTypeName(letterTypeName);
//        return new ResponseEntity<>(findScoreBoardTypeByLetterTypeName,HttpStatus.OK);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ScoreBoardTypeDto> updateScoreBoardType(@PathVariable("id") Long id, @RequestBody ScoreBoardType scoreBoardType) throws Exception {
        if (scoreBoardType == null) {
            throw new IllegalArgumentException("Invalid request body");
        }
        scoreBoardType.setId(id);
        ScoreBoardTypeDto result = scoreBoardTypeService.updatedScoreBoardType(scoreBoardType);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ScoreBoardTypeDto>> getPageScoreBoardType(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                         @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                         @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                         @RequestParam(name = "content", defaultValue = "id") String content){
        Page<ScoreBoardTypeDto> result = scoreBoardTypeService.getAllScoreBoardTypeWithAction(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<ScoreBoardTypeDto> getScoreBoardById(@PathVariable("id")Long id)throws Exception{
        try{
            ScoreBoardTypeDto result = scoreBoardTypeService.getScoreBoardTypeById(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedScoreBoardType(@PathVariable("id") Long id){
        String result = scoreBoardTypeService.deletedScoreBoardType(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
