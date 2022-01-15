package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialDao {
    Material readById(Long materialId);
    List<Material> readAllByClassroom(Long classroomId);
    List<Material> readAllByTopic(Long classroomId, Long topicId);
    List<Material> readAllByName(Long classroomId, String name);
    List<Material> readAllByType(Long classroomId, MaterialType materialType);
    Material create(Material material, Long topicId);
    Material update(Material material);
    int delete(Long materialId);
    int delete(Material material);
}
