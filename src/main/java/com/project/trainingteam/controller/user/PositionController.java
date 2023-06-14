package com.project.trainingteam.controller.user;


import com.project.trainingteam.dto.user.PositionDto;
import com.project.trainingteam.dto.user.RoleDto;
import com.project.trainingteam.entities.user.Position;
import com.project.trainingteam.entities.user.Role;
import com.project.trainingteam.service.inf.user.PositionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/position")
public class PositionController {

    private PositionService positionService;

    @PostMapping("/create")
    public ResponseEntity<PositionDto> createdPosition(@RequestBody Position req) throws Exception {
        PositionDto createdPosition = positionService.createdPosition(req);
        return new ResponseEntity<>(createdPosition, HttpStatus.CREATED);
    };

    @PutMapping("/update/{id}")
    public ResponseEntity<PositionDto> updatedPosition(@PathVariable("id")Long id,  @RequestBody Position req) throws Exception {
        req.setId(id);
        PositionDto updatedPosition = positionService.updatedPosition(req);
        return new ResponseEntity<>(updatedPosition, HttpStatus.CREATED);
    };

    @GetMapping("/all")
    public ResponseEntity<Page<PositionDto>> getAllPosition(@RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                    @RequestParam(name = "pageSize", defaultValue = "20") int size,
                                                    @RequestParam(name="direction",defaultValue = "ASC") String direction,
                                                    @RequestParam(name = "content", defaultValue = "id") String content) throws Exception {
        Page<PositionDto> positionDtoPage = positionService.getAllPosition(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction),content)));
        return new ResponseEntity<>(positionDtoPage, HttpStatus.OK);
    };

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletedPosition(@PathVariable("id") Long id)throws Exception{
        positionService.deletedPosition(id);
        return new ResponseEntity<>("Deleted Thành Công",HttpStatus.OK);
    }

}
