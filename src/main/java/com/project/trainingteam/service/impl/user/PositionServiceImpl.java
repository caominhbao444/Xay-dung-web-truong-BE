package com.project.trainingteam.service.impl.user;

import com.project.trainingteam.dto.user.PositionDto;
import com.project.trainingteam.entities.user.Position;
import com.project.trainingteam.repo.inf.user.PositionRepo;
import com.project.trainingteam.service.inf.user.PositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PositionRepo positionRepo;
    @Override
    public PositionDto createdPosition(Position req) throws Exception {
        boolean existPositionName = positionRepo.existsByPositionName(req.getPositionName());
        if(existPositionName){
            throw new Exception("Position đã tồn tại");
        }else{
            Position position = new Position();
            position.setPositionName(req.getPositionName());
            position.setPositionCode(req.getPositionCode());
            position.setPositionDesc(req.getPositionDesc());
            Position savedPosition = positionRepo.save(position);
            return modelMapper.map(savedPosition, PositionDto.class);
        }
    };

    @Override
    public PositionDto updatedPosition(Position req) throws Exception {
        Position position = positionRepo.findById(req.getId()).get();
        if(position != null){
            position.setPositionName(req.getPositionName());
            position.setPositionCode(req.getPositionCode());
            position.setPositionDesc(req.getPositionDesc());
            Position savedPosition = positionRepo.save(position);
            return modelMapper.map(savedPosition, PositionDto.class);
        }else{
            throw new Exception("Không tìm thấy Position");
        }
    };

    @Override
    public Page<PositionDto> getAllPosition(Pageable pageable) throws Exception {
        Page<Position> positionPage = positionRepo.findAll(pageable);
        List<Position> positionList = positionPage.getContent();
        List<PositionDto> positionDtoList = positionList.stream().map((result) -> modelMapper.map(result,PositionDto.class)).collect(Collectors.toList());
        if(positionDtoList != null){
            return new PageImpl<>(positionDtoList,pageable,positionPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Position");
        }
    };

    @Override
    public String deletedPosition(Long id) {
        positionRepo.deleteById(id);
        return "Delete Thành Công";
    };
}
