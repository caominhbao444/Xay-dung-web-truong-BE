package com.project.trainingteam.service.impl.scoreboard;

import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.letter.Exam;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.repo.inf.letter.ExamRepo;
import com.project.trainingteam.repo.inf.scoreboard.ScoreBoardTypeRepo;
import com.project.trainingteam.service.inf.letter.ExamService;
import com.project.trainingteam.service.inf.scoreboard.ScoreBoardTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreBoardTypeServiceImpl implements ScoreBoardTypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ScoreBoardTypeRepo scoreBoardTypeRepo;

    @Autowired
    private ExamRepo examRepo;

    @Override
    public ScoreBoardTypeDto createdScoreBoardType(ScoreBoardType scoreBoardType) {
        ScoreBoardType savedScoreBoardType = scoreBoardTypeRepo.save(scoreBoardType);
        ScoreBoardTypeDto scoreBoardTypeDto = modelMapper.map(savedScoreBoardType,ScoreBoardTypeDto.class);
        return scoreBoardTypeDto;
    }

    @Override
    public List<ScoreBoardType> createdListScoreBoardType(Long letterTypeId,String letterTypeName,ScoreBoardType[] scoreBoardTypeList) {
        List<ScoreBoardType> newScoreBoardTypeList = new ArrayList<>();

        for (ScoreBoardType scoreBoardType : scoreBoardTypeList) {
//            scoreBoardType.setLetterTypeId(letterTypeId);
//            scoreBoardType.setLetterTypeName(letterTypeName);
            ScoreBoardType savedScoreBoardType = scoreBoardTypeRepo.save(scoreBoardType);
            newScoreBoardTypeList.add(savedScoreBoardType);
        }
        return newScoreBoardTypeList;
    }

    @Override
    public List<ScoreBoardType> findScoreBoardTypeByLetterTypeName(String letterTypeName) {
//        List<ScoreBoardType> scoreBoardTypeList = scoreBoardTypeRepo.findScoreBoardTypeByLetterTypeName(letterTypeName);
//        return scoreBoardTypeList;
        return null;
    }
}
