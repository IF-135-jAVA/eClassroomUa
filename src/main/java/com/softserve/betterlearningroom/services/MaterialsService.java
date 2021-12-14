//package com.softserve.betterlearningroom.services;
//
//import com.softserve.betterlearningroom.dto.MaterialsDTO;
//import com.softserve.betterlearningroom.entity.Materials;
//import com.softserve.betterlearningroom.repository.MaterialsDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * Works with materials.
// */
//@Service
//public class MaterialsService {
//
//    @Autowired
//    private MaterialsDAO materialsDAO;
//
//
//    public MaterialsDTO findById(Integer id) {
//
//        return toDTO(materialsDAO.readById(id));//.orElseThrow(() -> new RuntimeException("material by id is not saved")));
//    }
//
////    public MaterialsDTO findByTitle(String title) {
////        return toDTO(materialsDAO.readById(title));//.orElseThrow(() -> new RuntimeException("material didn't find")));
////    }
//
//    public void removeById(Integer id) {
//
//         materialsDAO.delete(id);
//    }
//
////    public void removeByTitle(String title) {
////
////       materialsDAO.;
////    }
//
//
//    public List<MaterialsDTO> findAll() {
//
//        return materialsDAO.readAll().stream().map(this::toDTO).collect(Collectors.toList());
//    }
//
//    public void save(MaterialsDTO materialsDTO) {
//
//        materialsDAO.create(toEntity(materialsDTO));
//    }
//
//    public void update(MaterialsDTO materialsDTO) {
//
//        materialsDAO.update(toEntity(materialsDTO));
//    }
//
//    public Materials toEntity(MaterialsDTO materialsDTO) {
//        Materials materials = new Materials();
//        materials.builder()
//                .text(materialsDTO.getText())
//                .title(materialsDTO.getTitle())
//                .startDate((java.sql.Date) materialsDTO.getStartDate())
//                .dueDate((java.sql.Date) materialsDTO.getDueDate())
//                .answer(materialsDTO.getAnswer())
//                .task(materialsDTO.getTask());
//        return materials;
//
//    }
//
//    public MaterialsDTO toDTO(Materials materials) {
//        MaterialsDTO materialsDTO = new MaterialsDTO();
//        materialsDTO.builder()
//                .text(materials.getText())
//                .title(materials.getTitle())
//                .startDate(materials.getStartDate())
//                .dueDate(materials.getDueDate())
//                .answer(materials.getAnswer())
//                .task(materials.getTask());
//        return materialsDTO;
//
//    }
//
//}
