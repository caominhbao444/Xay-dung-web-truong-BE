package com.project.trainingteam.repo.inf.file;


import com.project.trainingteam.entities.file.LetterFile;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterFileRepo extends JpaRepository<LetterFile,Long> {

    @Query("SELECT f FROM LetterFile f WHERE f.letterId = :letterId")
    List<LetterFile> findLetterFileByLetterId(Long letterId);

}
