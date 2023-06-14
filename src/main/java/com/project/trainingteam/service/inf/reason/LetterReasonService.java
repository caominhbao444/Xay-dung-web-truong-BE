package com.project.trainingteam.service.inf.reason;

import com.project.trainingteam.dto.reason.LetterReasonDto;
import com.project.trainingteam.entities.reason.LetterReason;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface LetterReasonService {

    LetterReasonDto createdLetterReason(LetterReason letterReason) throws Exception;

    LetterReasonDto updatedLetterReason(LetterReason letterReason) throws Exception;

    Page<LetterReasonDto> getAllLetterReason(Pageable pageable) throws Exception;

    String deletedLetterReason(Long id);
}
