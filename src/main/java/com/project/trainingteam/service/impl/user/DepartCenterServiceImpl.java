package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.DepartCenterDto;
import com.project.trainingteam.entities.user.DepartCenter;
import com.project.trainingteam.repo.inf.user.DepartCenterRepo;
import com.project.trainingteam.service.inf.user.DepartCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartCenterServiceImpl implements DepartCenterService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartCenterRepo departCenterRepo;
    @Override
    public DepartCenterDto createdDepartCenter(DepartCenter departCenter) throws Exception {
        try{
            DepartCenter savedDepartCenter = departCenterRepo.save(departCenter);
            return modelMapper.map(savedDepartCenter,DepartCenterDto.class);
        }catch (Exception e){
            throw new Exception("Không thể tạo");
        }
    };

    @Override
    public DepartCenterDto updatedDepartCenter(DepartCenter departCenter) throws Exception {
        try{
            DepartCenter findDepartCenter = departCenterRepo.findById(departCenter.getId()).get();
            findDepartCenter.setDepartCenterName(departCenter.getDepartCenterName());
            findDepartCenter.setDepartCenterCode(departCenter.getDepartCenterCode());
            findDepartCenter.setDepartCenterDesc(departCenter.getDepartCenterDesc());
            DepartCenter savedDepartCenter = departCenterRepo.save(findDepartCenter);
            return modelMapper.map(savedDepartCenter,DepartCenterDto.class);
        }catch (Exception e){
            throw new Exception("Không thể tạo mới");
        }
    };

    @Override
    public Page<DepartCenterDto> getAllDepartCenter(Pageable pageable) throws Exception {

        try{
            Page<DepartCenter> departCenterPage = departCenterRepo.findAll(pageable);
            List<DepartCenter> departCenterList = departCenterPage.getContent();
            List<DepartCenterDto> departCenterDtoList = departCenterList.stream().map(result -> modelMapper.map(result,DepartCenterDto.class)).collect(Collectors.toList());
            if(departCenterDtoList != null){
                return new PageImpl<>(departCenterDtoList,pageable,departCenterPage.getTotalElements());
            }else{
                throw new Exception("Không tìm thấy DepartCenter Page");
            }
        }catch (Exception e){
            throw new Exception("Không thể tìm DepartCenter Page");
        }
    }

    @Override
    public String deletedDepartCenter(Long id) throws Exception {
        try{
            departCenterRepo.deleteById(id);
            return "DELETE Thành Công";
        }catch (Exception e){
            throw new Exception("Không thể xóa");
        }
    }
}
