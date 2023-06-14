package com.project.trainingteam.repo.inf.reason;

import com.project.trainingteam.entities.reason.LetterReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterReasonRepo extends JpaRepository<LetterReason,Long> {

    boolean existsByLetterReasonName(String letterReasonName);

    @Query("SELECT r FROM LetterReason r WHERE r.letterTypeName = :letterTypeName AND r.letterReasonName = :letterReasonName")
    LetterReason findLetterReasonByLetterTypeNameAndLetterReasonName(String letterTypeName , String letterReasonName);
}
