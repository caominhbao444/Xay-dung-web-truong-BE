package com.project.trainingteam.service.inf.user;

import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    UserDto createUser(User req) throws Exception;

    UserDto updateUser(User req) throws Exception;

    Page<UserDto> getAllUser(Pageable pageable) throws Exception;

    UserDto getUserById(Long id)throws Exception;

    String deletedUser(Long id);




}
