package com.project.trainingteam.repo.inf.request;

import com.project.trainingteam.entities.request.ScoreBoardRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreBoardRequestRepo extends JpaRepository<ScoreBoardRequest,Long> {

    @Query("SELECT s FROM ScoreBoardRequest s WHERE s.letterId = :letterId")
    List<ScoreBoardRequest> findScoreBoardRequestByLetterId(Long letterId);
}
