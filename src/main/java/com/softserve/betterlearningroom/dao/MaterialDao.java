package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialDao {
    
    Material findById(Long materialId);
    
    List<Material> findAllByClassroomId(Long classroomId);
    
    List<Material> findAllByClassroomIdAndTopicId(Long classroomId, Long topicId);
    
    List<Material> findAllByClassroomIdAndName(Long classroomId, String name);
    
    List<Material> findAllByClassroomIdAndType(Long classroomId, MaterialType materialType);
    
    Material save(Material material, Long topicId);
    
    Material update(Material material);
    
    int delete(Long materialId);
}
