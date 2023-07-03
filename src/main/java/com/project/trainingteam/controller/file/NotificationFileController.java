package com.project.trainingteam.controller.file;

import com.project.trainingteam.dto.file.NotificationFileDto;
import com.project.trainingteam.entities.file.NotificationFile;
import com.project.trainingteam.service.inf.file.NotificationFileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/notification-file")
public class NotificationFileController {

    private NotificationFileService notificationFileService;

    @PostMapping("/upload/{notificationId}/{title}")
    public ResponseEntity<List<NotificationFile>> uploadNotificationFile(@RequestParam("files") MultipartFile[] multipartFiles, @PathVariable("notificationId") Long notificationId,@PathVariable("title") String notificationTitle)throws Exception{
        try{
            List<NotificationFile> result = notificationFileService.savedMultiNewsFile(multipartFiles, notificationId, notificationTitle);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/find/{notificationId}")
    public ResponseEntity<List<NotificationFileDto>> getNotificationFileByNotificationId(@PathVariable("notificationId") Long notificationId)throws Exception{
//        try{
//            List<NotificationFile> result = notificationFileService.getNotificationFileByNotificationId(notificationId);
//            return new ResponseEntity<>(result,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        List<NotificationFileDto> result = notificationFileService.getNotificationFileByNotificationId(notificationId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadNotificationFile(@PathVariable Long id) throws Exception {
        NotificationFile notificationFile = notificationFileService.downloadNotificationFile(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(notificationFile.getFileType()));
        httpHeaders.set("Content-Disposition", "attachment; filename=\"" + notificationFile.getFileName() + "\"");
        ByteArrayResource content = new ByteArrayResource(notificationFile.getData());
        return ResponseEntity.ok().headers(httpHeaders).body(content);
    }
}
