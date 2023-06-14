package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.entities.letter.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<Exam,Long> {

    boolean existsByExamName(String examName);

}
