package com.project.trainingteam.service.impl.reason;

import com.project.trainingteam.dto.reason.LetterReasonDto;
import com.project.trainingteam.entities.reason.LetterReason;
import com.project.trainingteam.repo.inf.reason.LetterReasonRepo;
import com.project.trainingteam.service.inf.reason.LetterReasonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterReasonServiceImpl implements LetterReasonService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterReasonRepo letterReasonRepo;

    @Override
    public LetterReasonDto createdLetterReason(LetterReason letterReason) throws Exception {
        try{
            LetterReason checkLetterReason = letterReasonRepo.findLetterReasonByLetterTypeNameAndLetterReasonName(letterReason.getLetterTypeName(),letterReason.getLetterReasonName());
            if(checkLetterReason != null ){
                throw new Exception("Letter Reason đã tồn tại");
            }else{
                LetterReason createLetterReason = letterReasonRepo.save(letterReason);
                return modelMapper.map(createLetterReason,LetterReasonDto.class);
            }
        }catch (Exception e){
            throw new Exception("Không thể tạo mới");
        }
    };

    @Override
    public LetterReasonDto updatedLetterReason(LetterReason letterReason) throws Exception {
        LetterReason checkLetterReason = letterReasonRepo.findById(letterReason.getId()).get();
        if(checkLetterReason != null){
            checkLetterReason.setLetterReasonName(letterReason.getLetterReasonName());
            checkLetterReason.setLetterReasonDesc(letterReason.getLetterReasonDesc());
            LetterReason savedLetterReason = letterReasonRepo.save(checkLetterReason);
            return modelMapper.map(savedLetterReason,LetterReasonDto.class);
        }else{
            throw new Exception("Không tìm thấy Letter Reason");
        }
    }

    @Override
    public Page<LetterReasonDto> getAllLetterReason(Pageable pageable) throws Exception {
        Page<LetterReason> letterReasonPage = letterReasonRepo.findAll(pageable);
        List<LetterReason> letterReasonList = letterReasonPage.getContent();
        List<LetterReasonDto> letterReasonDtoList = letterReasonList.stream().map(result -> modelMapper.map(result, LetterReasonDto.class)).collect(Collectors.toList());
        if(letterReasonDtoList != null ){
            return new PageImpl<>(letterReasonDtoList,pageable,letterReasonPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy Letter Reason");
        }
    }

    @Override
    public String deletedLetterReason(Long id) {
        letterReasonRepo.deleteById(id);
        return "DELETE Thành Công";
    }
}
