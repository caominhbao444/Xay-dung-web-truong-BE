package com.project.trainingteam.service.impl.scoreboard;

import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;

import com.project.trainingteam.entities.scoreboard.ScoreBoardType;

import com.project.trainingteam.repo.inf.scoreboard.ScoreBoardTypeRepo;

import com.project.trainingteam.service.inf.scoreboard.ScoreBoardTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreBoardTypeServiceImpl implements ScoreBoardTypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ScoreBoardTypeRepo scoreBoardTypeRepo;

    @Override
    public ScoreBoardTypeDto createdScoreBoardType(ScoreBoardType scoreBoardType) {
        ScoreBoardType savedScoreBoardType = scoreBoardTypeRepo.save(scoreBoardType);
        ScoreBoardTypeDto scoreBoardTypeDto = modelMapper.map(savedScoreBoardType,ScoreBoardTypeDto.class);
        return scoreBoardTypeDto;
    }

    @Override
    public ScoreBoardTypeDto updatedScoreBoardType(ScoreBoardType scoreBoardType) {
        ScoreBoardType findScoreBoardType = scoreBoardTypeRepo.findById(scoreBoardType.getId()).get();
        findScoreBoardType.setScoreBoardTypeName(scoreBoardType.getScoreBoardTypeName());
        findScoreBoardType.setScoreBoardTypePrice(scoreBoardType.getScoreBoardTypePrice());
        findScoreBoardType.setScoreBoardTypeDesc(scoreBoardType.getScoreBoardTypeDesc());
        ScoreBoardType savedScoreBoardType = scoreBoardTypeRepo.save(findScoreBoardType);
        return modelMapper.map(savedScoreBoardType,ScoreBoardTypeDto.class);
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

    @Override
    public Page<ScoreBoardTypeDto> getAllScoreBoardTypeWithAction(Pageable pageable) {
        Page<ScoreBoardType> scoreBoardTypePage = scoreBoardTypeRepo.pageFindAllScoreBoardTypeAction(pageable);
        List<ScoreBoardType> scoreBoardTypeList = scoreBoardTypePage.getContent();
        List<ScoreBoardTypeDto> scoreBoardTypeDtoList = scoreBoardTypeList.stream().map(result -> modelMapper.map(result,ScoreBoardTypeDto.class)).collect(Collectors.toList());
        return new PageImpl<>(scoreBoardTypeDtoList,pageable,scoreBoardTypePage.getTotalElements());
    }

    @Override
    public String deletedScoreBoardType(Long id) {
        scoreBoardTypeRepo.deleteById(id);
        return "DELETE Thành Công";
    }
}
