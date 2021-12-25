package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.service.impl.CriterionService;
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
@RequestMapping("/api/classrooms/topics/materials/criterion")
@AllArgsConstructor
public class CriterionController {

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
    public ResponseEntity<?> create(@Valid @RequestBody CriterionDTO criterionDTO) {
        criterionService.save(criterionDTO);
        // TODO: 25.12.2021 return created object
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final CriterionDTO criterionDTO) {
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
