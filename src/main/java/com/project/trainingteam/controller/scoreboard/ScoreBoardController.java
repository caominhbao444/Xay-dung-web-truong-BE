package com.project.trainingteam.controller.scoreboard;

import com.project.trainingteam.dto.letter.SemesterDto;
import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.letter.Semester;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.service.inf.scoreboard.ScoreBoardTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/score-board-type")
public class ScoreBoardController {

    private ScoreBoardTypeService scoreBoardTypeService;

    @PostMapping("/create")
    public ResponseEntity<ScoreBoardTypeDto> createdScoreBoardType(@RequestBody ScoreBoardType ScoreBoardType) throws Exception {
        ScoreBoardTypeDto createdScoreBoardType = scoreBoardTypeService.createdScoreBoardType(ScoreBoardType);
        return new ResponseEntity<>(createdScoreBoardType, HttpStatus.CREATED);
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
}
