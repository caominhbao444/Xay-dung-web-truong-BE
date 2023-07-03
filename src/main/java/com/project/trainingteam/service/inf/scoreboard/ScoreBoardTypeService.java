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

    ScoreBoardTypeDto updatedScoreBoardType(ScoreBoardType scoreBoardType) throws Exception;


    //Chưa cần quan tâm đến
    List<ScoreBoardType> createdListScoreBoardType(Long letterTypeId,String letterTypeName,ScoreBoardType[] scoreBoardTypeList);

    //Không sử dụng
    List<ScoreBoardType> findScoreBoardTypeByLetterTypeName(String letterTypeName);

    Page<ScoreBoardTypeDto> getAllScoreBoardTypeWithAction(Pageable pageable);

    ScoreBoardTypeDto getScoreBoardTypeById (Long id)throws Exception;

    String deletedScoreBoardType(Long id);

}
