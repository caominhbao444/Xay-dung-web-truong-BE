package com.project.trainingteam.repo.inf.scoreboard;

import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreBoardTypeRepo extends JpaRepository<ScoreBoardType,Long> {


//    @Query("SELECT s FROM ScoreBoardType s WHERE s.letterTypeName = :letterTypeName ")
//    List<ScoreBoardType> findScoreBoardTypeByLetterTypeName(String letterTypeName);

    @Query("SELECT s FROM ScoreBoardType s WHERE s.action = true")
    List<ScoreBoardType> findAllScoreBoardTypeByAction();
}
