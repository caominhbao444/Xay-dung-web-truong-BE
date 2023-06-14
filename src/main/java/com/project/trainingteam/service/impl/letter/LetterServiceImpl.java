package com.project.trainingteam.service.impl.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.dto.letter.LetterTypeDto;
import com.project.trainingteam.dto.scoreboard.ScoreBoardTypeDto;
import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.LetterType;
import com.project.trainingteam.entities.request.ScoreBoardRequest;
import com.project.trainingteam.entities.scoreboard.ScoreBoardType;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.letter.LetterRepo;
import com.project.trainingteam.repo.inf.letter.LetterTypeRepo;
import com.project.trainingteam.repo.inf.request.ScoreBoardRequestRepo;
import com.project.trainingteam.repo.inf.scoreboard.ScoreBoardTypeRepo;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.inf.file.LetterFileService;
import com.project.trainingteam.service.inf.letter.LetterService;
import com.project.trainingteam.service.inf.request.ScoreBoardRequestService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LetterServiceImpl implements LetterService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LetterRepo letterRepo;

    @Autowired
    private LetterTypeRepo letterTypeRepo;

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ScoreBoardTypeRepo scoreBoardTypeRepo;

    @Autowired
    private ScoreBoardRequestService scoreBoardRequestService;

    @Autowired
    private LetterFileService letterFileService;

    @Override
        public Letter createdLetter(String letterTypeName, Letter letter, ScoreBoardRequest[] scoreBoardRequest, MultipartFile[] multipartFiles) throws Exception {
        //xử lí đơn
//        LetterType resLetterType = letterTypeRepo.findLetterTypeByLetterTypeName(letterTypeName);
//
//        LetterTypeDto resLetterTypeDto = modelMapper.map(resLetterType,LetterTypeDto.class);
//
//        resLetterTypeDto.setLetterTypeName(letterTypeName);
//        resLetterTypeDto.setSemesterName(letter.getSemesterName());
//        resLetterTypeDto.setExamName(letter.getExamName());
//        resLetterTypeDto.setPrintedQuantity(letter.getPrintedQuantity());
//        resLetterTypeDto.setReason(letter.getReason());
//
//        Letter mappedLetter = modelMapper.map(resLetterTypeDto,Letter.class);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentName = authentication.getName();
//        User user = userRepo.findUserByUserName(currentName);
//
//        mappedLetter.setUsername(user.getUsername());
//        mappedLetter.setFullname(user.getFullname());
//        mappedLetter.setClassUser(user.getClassUser());
//        mappedLetter.setFacultyName(user.getFacultyName());
//        mappedLetter.setMajor(user.getMajor());
//        mappedLetter.setPhone(user.getPhone());
//
//
//        Letter savedLetter = letterRepo.save(mappedLetter);
        //Xử lí đơn
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        User user = userRepo.findUserByUserName(currentName);

        Letter savedLetter = letterRepo.save(letter);

        savedLetter.setLetterTypeName(letterTypeName);
        savedLetter.setUsername(user.getUsername());
        savedLetter.setFullname(user.getFullname());
        savedLetter.setClassUser(user.getClassUser());
        savedLetter.setFacultyName(user.getFacultyName());
        savedLetter.setMajor(user.getMajor());
        savedLetter.setPhone(user.getPhone());
        savedLetter.setSemesterName(letter.getSemesterName());
        savedLetter.setExamName(letter.getExamName());
        savedLetter.setPrintedQuantity(letter.getPrintedQuantity());
        savedLetter.setReason(letter.getReason());



        //Xử lí bảng điểm
        List<ScoreBoardRequest> scoreBoardRequestList = scoreBoardRequestService.createdListScoreBoardRequest(savedLetter.getId(),letterTypeName,scoreBoardRequest);

        //Xử lí file
        List<LetterFile> letterFileList = letterFileService.savedMultiLetterFile(multipartFiles, savedLetter.getId(),letterTypeName);

        return savedLetter;

    }

    @Override
    public Letter updateLetter(Letter letter) {
        Letter findLetter = letterRepo.findById(letter.getId()).get();
        findLetter.setProcessedDate(letter.getProcessedDate());
        findLetter.setResultDate(letter.getResultDate());
        findLetter.setStatus(letter.getStatus());
        findLetter.setResult(letter.getResult());
        findLetter.setNote(letter.getNote());
        findLetter.setTotal(letter.getTotal());
        Letter savedLetter = letterRepo.save(findLetter);
        return savedLetter;
    }

    @Override
    public Page<LetterDto> getAllLetter(Pageable pageable) throws Exception {
        Page<Letter> letterPage = letterRepo.findAll(pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getAllLetterUser(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Page<Letter> letterPage = letterRepo.findLetterByUserName(currentName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getUserLetterByStatus0(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Page<Letter> letterPage = letterRepo.findUserLetterByUserNameAndStatus0(currentName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null ){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());

        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getUserLetterByStatus1(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Page<Letter> letterPage = letterRepo.findUserLetterByUserNameAndStatus1(currentName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null ){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());

        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getUserLetterByStatus2(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Page<Letter> letterPage = letterRepo.findUserLetterByUserNameAndStatus2(currentName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null ){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());

        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Page<LetterDto> getAllLetterFaculty(String facultyName, Pageable pageable) throws Exception {
        Page<Letter> letterPage = letterRepo.findLetterByFacultyName(facultyName,pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getFacultyLetterByFacultyNameAndStatus0And1(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Page<Letter> letterPage = letterRepo.findLetterByFacultyNameAndStatus0And1(inforUser.get().getFacultyName(),pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getFacultyLetterByFacultyNameAndStatus0(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Page<Letter> letterPage = letterRepo.findFacultyLetterByFacultyNameAndStatus0(inforUser.get().getFacultyName(),pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getFacultyLetterByFacultyNameAndStatus1(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Page<Letter> letterPage = letterRepo.findFacultyLetterByFacultyNameAndStatus1(inforUser.get().getFacultyName(),pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public Page<LetterDto> getFacultyLetterByFacultyNameAndStatus2(Pageable pageable) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        Optional<User> inforUser = userRepo.findByUsername(currentName);
        Page<Letter> letterPage = letterRepo.findFacultyLetterByFacultyNameAndStatus2(inforUser.get().getFacultyName(),pageable);
        List<Letter> letterList = letterPage.getContent();
        List<LetterDto> letterDtoList = letterList.stream().map((result) -> modelMapper.map(result,LetterDto.class)).collect(Collectors.toList());
        if(letterDtoList != null){
            return new PageImpl<>(letterDtoList,pageable,letterPage.getTotalElements());
        }else{
            throw new Exception("Không tìm thấy List Letter");
        }
    }

    @Override
    public LetterDto getLetterById(Long id) throws Exception {
        Letter letter = letterRepo.findById(id).get();
        if(letter != null){
            return modelMapper.map(letter, LetterDto.class);
        }else{
            throw new Exception("không tìm thấy Letter");
        }
    }

    @Override
    public String deletedLetter(Long id) {
        letterRepo.deleteById(id);
        return "Delete thành Công";
    }

    @Override
    public String hello() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentName = authentication.getName();
//        User user = userRepo.findUserByUserName(currentName);
//        retu"rn user;
        String result = "Hello";
        return result;
    }
}

