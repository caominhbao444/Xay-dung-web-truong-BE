package com.project.trainingteam.repo.inf.user;

import com.project.trainingteam.entities.user.DepartCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartCenterRepo extends JpaRepository<DepartCenter,Long> {
}
