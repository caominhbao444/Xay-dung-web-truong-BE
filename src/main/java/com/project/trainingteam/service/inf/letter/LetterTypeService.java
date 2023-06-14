package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.LetterTypeDto;
import com.project.trainingteam.entities.letter.LetterType;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface LetterTypeService {

    LetterTypeDto createLetterType (LetterType letterType);

    LetterTypeDto updatedLetterType(LetterType letterType) throws Exception;

    LetterTypeDto findLetterTypeByLetterTypeName(@Param("letterTypeName")String letterTypeName);

    Page<LetterTypeDto> getAllLetterType(Pageable pageable) throws Exception;

    String deletedLetterType(Long id);
}
