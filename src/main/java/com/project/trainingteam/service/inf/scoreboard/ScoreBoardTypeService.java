package com.project.trainingteam.service.inf.scoreboard;

import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.letter.Exam;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreBoardTypeService {
    ScoreBoardTypeDto createdScoreBoardType(ScoreBoardType scoreBoardType);

    List<ScoreBoardType> createdListScoreBoardType(Long letterTypeId,String letterTypeName,ScoreBoardType[] scoreBoardTypeList);

    List<ScoreBoardType> findScoreBoardTypeByLetterTypeName(String letterTypeName);

}
