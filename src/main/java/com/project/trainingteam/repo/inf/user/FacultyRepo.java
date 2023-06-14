package com.project.trainingteam.repo.inf.user;

import com.project.trainingteam.entities.user.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty,Long> {

    boolean existsByFacultyName(String facultyName);
}
