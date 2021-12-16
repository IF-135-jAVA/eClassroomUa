package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialService {

    MaterialDTO getMaterialById(Long id);

    MaterialDTO getFirstMaterialByName(String name, Long classroomId);

    List<? extends MaterialDTO> getAllMaterialsByName(String name, Long classroomId);

    MaterialDTO addMaterial(MaterialDTO material, Long topicId);

    MaterialDTO updateMaterial(MaterialDTO material);

    List<? extends MaterialDTO> getMaterialsByClassroom(Long classroomId);

    List<? extends MaterialDTO> getMaterialsByType(Long classroomId, MaterialType materialType);

    List<? extends MaterialDTO> getMaterialsByTopic(Long classroomId, Long topicId);

    //TODO: getMaterialsByUser;
}