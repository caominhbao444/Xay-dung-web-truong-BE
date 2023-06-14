package com.project.trainingteam.repo.inf.letter;

import com.project.trainingteam.entities.letter.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester,Long> {

    boolean existsBySemesterName(String semesterName);
}
