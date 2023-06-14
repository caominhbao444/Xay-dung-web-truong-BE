package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.inf.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(User req) throws Exception {

        User user = new User();

        boolean existUser = userRepo.existsByUsername(req.getUsername());

        if(existUser){
            throw new Exception("Tài Khoản Đã Tồn Tại !!!");
        }

        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setFullname(req.getFullname());
        user.setClassUser(req.getClassUser());
        user.setFacultyName(req.getFacultyName());
        user.setMajor(req.getMajor());
        user.setGender(req.getGender());
        user.setAvatar(req.getAvatar());
        user.setNationality(req.getNationality());
        user.setHometown(req.getHometown());
        user.setAddress(req.getAddress());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setDepartCenterName(req.getDepartCenterName());
        user.setRoleName(req.getRoleName());
        user.setPositionName(req.getPositionName());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    };

    @Override
    public UserDto updateUser(User req) throws Exception {
        User user = userRepo.findById(req.getId()).get();
        if(user != null){
            user.setUsername(user.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setFullname(req.getFullname());
            user.setClassUser(req.getClassUser());
            user.setFacultyName(req.getFacultyName());
            user.setGender(req.getGender());
            user.setAvatar(req.getAvatar());
            user.setNationality(req.getNationality());
            user.setHometown(req.getHometown());
            user.setAddress(req.getAddress());
            user.setEmail(req.getEmail());
            user.setPhone(req.getPhone());
            user.setDepartCenterName(req.getDepartCenterName());
            user.setRoleName(user.getRoleName());
            user.setPositionName(req.getPositionName());
            User savedUser = userRepo.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        }else{
            throw new Exception("Không tìm thấy User");
        }
    };

    @Override
    public Page<UserDto> getAllUser(Pageable pageable) throws Exception {

        Page<User> userPage = userRepo.findAll(pageable);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtoList = userList.stream().map((result) -> modelMapper.map(result,UserDto.class)).collect(Collectors.toList());
        if(userDtoList != null){
            return new PageImpl<>(userDtoList,pageable,userPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Users");
        }
    };

    @Override
    public UserDto getUserById(Long id) throws Exception {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser != null){
            return modelMapper.map(optionalUser,UserDto.class);
        }else{
            throw new Exception("không tìm thấy User");
        }
    };

    public String deletedUser(Long id ) {
        userRepo.deleteById(id);
        return "Delete Thành Công";
    };
}
