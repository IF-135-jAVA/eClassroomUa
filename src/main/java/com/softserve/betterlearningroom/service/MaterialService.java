package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialService {

    MaterialDTO findMaterialById(Long id);

    MaterialDTO findFirstMaterialByNameAndClassroomId(String name, String classroomId);

    List<? extends MaterialDTO> findAllMaterialsByNameAndClassroomId(String name, String classroomId);

    MaterialDTO save(MaterialDTO material, Long topicId);

    MaterialDTO update(MaterialDTO material);

    void delete(MaterialDTO material);

    List<MaterialDTO> findAllMaterialsByClassroomId(String classroomId);

    List<MaterialDTO> findAllMaterialsByClassroomIdAndType(String classroomId, MaterialType materialType);

    List<MaterialDTO> findAllMaterialsByClassroomIdAndTopicId(String classroomId, Long topicId);

}