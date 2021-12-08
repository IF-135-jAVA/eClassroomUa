package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.converter.MaterialConverter;
import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;
import com.softserve.betterlearningroom.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

//TODO: Exception handle
@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialDao materialDao;
    private MaterialConverter materialConverter;

    @Autowired
    public MaterialServiceImpl(MaterialDao materialDao, MaterialConverter materialConverter) {
        this.materialDao = materialDao;
        this.materialConverter = materialConverter;
    }

    @Override
    public MaterialDTO getMaterialById(Long id) {
        return materialConverter.materialToMaterialDTO(materialDao.getById(id));
    }

    @Override
    public MaterialDTO getFirstMaterialByName(String name, Long classroomId) {
        return materialConverter.materialToMaterialDTO(materialDao.getAllByName(classroomId, name).stream().findFirst().orElse(null));
    }

    @Override
    public List<? extends MaterialDTO> getAllMaterialsByName(String name, Long classroomId) {
        List<MaterialDTO> materials = new LinkedList<>();
        for (Material m: materialDao.getAllByName(classroomId, name)) {
            materials.add(materialConverter.materialToMaterialDTO(m));
        }
        return materials;
    }

    @Override
    public List<? extends MaterialDTO> getMaterialsByClassroom(Long classroomId) {
        List<MaterialDTO> materials = new LinkedList<>();
        for (Material m: materialDao.getAllByClassroom(classroomId)) {
            materials.add(materialConverter.materialToMaterialDTO(m));
        }
        return materials;
    }

    @Override
    public List<? extends MaterialDTO> getMaterialsByType(Long classroomId, MaterialType materialType) {
        List<MaterialDTO> materials = new LinkedList<>();
        for (Material m: materialDao.getAllByType(classroomId, materialType)) {
            materials.add(materialConverter.materialToMaterialDTO(m));
        }
        return materials;
    }

    @Override
    public List<? extends MaterialDTO> getMaterialsByTopic(Long classroomId, Long topicId) {
        List<MaterialDTO> materials = new LinkedList<>();
        for (Material m: materialDao.getAllByTopic(classroomId, topicId)) {
            materials.add(materialConverter.materialToMaterialDTO(m));
        }
        return materials;
    }

    @Override
    public void addMaterial(MaterialDTO material, Long topicId) {
        materialDao.addMaterial(materialConverter.materialDTOToMaterial(material), topicId);
    }

    @Override
    public void updateMaterial(MaterialDTO material) {
        materialDao.updateMaterial(materialConverter.materialDTOToMaterial(material));
    }
}
