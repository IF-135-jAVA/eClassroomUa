package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialService {

    MaterialDTO getMaterialById(Long id);

    MaterialDTO getFirstMaterialByName(String name, Long classroomId);

    List<? extends MaterialDTO> getAllMaterialsByName(String name, Long classroomId);

    MaterialDTO addMaterial(MaterialDTO material, Long topicId);

    MaterialDTO updateMaterial(MaterialDTO material);

    void deleteMaterial(MaterialDTO material);

    List<MaterialDTO> getMaterialsByClassroom(Long classroomId);

    List<MaterialDTO> getMaterialsByType(Long classroomId, MaterialType materialType);

    List<MaterialDTO> getMaterialsByTopic(Long classroomId, Long topicId);

    //TODO: getMaterialsByUser;
}