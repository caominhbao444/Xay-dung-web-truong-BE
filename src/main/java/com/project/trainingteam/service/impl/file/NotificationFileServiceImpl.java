package com.project.trainingteam.service.impl.file;

import com.project.trainingteam.dto.file.NotificationFileDto;
import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.entities.file.NotificationFile;
import com.project.trainingteam.repo.inf.file.NotificationFileRepo;
import com.project.trainingteam.service.inf.file.NotificationFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationFileServiceImpl  implements NotificationFileService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationFileRepo notificationFileRepo;

    @Override
    @Transactional
    public List<NotificationFile> savedMultiNewsFile(MultipartFile[] multipartFiles, Long notificationId, String notificationTitle) throws Exception {
        List<NotificationFile> fileUpLoad = new ArrayList<>();
        Arrays.stream(multipartFiles).forEach(file -> {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String downloadUrl = "";
                NotificationFile f = new NotificationFile();
                f.setFileName(fileName);
                f.setFileType(file.getContentType());
                f.setData(file.getBytes());
                f.setNotificationId(notificationId);
                f.setNotificationTitle(notificationTitle);
                NotificationFile savedNotificationFile = notificationFileRepo.save(f);

                if(fileName.equals("")){
                    f.setDownloadUrl("");
                }else{
                    downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/notification-file/download/")
                            .path(String.valueOf(f.getId()))
                            .toUriString();
                    f.setDownloadUrl(downloadUrl);
                }
                savedNotificationFile = notificationFileRepo.save(savedNotificationFile);

                fileUpLoad.add(savedNotificationFile);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return fileUpLoad;
    }

    @Override
    public NotificationFile downloadNotificationFile(Long id) {
        return notificationFileRepo.findById(id).get();
    }

    @Override
    @Transactional
    public List<NotificationFileDto> getNotificationFileByNotificationId(Long notificationId) throws Exception {
        List<NotificationFile> notificationFileList = notificationFileRepo.getNotificationFileByNotificationId(notificationId);
        return notificationFileList.stream().map(result -> modelMapper.map(result,NotificationFileDto.class)).collect(Collectors.toList());
    }
}
