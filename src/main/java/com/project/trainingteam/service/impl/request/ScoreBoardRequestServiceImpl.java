package com.project.trainingteam.service.impl.request;

import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.request.ScoreBoardRequest;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.repo.inf.request.ScoreBoardRequestRepo;
import com.project.trainingteam.repo.inf.scoreboard.ScoreBoardTypeRepo;
import com.project.trainingteam.service.inf.request.ScoreBoardRequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ScoreBoardRequestServiceImpl implements ScoreBoardRequestService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ScoreBoardRequestRepo scoreBoardRequestRepo;

    @Autowired
    private ScoreBoardTypeRepo scoreBoardTypeRepo;


    @Override
    public List<ScoreBoardRequest> createdListScoreBoardRequest(Long letterId, String letterTypeName, ScoreBoardRequest[] scoreBoardRequest) {
        List<ScoreBoardType> scoreBoardTypeList = scoreBoardTypeRepo.findAllScoreBoardTypeByAction();
        List<ScoreBoardRequest> scoreBoardRequestList = new ArrayList<>();

        if (scoreBoardRequest != null && scoreBoardRequest.length > 0) {
            int length = Math.min(scoreBoardTypeList.size(), scoreBoardRequest.length);

            for (int i = 0; i < length; i++) {
                ScoreBoardType scoreBoardType = scoreBoardTypeList.get(i);
                ScoreBoardRequest scoreBoardRequestItem = scoreBoardRequest[i];

                ScoreBoardTypeDto scoreBoardTypeDto = modelMapper.map(scoreBoardType, ScoreBoardTypeDto.class);
                ScoreBoardRequest mappedScoreBoardRequest = modelMapper.map(scoreBoardTypeDto, ScoreBoardRequest.class);
                mappedScoreBoardRequest.setLetterId(letterId);
                mappedScoreBoardRequest.setLetterTypeName(letterTypeName);
                mappedScoreBoardRequest.setScoreBoardTypeQuantity(scoreBoardRequestItem.getScoreBoardTypeQuantity());
                mappedScoreBoardRequest.setScoreBoardTypePrice(scoreBoardRequestItem.getScoreBoardTypePrice());

                ScoreBoardRequest savedScoreBoardRequest = scoreBoardRequestRepo.save(mappedScoreBoardRequest);
                scoreBoardRequestList.add(savedScoreBoardRequest);
            }
        } else {
            return null;
        }
        return scoreBoardRequestList;
    }

    @Override
    public List<ScoreBoardRequest> findScoreBoardRequestByLetterId(Long letterId) {
        List<ScoreBoardRequest> scoreBoardRequestList = scoreBoardRequestRepo.findScoreBoardRequestByLetterId(letterId);
        return scoreBoardRequestList;
    };
}
