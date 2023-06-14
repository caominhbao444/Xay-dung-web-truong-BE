package com.project.trainingteam.service.impl.letter;


import com.project.trainingteam.dto.letter.LetterTypeDto;
import com.project.trainingteam.entities.letter.LetterType;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.repo.inf.letter.LetterTypeRepo;
import com.project.trainingteam.repo.inf.scoreboard.ScoreBoardTypeRepo;
import com.project.trainingteam.service.inf.letter.LetterTypeService;
import com.project.trainingteam.service.inf.scoreboard.ScoreBoardTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LetterTypeTypeServiceImpl implements LetterTypeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterTypeRepo letterTypeRepo;

    @Autowired
    private ScoreBoardTypeService scoreBoardTypeService;


    @Autowired
    private ScoreBoardTypeRepo scoreBoardTypeRepo;


    @Override
    @Transactional
    public LetterTypeDto createLetterType(LetterType letterType) {
        LetterType savedLetterType = letterTypeRepo.save(letterType);

        if(savedLetterType.isCheckSemesterName() == false){
            savedLetterType.setSemesterName(null);
        }
        if(savedLetterType.isCheckExamName() == false){
            savedLetterType.setExamName(null);
        }

        if(savedLetterType.isCheckTotal() == false){
            savedLetterType.setTotal(null);
        }

        if(savedLetterType.isCheckPrintedQuantity() == false){
            savedLetterType.setPrintedQuantity(null);
        }

        savedLetterType = letterTypeRepo.save(savedLetterType);



        LetterTypeDto letterTypeDto = modelMapper.map(savedLetterType, LetterTypeDto.class);

        if(savedLetterType.isCheckScoreBoard() == true){
            List<ScoreBoardType> scoreBoardType = scoreBoardTypeRepo.findAllScoreBoardTypeByAction();
            letterTypeDto.setScoreBoardTypeList(scoreBoardType);
        }

        return letterTypeDto;
    }

    @Override
    public LetterTypeDto updatedLetterType(LetterType letterType) throws Exception {
        LetterType findLetter = letterTypeRepo.findById(letterType.getId()).get();
        if(findLetter != null){
            findLetter = letterTypeRepo.save(letterType);
        }else{
            throw new Exception("Không tìm thấy");
        }
        return modelMapper.map(findLetter,LetterTypeDto.class);
    };

    @Override
    @Transactional
    public LetterTypeDto findLetterTypeByLetterTypeName(String letterTypeName) {
        LetterType resLetterType = letterTypeRepo.findLetterTypeByLetterTypeName(letterTypeName);
        LetterTypeDto letterTypeDto = modelMapper.map(resLetterType,LetterTypeDto.class);
        return letterTypeDto;
    }

    @Override
    public Page<LetterTypeDto> getAllLetterType(Pageable pageable) throws Exception {
        Page<LetterType> letterTypePage = letterTypeRepo.findAll(pageable);
        List<LetterType> letterTypeList = letterTypePage.getContent();
        List<LetterTypeDto> letterTypeDtoList = letterTypeList.stream().map((result) -> modelMapper.map(result,LetterTypeDto.class)).collect(Collectors.toList());
        if(letterTypeDtoList != null){
            return new PageImpl<>(letterTypeDtoList,pageable,letterTypePage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List LetterType");
        }
    }

    @Override
    public String deletedLetterType(Long id) {
        letterTypeRepo.deleteById(id);
        return "DELETE Thành Công";
    }


}
