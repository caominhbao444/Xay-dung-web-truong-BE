package com.project.trainingteam.repo.inf.user;

import com.project.trainingteam.entities.user.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends JpaRepository<Position,Long> {

    boolean existsByPositionName(String positionName);
}
