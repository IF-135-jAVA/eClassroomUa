package com.softserve.betterlearningroom.controller;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.service.impl.CriterionServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/classrooms/{classroomId}/topics/{topicId}/materials/{materialId}/criterions")

public class CriterionController {

    @Autowired
    private CriterionServiceImpl criterionService;

   @PreAuthorize("h)
    @GetMapping
    public ResponseEntity<List<CriterionDTO>> getAll() {
        List<CriterionDTO> criterion = criterionService.findAll();
        return ResponseEntity.ok().body(criterion);
    }

    /**
     * get all criterion by material id
     */
    @GetMapping("/byMaterialId/{byMaterialId}")
    public ResponseEntity<List<CriterionDTO>> getAllByMaterialId(@PathVariable(value = "byMaterialId") final Long materialId) {
        List<CriterionDTO> criterion = criterionService.findAllByMaterialId(materialId);
        return ResponseEntity.ok().body(criterion);
    }

    /**
     * get criterion by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<CriterionDTO> getById(@PathVariable(value = "id") final Long criterionId) {
        CriterionDTO criterionDTO = criterionService.findById(criterionId);
        return ResponseEntity.ok().body(criterionDTO);
    }

    /**
     * create criterion
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CriterionDTO> create(@Valid @RequestBody CriterionDTO criterionDTO) {

        return new ResponseEntity<>(criterionService.save(criterionDTO), HttpStatus.CREATED);
    }

    /**
     * update table by id
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody final CriterionDTO criterionDTO) {

        return new ResponseEntity<>(criterionService.update(criterionDTO), HttpStatus.ACCEPTED);
    }

    /**
     * delete by id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {

        criterionService.removeById(id);
    }

}
