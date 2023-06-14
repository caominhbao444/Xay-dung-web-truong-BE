package com.project.trainingteam.service.inf.letter;

import com.project.trainingteam.dto.letter.LetterDto;
import com.project.trainingteam.entities.letter.Letter;
import com.project.trainingteam.entities.letter.LetterType;
import com.project.trainingteam.entities.request.ScoreBoardRequest;
import com.project.trainingteam.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface LetterService {

    //User tạo đơn
    Letter createdLetter(@Param("letterTypeName") String letterTypeName, Letter letter, ScoreBoardRequest[] scoreBoardRequest, MultipartFile[] multipartFiles) throws Exception;

    //ADMIN xử lí đơn
    Letter updateLetter(Letter letter);

    //tất cả đơn
    Page<LetterDto> getAllLetter(Pageable pageable) throws Exception;

    //đơn cửa từng user
    Page<LetterDto> getAllLetterUser(Pageable pageable)throws Exception;


    //đơn của user đã nộp
    Page<LetterDto> getUserLetterByStatus0(Pageable pageable) throws Exception;

    //đơn của user đang xử lí
    Page<LetterDto> getUserLetterByStatus1(Pageable pageable) throws Exception;

    //đơn của user đã xử lí
    Page<LetterDto> getUserLetterByStatus2(Pageable pageable) throws Exception;

    ////////////////////////////////////////////////////////////////////////////

    //tất cả đơn của từng khoa
    Page<LetterDto> getAllLetterFaculty(@Param("facultyName") String facultyName, Pageable pageable)throws Exception;

    //tất cả đơn chưa xử lí của từng khoa
    Page<LetterDto> getFacultyLetterByFacultyNameAndStatus0And1(Pageable pageable) throws Exception;

    Page<LetterDto> getFacultyLetterByFacultyNameAndStatus0(Pageable pageable) throws Exception;

    Page<LetterDto> getFacultyLetterByFacultyNameAndStatus1(Pageable pageable) throws Exception;


    Page<LetterDto> getFacultyLetterByFacultyNameAndStatus2(Pageable pageable) throws  Exception;


    ///////////////////////////////////////////////////////////////////////////
    LetterDto getLetterById(Long id) throws Exception;

    String deletedLetter(Long id);

    String hello();

}
