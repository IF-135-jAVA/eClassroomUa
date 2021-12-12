package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.repository.CriterionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/classrooms/topics/materials/criterion")
public class CriterionController {


    @Autowired
    private CriterionDAO criterionDAO;

    public CriterionController() {
        super();
    }
    /**
     * get all criterion
     */
    @GetMapping
    public List<Criterion> getAll() {

        return (List<Criterion>) criterionDAO.findAll();
    }
    /**
     *
     * get criterion by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Criterion> getById(@PathVariable(value = "id") final Integer criterionId) {
        Criterion criterion = criterionDAO.findById(criterionId)
                .orElseThrow(() -> new NoSuchElementException("criterion not available for Id :" + criterionId));
        return ResponseEntity.ok().body(criterion);
    }
    /**
     *
     * create criterion
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCriterion(@Valid @RequestBody Criterion criterion) {
        //System.out.println(criterion); // Just to inspect values for demo
        criterionDAO.save(criterion);
    }
    /**
     * update table by id
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final int id, @RequestBody final Criterion criterion) {
        Criterion findCriterion = criterionDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Criterion not available for Id :" + id));
        criterionDAO.save(criterion);
    }
    /**
     *
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final int id) {
        Criterion criterion = criterionDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Criterion not available for Id :" + id));
        criterionDAO.removeById(id);
    }

}
