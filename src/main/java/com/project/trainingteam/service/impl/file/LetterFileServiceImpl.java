package com.project.trainingteam.service.impl.file;


import com.project.trainingteam.dto.file.LetterFileDto;
import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.file.LetterFileRepo;
import com.project.trainingteam.service.inf.file.LetterFileService;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LetterFileServiceImpl implements LetterFileService {

    @Autowired
    private LetterFileRepo letterFileRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<LetterFile> savedMultiLetterFile(MultipartFile[] multipartFiles, Long letterId, String letterTypeName) throws Exception {
        List<LetterFile> fileUpLoad = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(file -> {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String downloadUrl = "";
                LetterFile f = new LetterFile();
                f.setFileName(fileName);
                f.setFileType(file.getContentType());
                f.setData(file.getBytes());
                f.setLetterId(letterId);
                f.setLetterTypeName(letterTypeName);
                // Set the Letter object with the provided letterId
                LetterFile savedLetterFile = letterFileRepo.save(f);

                if(fileName.equals("")){
                    f.setDownloadUrl("");
                }else{
                    downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/letter-file/download/")
                            .path(String.valueOf(f.getId()))
                            .toUriString();
                    f.setDownloadUrl(downloadUrl);
                }
                savedLetterFile = letterFileRepo.save(savedLetterFile);

                fileUpLoad.add(savedLetterFile);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return fileUpLoad;
    }

    @Override
    @Transactional
    public List<LetterFileDto> findLetterFileUserByUserNameAndLetterId(Long letterId) throws Exception {
        List<LetterFile> findLetter;
        List<LetterFileDto> letterFileDtoList = new ArrayList<>();

        try {
            findLetter = letterFileRepo.findLetterFileByLetterId(letterId);

            for (LetterFile letterFile : findLetter) {
                LetterFileDto letterFileDto = new LetterFileDto();
                letterFileDto.setId(letterFile.getId());
                letterFileDto.setLetterId(letterId);
                letterFileDto.setLetterTypeName(letterFile.getLetterTypeName());
                letterFileDto.setFileName(letterFile.getFileName());
                letterFileDto.setFileType(letterFile.getFileType());
                letterFileDto.setDownloadUrl(letterFile.getDownloadUrl());
                letterFileDtoList.add(letterFileDto);
            }

        } catch (Exception e) {
            // Handle the exception
            throw new Exception(e);
        }

        return letterFileDtoList;
    }

    @Override
    public LetterFile downloadLetterFile(Long id) {
        return letterFileRepo.findById(id).get();
    }
}
