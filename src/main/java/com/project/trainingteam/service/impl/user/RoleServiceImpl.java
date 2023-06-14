package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.RoleDto;
import com.project.trainingteam.entities.user.Role;
import com.project.trainingteam.repo.inf.user.RoleRepo;
import com.project.trainingteam.service.inf.user.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public RoleDto createdRole(Role req) throws Exception {
        boolean existRole = roleRepo.existsByRoleName(req.getRoleName());
        if(existRole){
            throw new Exception("Role đã tồn tại");
        }else{
            Role role = new Role();
            role.setRoleName(req.getRoleName());
            role.setRoleCode(req.getRoleCode());
            role.setRoleDesc(req.getRoleDesc());
            Role savedRole = roleRepo.save(role);
            return modelMapper.map(savedRole, RoleDto.class);
        }
    };

    @Override
    public RoleDto updatedRole(Role req) throws Exception {
        Role role = roleRepo.findById(req.getId()).get();
        if(role != null){
            role.setRoleName(req.getRoleName());
            role.setRoleCode(req.getRoleCode());
            role.setRoleDesc(req.getRoleDesc());
            Role savedRole = roleRepo.save(role);
            return modelMapper.map(savedRole,RoleDto.class);
        }else{
            throw new Exception("Không tìm thấy Role");
        }
    };

    @Override
    public Page<RoleDto> getAllRole(Pageable pageable) throws Exception {
        Page<Role> rolePage = roleRepo.findAll(pageable);
        List<Role> roleList = rolePage.getContent();
        List<RoleDto> roleDtoList = roleList.stream().map((result) -> modelMapper.map(result,RoleDto.class)).collect(Collectors.toList());
        if(roleDtoList != null){
            return new PageImpl<>(roleDtoList,pageable,rolePage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Role");
        }
    };

    @Override
    public String deletedRole(Long id) {
        roleRepo.deleteById(id);
        return "Delete Thành Công";
    }
}
