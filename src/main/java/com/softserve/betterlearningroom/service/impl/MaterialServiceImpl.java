package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.mapper.MaterialMapper;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO: Exception handle
@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialDao materialDao;
    private final MaterialMapper materialMapper;

    @Override
    public MaterialDTO getMaterialById(Long id) {
        return materialMapper.materialToMaterialDTO(materialDao.getById(id));
    }

    @Override
    public MaterialDTO getFirstMaterialByName(String name, Long classroomId) {
        return materialMapper.materialToMaterialDTO(materialDao.getAllByName(classroomId, name).stream().findFirst().orElse(null));
    }

    @Override
    public List<MaterialDTO> getAllMaterialsByName(String name, Long classroomId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.getAllByName(classroomId, name).forEach(m -> materials.add(materialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> getMaterialsByClassroom(Long classroomId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.getAllByClassroom(classroomId).forEach(m -> materials.add(materialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> getMaterialsByType(Long classroomId, MaterialType materialType) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.getAllByType(classroomId, materialType).forEach(m -> materials.add(materialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public List<MaterialDTO> getMaterialsByTopic(Long classroomId, Long topicId) {
        List<MaterialDTO> materials = new ArrayList<>();
        materialDao.getAllByTopic(classroomId, topicId).forEach(m -> materials.add(materialMapper.materialToMaterialDTO(m)));
        return materials;
    }

    @Override
    public MaterialDTO addMaterial(MaterialDTO material, Long topicId) {
        return materialMapper.materialToMaterialDTO(materialDao.addMaterial(materialMapper.materialDTOToMaterial(material), topicId));
    }

    @Override
    public MaterialDTO updateMaterial(MaterialDTO material) {
        return materialMapper.materialToMaterialDTO(materialDao.updateMaterial(materialMapper.materialDTOToMaterial(material)));
    }

    @Override
    public void deleteMaterial(MaterialDTO material) {
        materialDao.removeMaterial(materialMapper.materialDTOToMaterial(material));
    }
}
