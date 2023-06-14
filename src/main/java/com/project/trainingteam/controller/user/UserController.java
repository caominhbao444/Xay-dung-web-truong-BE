package com.project.trainingteam.controller.user;

import com.project.trainingteam.dto.user.UserDto;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.service.inf.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<UserDto> createdUser(@RequestBody User req) throws Exception {
        UserDto createdUser = userService.createUser(req);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updatedUser(@PathVariable("id")Long id,  @RequestBody User req) throws Exception {
        req.setId(id);
        UserDto updatedUser = userService.updateUser(req);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> getAllUsers( @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                      @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                      @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                      @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<UserDto> users = userService.getAllUser(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(users, HttpStatus.OK);
    };

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) throws Exception {
        UserDto user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)throws Exception{
        userService.deletedUser(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

}


