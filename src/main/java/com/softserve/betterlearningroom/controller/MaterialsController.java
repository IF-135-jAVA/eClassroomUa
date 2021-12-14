//package com.softserve.betterlearningroom.controller;
//
//import com.softserve.betterlearningroom.dto.MaterialsDTO;
//import com.softserve.betterlearningroom.services.MaterialsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/classrooms/topic/materials")
//public class MaterialsController {
//@Autowired
//    private MaterialsService materialsService;
//
//
//    /**
//     * get all materials
//     */
//    @GetMapping
//    public ResponseEntity<List<MaterialsDTO>> getAll() {
//        List<MaterialsDTO> materials =materialsService.findAll();
//
//        return ResponseEntity.ok().body(materials);
//    }
//
//    /**
//     * get materials by id
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<MaterialsDTO> findById(@PathVariable(value = "id") final Integer Id) {
//        MaterialsDTO materials = materialsService.findById(Id);
//        return ResponseEntity.ok().body(materials);
//    }
//    /**
//     * get materials by title
//     */
////    @GetMapping("/{title}")
////    public ResponseEntity<MaterialsDTO> getByTitle(@PathVariable(value = "title") final String title) {
////        MaterialsDTO materialsDTO = materialsService.findByTitle(title);
////        return ResponseEntity.ok().body(materialsDTO);
////    }
//
//    /**
//     * create materials
//     */
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void create(@Valid @RequestBody MaterialsDTO materialsDTO) {
//        //System.out.println(materials); // Just to inspect values for demo
//        materialsService.save(materialsDTO);
//    }
//
//    /**
//     * update table by id
//     */
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") final int id, @RequestBody final MaterialsDTO materialsDTO) {
//        materialsService.update(materialsDTO);
//    }
//
//    /**
//     * delete by id
//     */
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void removeById(@PathVariable final int id) {
//        MaterialsDTO materialsDTO = materialsService.findById(id);
//        materialsService.removeById(id);
//    }
//
////    @DeleteMapping("/{title}")
////    @ResponseStatus(HttpStatus.NO_CONTENT)
////    public void removeByTitle(@PathVariable final String title) {
////
////         materialsService.removeByTitle(title);
//    }
//
//
