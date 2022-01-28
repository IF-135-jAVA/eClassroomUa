package com.softserve.betterlearningroom.service;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.MaterialType;

import java.util.List;

public interface MaterialService {

    MaterialDTO findMaterialById(Long id);

    MaterialDTO findFirstMaterialByNameAndClassroomId(String name, Long classroomId);

    List<? extends MaterialDTO> findAllMaterialsByNameAndClassroomId(String name, Long classroomId);

    MaterialDTO save(MaterialDTO material, Long topicId);

    MaterialDTO update(MaterialDTO material);

    void delete(MaterialDTO material);

    List<MaterialDTO> findAllMaterialsByClassroomId(Long classroomId);

    List<MaterialDTO> findAllMaterialsByClassroomIdAndType(Long classroomId, MaterialType materialType);

    List<MaterialDTO> findAllMaterialsByClassroomIdAndTopicId(Long classroomId, Long topicId);

}