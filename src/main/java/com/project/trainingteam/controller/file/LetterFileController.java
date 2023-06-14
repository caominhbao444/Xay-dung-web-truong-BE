package com.project.trainingteam.controller.file;


import com.project.trainingteam.dto.file.LetterFileDto;
import com.project.trainingteam.entities.file.LetterFile;
import com.project.trainingteam.service.inf.file.LetterFileService;
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
@RequestMapping("api/letter-file")
public class LetterFileController {

    private LetterFileService letterFileService;

    @PostMapping("upload-multiple")
    public ResponseEntity<List<LetterFile>> uploadMultiFile(@RequestParam("files") MultipartFile[] multipartFiles, Long letterId, String letterTypeName)throws Exception{
        List<LetterFile> letterFileList = letterFileService.savedMultiLetterFile(multipartFiles,letterId,letterTypeName);
        return new ResponseEntity<>(letterFileList, HttpStatus.CREATED);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws Exception {
        LetterFile letterFile = letterFileService.downloadLetterFile(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(letterFile.getFileType()));
        httpHeaders.set("Content-Disposition", "attachment; filename=\"" + letterFile.getFileName() + "\"");
        ByteArrayResource content = new ByteArrayResource(letterFile.getData());
        return ResponseEntity.ok().headers(httpHeaders).body(content);
    }

    @GetMapping("/find/{letterId}")
    public ResponseEntity<List<LetterFileDto>> findLetterFileUserByUserNameAndLetterId(@PathVariable("letterId")Long letterId) throws Exception {
        List<LetterFileDto> letterFileDtoList = letterFileService.findLetterFileUserByUserNameAndLetterId(letterId);
        return new ResponseEntity<>(letterFileDtoList,HttpStatus.OK);
    }

}
