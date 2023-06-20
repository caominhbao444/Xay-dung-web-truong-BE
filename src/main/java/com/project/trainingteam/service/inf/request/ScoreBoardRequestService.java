package com.project.trainingteam.service.inf.request;

import com.project.trainingteam.entities.request.ScoreBoardRequest;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreBoardRequestService {
    List<ScoreBoardRequest> createdListScoreBoardRequest(Long letterId,String letterTypeName, ScoreBoardRequest[] scoreBoardRequests);

    List<ScoreBoardRequest> findScoreBoardRequestByLetterId(@Param("letterId") Long letterId);

}
