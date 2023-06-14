package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.dto.letter.LetterTypeDto;
import com.project.trainingteam.entities.letter.LetterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LetterTypeRepo extends JpaRepository<LetterType,Long> {

    @Query("SELECT l FROM LetterType l WHERE l.letterTypeName = :letterTypeName")
    LetterType findLetterTypeByLetterTypeName(String letterTypeName);
}
