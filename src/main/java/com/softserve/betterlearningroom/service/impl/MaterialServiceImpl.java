package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.MaterialDao;
import com.softserve.betterlearningroom.model.Material;
import com.softserve.betterlearningroom.model.MaterialType;
import com.softserve.betterlearningroom.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO: Exception handle
@Service
public class MaterialServiceImpl implements MaterialService {

    private MaterialDao materialDao;

    @Override
    public Material getMaterialById(Long id) {
        return materialDao.getById(id).stream().findFirst().get();
    }

    @Override
    public Material getFirstMaterialByName(String name) {
        return materialDao.getAllByName(name).stream().findFirst().get();
    }

    @Override
    public List<? extends Material> getAllMaterialsByName(String name) {
        return materialDao.getAllByName(name);
    }

    @Override
    public List<? extends Material> getMaterialsByClassroom(Long classroomId) {
        return materialDao.getAllByClassroom(classroomId);
    }

    @Override
    public List<? extends Material> getMaterialsByType(Long classroomId, MaterialType materialType) {
        return materialDao.getAllByType(classroomId, materialType);
    }

    @Override
    public List<? extends Material> getMaterialsByTopic(Long classroomId, Long topicId) {
        return materialDao.getAllByTopic(classroomId, topicId);
    }

    @Override
    public void addMaterial(Material material) {
        materialDao.addMaterial(material);
    }

    @Override
    public void updateMaterial(Material material) {
        materialDao.updateMaterial(material);
    }
}
