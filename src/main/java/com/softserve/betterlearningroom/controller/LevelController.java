package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.service.impl.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials/{materialId}/criterions/{criterionId}/level")
public class LevelController {

    @Autowired
    private LevelService levelService;
    /**
     * get all exist level
     */
    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAll() {
        List<LevelDTO> level = levelService.findAll();
        return ResponseEntity.ok().body(level);
    }
    /**
     * get deleted levels
     */
    @GetMapping("/deleted/{deleted}")
    public ResponseEntity<List<LevelDTO>> getAllDeleted(@PathVariable String deleted) {
        List<LevelDTO> level = levelService.findAllDeleted();
        return ResponseEntity.ok().body(level);
    }
    /**
     * get level by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<LevelDTO> getById(@PathVariable(value = "id") final Integer levelId) {
        LevelDTO levelDTO = levelService.findById(levelId);
        return ResponseEntity.ok().body(levelDTO);
    }
    /**
     * create level
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelDTO levelDTO) {
        levelService.save(levelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * update table by id
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final LevelDTO levelDTO) {
        levelService.update(levelDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final int id) {

        levelService.removeById(id);
    }

}
