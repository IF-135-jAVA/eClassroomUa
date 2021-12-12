package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.MaterialsDTO;
import com.softserve.betterlearningroom.entity.Materials;
import com.softserve.betterlearningroom.services.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/topic/materials")
public class MaterialsController {

    @Autowired
    private MaterialsService materialsService;

    public MaterialsController() {
        super();
    }

    /**
     * get all materials
     */
    @GetMapping
    public List<Materials> getAll() {

        return (List<Materials>) materialsService.findAll();
    }

    /**
     * get materials by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Materials> getById(@PathVariable(value = "id") final Integer Id) {
        Materials materials = materialsService.findById(Id);
        return ResponseEntity.ok().body(materials);
    }
    /**
     * get materials by title
     */
    @GetMapping("/{title}")
    public ResponseEntity<Materials> getByTitle(@PathVariable(value = "title") final String title) {
        Materials materials = materialsService.findByTitle(title);
        return ResponseEntity.ok().body(materials);
    }

    /**
     * create materials
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody MaterialsDTO materials) {
        //System.out.println(materials); // Just to inspect values for demo
        materialsService.save(materials);
    }

    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final int id, @RequestBody final MaterialsDTO materials) {
        materialsService.save(materials);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeById(@PathVariable final int id) {
        Materials materials = materialsService.findById(id);
        materialsService.removeById(id);
    }

    @DeleteMapping("/{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeByTitle(@PathVariable final String title) {

         materialsService.removeByTitle(title);
    }

}
