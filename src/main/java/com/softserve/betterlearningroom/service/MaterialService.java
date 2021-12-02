package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.model.Material;
import com.softserve.betterlearningroom.model.MaterialType;

import java.util.List;

public interface MaterialService {

    Material getMaterialById(Long id);

    Material getFirstMaterialByName(String name);

    List<? extends Material> getAllMaterialsByName(String name);

    void addMaterial(Material material);

    void updateMaterial(Material material);

    List<? extends Material> getMaterialsByClassroom(Long classroomId);

    List<? extends Material> getMaterialsByType(Long classroomId, MaterialType materialType);

    List<? extends Material> getMaterialsByTopic(Long classroomId, Long topicId);

    //TODO: getMaterialsByUser;
}