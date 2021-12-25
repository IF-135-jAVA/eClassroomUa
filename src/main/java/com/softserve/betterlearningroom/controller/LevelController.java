package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.entity.Level;
import com.softserve.betterlearningroom.service.impl.LevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/topics/materials/level/")
@AllArgsConstructor
public class LevelController {

    private LevelService levelService;

    /**
     * get all level
     */
    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAll() {
        List<LevelDTO> level = levelService.findAll();
        return ResponseEntity.ok().body(level);
    }

    /**
     * get level by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Level> getById(@PathVariable(value = "id") final Integer levelId) {
        Level level = levelService.findById(levelId);
        return ResponseEntity.ok().body(level);
    }

    /**
     * create level
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelDTO levelDTO) {
        levelService.save(levelDTO);
        // TODO: 25.12.2021 return created object
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final LevelDTO levelDTO) {
        levelService.update(levelDTO);
        // TODO: 25.12.2021 return updated object
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
