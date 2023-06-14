package com.project.trainingteam.repo.inf.user;

import com.project.trainingteam.entities.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    boolean existsByRoleName(String roleName);

}
