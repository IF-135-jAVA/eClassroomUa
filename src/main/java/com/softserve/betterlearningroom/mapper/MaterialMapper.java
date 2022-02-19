package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dao.impl.MaterialDAOImpl;
import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.MaterialType;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MaterialMapper {

    public static MaterialDTO materialToMaterialDTO(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        log.info(material.toString());
        materialDTO.setDueDate(material.getDueDate());
        materialDTO.setStartDate(material.getStartDate());
        materialDTO.setMaxScore(material.getMaxScore());
        materialDTO.setQuestions(material.getQuestions());
        materialDTO.setTask(material.getTask());
        materialDTO.setUrl(material.getUrl());
        materialDTO.setUrls(material.getUrls());
        materialDTO.setTitle(material.getTitle());
        materialDTO.setText(material.getText());
        materialDTO.setId(material.getId());
        materialDTO.setTopicId(material.getTopicId());
        materialDTO.setClassroomId(material.getClassroomId());
        materialDTO.setMaterialType(material.getMaterialType().name());
        return materialDTO;
    }

    public static Material materialDTOToMaterial(MaterialDTO materialDTO) {
        Material material = new Material();
        material.setDueDate(materialDTO.getDueDate());
        material.setStartDate(materialDTO.getStartDate());
        material.setMaxScore(materialDTO.getMaxScore());
        material.setQuestions(materialDTO.getQuestions());
        material.setTask(materialDTO.getTask());
        material.setUrl(materialDTO.getUrl());
        material.setUrls(materialDTO.getUrls());
        material.setTitle(materialDTO.getTitle());
        material.setText(materialDTO.getText());
        material.setId(materialDTO.getId());
        material.setTopicId(materialDTO.getTopicId());
        material.setClassroomId(materialDTO.getClassroomId());
        material.setMaterialType(MaterialType.valueOf(materialDTO.getMaterialType()));
        return material;
    }

}
