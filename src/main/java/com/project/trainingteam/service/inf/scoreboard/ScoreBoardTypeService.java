package com.project.trainingteam.service.inf.scoreboard;

import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreBoardTypeService {
    ScoreBoardTypeDto createdScoreBoardType(ScoreBoardType scoreBoardType);

    ScoreBoardTypeDto updatedScoreBoardType(ScoreBoardType scoreBoardType);

    List<ScoreBoardType> createdListScoreBoardType(Long letterTypeId,String letterTypeName,ScoreBoardType[] scoreBoardTypeList);

    List<ScoreBoardType> findScoreBoardTypeByLetterTypeName(String letterTypeName);

    Page<ScoreBoardTypeDto> getAllScoreBoardTypeWithAction(Pageable pageable);

    String deletedScoreBoardType(Long id);

}
