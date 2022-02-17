package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.MaterialDAO;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.mapper.MaterialMapper;
import com.softserve.betterlearningroom.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialDAO materialDao;

    @Override
    public MaterialDTO findMaterialById(Long id) {
        return MaterialMapper.materialToMaterialDTO(materialDao.findById(id));
    }

    @Override
    public MaterialDTO findFirstMaterialByNameAndClassroomId(String name, Long classroomId) {
        return MaterialMapper.materialToMaterialDTO(materialDao.findAllByClassroomIdAndName(classroomId, name).stream().findFirst().orElse(null));
    }

    @Override
    public List<MaterialDTO> findAllMaterialsByNameAndClassroomId(String name, Long classroomId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.findAllByClassroomIdAndName(classroomId, name).forEach(m -> materials.add(MaterialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> findAllMaterialsByClassroomId(Long classroomId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.findAllByClassroomId(classroomId).forEach(m -> materials.add(MaterialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> findAllMaterialsByClassroomIdAndType(Long classroomId, MaterialType materialType) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.findAllByClassroomIdAndType(classroomId, materialType).forEach(m -> materials.add(MaterialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> findAllMaterialsByClassroomIdAndTopicId(Long classroomId, Long topicId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.findAllByClassroomIdAndTopicId(classroomId, topicId).forEach(m -> materials.add(MaterialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public MaterialDTO save(MaterialDTO material, Long topicId) {
        return MaterialMapper.materialToMaterialDTO(materialDao.save(MaterialMapper.materialDTOToMaterial(material), topicId));
    }

    @Override
    public MaterialDTO update(MaterialDTO material) {
        return MaterialMapper.materialToMaterialDTO(materialDao.update(MaterialMapper.materialDTOToMaterial(material)));
    }

    @Override
    public void delete(MaterialDTO material) {
        materialDao.delete(material.getId());
    }
}
