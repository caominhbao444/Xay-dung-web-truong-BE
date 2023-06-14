package com.project.trainingteam.controller.request;

import com.project.trainingteam.entities.request.ScoreBoardRequest;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.service.inf.request.ScoreBoardRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/request/score-board/")
public class ScoreBoardRequestController {

    private ScoreBoardRequestService scoreBoardRequestService;

    @PostMapping("/create/{letterId}/{letterTypeName}")
    public ResponseEntity<List<ScoreBoardRequest>> createdListScoreBoardRequest(@PathVariable("letterId")Long letterId,@PathVariable("letterTypeName")String letterTypeName, @RequestPart("scoreboard") ScoreBoardRequest[] scoreBoardRequest) throws Exception {
        List<ScoreBoardRequest> createdListScoreBoardRequest = scoreBoardRequestService.createdListScoreBoardRequest(letterId,letterTypeName,scoreBoardRequest);
        return new ResponseEntity<>(createdListScoreBoardRequest, HttpStatus.CREATED);
    }

    @GetMapping("/find/{letterId}")
    public ResponseEntity<List<ScoreBoardRequest>> findScoreBoardRequestByLetterId(@PathVariable("letterId")Long letterId){
        List<ScoreBoardRequest> scoreBoardRequestList = scoreBoardRequestService.findScoreBoardRequestByLetterId(letterId);
        return new ResponseEntity<>(scoreBoardRequestList,HttpStatus.OK);
    }
}
