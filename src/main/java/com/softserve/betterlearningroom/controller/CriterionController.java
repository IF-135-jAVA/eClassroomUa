package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.services.CriterionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/classrooms/topics/materials/criterion")
public class CriterionController {

    @Autowired
    private CriterionService criterionService;

    /**
     * get all criterion
     */
    @GetMapping
    public ResponseEntity<List<CriterionDTO>> getAll() {
        List<CriterionDTO> criterion = criterionService.findAll();
        return ResponseEntity.ok().body(criterion);
    }

    /**
     * get criterion by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Criterion> getById(@PathVariable(value = "id") final Integer criterionId) {
        Criterion criterion = criterionService.findById(criterionId);
        return ResponseEntity.ok().body(criterion);
    }

    /**
     * create criterion
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createCriterion(@Valid @RequestBody CriterionDTO criterionDTO) {
        criterionService.save(criterionDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable("id") final int id, @RequestBody final CriterionDTO criterionDTO) {
        criterionService.update(criterionDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final int id) {
        criterionService.removeById(id);
    }

}
