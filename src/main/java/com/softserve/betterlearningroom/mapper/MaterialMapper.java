package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.Task;
import com.softserve.betterlearningroom.entity.Test;
import org.springframework.stereotype.Component;

@Component
public class MaterialConverter {

    public MaterialDTO materialToMaterialDTO(Material material){
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setCriterions(material.getCriterions());
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
        materialDTO.setMaterialType(material.getMaterialType());
        return materialDTO;
    }

    public Material materialDTOToMaterial(MaterialDTO materialDTO){
        Material material;
        switch (materialDTO.getMaterialType()){
            case TEST: material = new Test();
                break;
            case TASK: material = new Task();
                break;
            default: material = new Material();
        }
        material.setCriterions(materialDTO.getCriterions());
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
        material.setMaterialType(materialDTO.getMaterialType());
        return material;
    }

}
