package com.project.trainingteam.controller.user;

import com.project.trainingteam.dto.user.RoleDto;
import com.project.trainingteam.entities.user.Role;
import com.project.trainingteam.service.inf.user.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/role")
public class RoleController {
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleDto> createdRole(@RequestBody Role req) throws Exception {
        RoleDto createdRole = roleService.createdRole(req);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    };

    @PutMapping("//update/{id}")
    public ResponseEntity<RoleDto> updatedRole(@PathVariable("id")Long id,  @RequestBody Role req) throws Exception {
        req.setId(id);
        RoleDto updatedRole = roleService.updatedRole(req);
        return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);
    };


    @GetMapping("/all")
    public ResponseEntity<Page<RoleDto>> getAllRole(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                          @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                          @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                          @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<RoleDto> role = roleService.getAllRole(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(role, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedRole(@PathVariable("id") Long id)throws Exception{
        roleService.deletedRole(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    }

}
