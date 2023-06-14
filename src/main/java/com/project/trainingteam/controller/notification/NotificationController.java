package com.project.trainingteam.controller.notification;

import com.project.trainingteam.dto.notification.DashBoardUnSeenNotificationDto;
import com.project.trainingteam.dto.notification.NotificationDto;
import com.project.trainingteam.dto.notification.PageUnSeenNotificationDto;
import com.project.trainingteam.dto.notification.SearchRequestNotificationDto;
import com.project.trainingteam.entities.notification.Notification;
import com.project.trainingteam.service.inf.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/notification")
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping("/create")
    public ResponseEntity<NotificationDto> createdNotification(@RequestPart("notification") Notification notification, @RequestPart(value = "files",required = false)MultipartFile[] multipartFiles)throws Exception{
        try{
            NotificationDto result = notificationService.createdNotification(notification,multipartFiles);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    };

    @PostMapping("/seen/{notificationId}")
    public ResponseEntity<String> seenByUser(@PathVariable("notificationId")Long notificationId)throws Exception{
        try{
            String result = notificationService.seenByUser(notificationId);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NotificationDto> updatedNotification(@PathVariable("id") Long id ,@RequestBody Notification notification)throws Exception{
        try{
            notification.setId(id);
            NotificationDto result = notificationService.updatedNotification(notification);
            return new ResponseEntity<>(result,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable("id")Long id,@RequestBody Notification notification)throws Exception{
        try{
            notification.setId(id);
            NotificationDto result = notificationService.getNotificationById(notification);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<NotificationDto>> getAllNotification(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                    @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                    @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                    @RequestParam(name = "content", defaultValue = "id") String content) throws Exception{
        try {
            Page<NotificationDto> result = notificationService.getAllNotification(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dashboard/new")
    public ResponseEntity<List<NotificationDto>> getNewNotificationList()throws Exception{
        try{
            List<NotificationDto> result = notificationService.getNewNotificationList();
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dashboard/important")
    public ResponseEntity<List<NotificationDto>> getImportantNotificationList()throws Exception{
        try{
            List<NotificationDto> result = notificationService.getImportantNotificationList();
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/dashboard/unseen")
    public ResponseEntity<List<DashBoardUnSeenNotificationDto>> getNotificationUnSeenByCategoryName()throws Exception{
        try{
            List<DashBoardUnSeenNotificationDto> result = notificationService.getUnseenCountNotificationByCategoryName();
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/sidebar/faculty/{facultyName}")
    public ResponseEntity<Page<NotificationDto>> getAllNotificationByFacultyName(@PathVariable("facultyName") String facultyName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                 @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                 @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                 @RequestParam(name = "content", defaultValue = "id") String content
    ) throws Exception {
        try {
            Page<NotificationDto> result = notificationService.getAllNotificationByFacultyName(facultyName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sidebar/depart-center/{departCenterName}")
    public ResponseEntity<Page<NotificationDto>> getAllNotificationByDepartCenterName(@PathVariable("departCenterName") String departCenterName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                      @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                      @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                      @RequestParam(name = "content", defaultValue = "id") String content
    ) throws Exception {
        try {
            Page<NotificationDto> result = notificationService.getAllNotificationByDepartCenterName(departCenterName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/sidebar/category/{categoryName}")
    public ResponseEntity<Page<NotificationDto>> getAllNotificationByCategoryName(@PathVariable("categoryName") String categoryName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                      @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                      @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                      @RequestParam(name = "content", defaultValue = "id") String content
    ) throws Exception {
        try {
            Page<NotificationDto> result = notificationService.getAllNotificationByCategoryName(categoryName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page/category/{categoryName}")
    public ResponseEntity<Page<PageUnSeenNotificationDto>> getUnSeenNotificationByCategoryName(@PathVariable("categoryName") String categoryName, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                               @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                               @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                               @RequestParam(name = "content", defaultValue = "id") String content
    ) throws Exception {
        try {
            Page<PageUnSeenNotificationDto> result = notificationService.getUnSeenNotificationByCategoryName(categoryName, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page/important")
    public ResponseEntity<Page<NotificationDto>> getAllImportantNotification(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                             @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                             @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                             @RequestParam(name = "content", defaultValue = "id") String content)throws Exception{
        try{
            Page<NotificationDto> result = notificationService.getAllImportantNotification(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page/new")
    public ResponseEntity<Page<NotificationDto>> getAllNewNotification(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                             @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                             @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                                             @RequestParam(name = "content", defaultValue = "id") String content)throws Exception{
        try{
            Page<NotificationDto> result = notificationService.getAllNewNotification(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<Page<NotificationDto>> getSearchNotification(@RequestBody SearchRequestNotificationDto searchRequestNotificationDto,
                                                                       @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                       @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                       @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                       @RequestParam(name = "content", defaultValue = "id") String content

    ) throws Exception {
        Page<NotificationDto> result = notificationService.searchNotification(searchRequestNotificationDto, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/faculty/{facultyName}")
    public ResponseEntity<Page<NotificationDto>> getSearchNotificationByFacultyName(@PathVariable("facultyName") String facultyName,
                                                                                    @RequestBody SearchRequestNotificationDto searchRequestNotificationDto,
                                                                                    @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                    @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                                    @RequestParam(name = "content", defaultValue = "id") String content

    )  throws Exception {
        Page<NotificationDto> result = notificationService.searchNotificationByFacultyName(facultyName, searchRequestNotificationDto,PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/search/depart-center/{departCenterName}")
    public ResponseEntity<Page<NotificationDto>> getSearchNotificationByDepartCenterName(
            @PathVariable("departCenterName") String departCenterName,
            @RequestBody SearchRequestNotificationDto searchRequestNotificationDto,
            @RequestParam(name = "pageNumber", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "content", defaultValue = "id") String content

    )  throws Exception {
        Page<NotificationDto> result = notificationService.searchNotificationByDepartCenterName(departCenterName, searchRequestNotificationDto, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search/category/{categoryName}")
    public ResponseEntity<Page<NotificationDto>> getSearchNotificationByCategoryName(
            @PathVariable("categoryName") String categoryName,
            @RequestBody SearchRequestNotificationDto searchRequestNotificationDto,
            @RequestParam(name = "pageNumber", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "20") int size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "content", defaultValue = "id") String content

    )  throws Exception {
        Page<NotificationDto> result = notificationService.searchNotificationByCategoryName(categoryName, searchRequestNotificationDto, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), content)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedNotification(@PathVariable("id")Long id)throws Exception{
        try{
            String result = notificationService.deletedNotification(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/test/{facultyName}")
    public ResponseEntity<List<Notification>> testEndpoint(@PathVariable("facultyName") String facultyName,
            @RequestBody SearchRequestNotificationDto searchRequestNotificationDto) {
        List<Notification> result = notificationService.test(facultyName,searchRequestNotificationDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
