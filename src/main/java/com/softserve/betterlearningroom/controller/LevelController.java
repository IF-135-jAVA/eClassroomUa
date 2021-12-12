package com.softserve.betterlearningroom.controller;


import com.softserve.betterlearningroom.entity.Level;
import com.softserve.betterlearningroom.repository.LevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/classrooms/topics/materials/criterion/level")
public class LevelController {

    @Autowired
    private LevelDAO levelDAO;

    public LevelController() {
        super();
    }
    /**
     * get all level
     */
    @GetMapping
    public List<Level> getAll() {

        return (List<Level>) levelDAO.findAll();
    }
    /**
     *
     * get level by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable(value = "id") final Integer levelId) {
        Level level = levelDAO.findById(levelId)
                .orElseThrow(() -> new NoSuchElementException("level not available for Id :" + levelId));
        return ResponseEntity.ok().body(level);
    }
    /**
     *
     * create level
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLevel(@Valid @RequestBody Level level) {
        //System.out.println(level); // Just to inspect values for demo
        levelDAO.save(level);
    }
    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevel(@PathVariable("id") final int id, @RequestBody final Level level) {
        Level findLevel = levelDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Level not available for Id :" + id));
        levelDAO.save(level);
    }
    /**
     *
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@PathVariable final int id) {
        Level level = levelDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Level not available for Id :" + id));
        levelDAO.removeById(id);
    }

}
