package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialDAO {
    
    Material findById(Long materialId);
    
    List<Material> findAllByClassroomId(String classroomId);
    
    List<Material> findAllByClassroomIdAndTopicId(String classroomId, Long topicId);
    
    List<Material> findAllByClassroomIdAndName(String classroomId, String name);
    
    List<Material> findAllByClassroomIdAndType(String classroomId, MaterialType materialType);
    
    Material save(Material material, Long topicId);
    
    Material update(Material material);
    
    int delete(Long materialId);
}
