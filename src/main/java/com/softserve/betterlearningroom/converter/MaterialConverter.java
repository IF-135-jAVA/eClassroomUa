package com.softserve.betterlearningroom.converter;

import com.softserve.betterlearningroom.dto.MaterialDTO;
import com.softserve.betterlearningroom.dto.QuestionsDTO;
import com.softserve.betterlearningroom.dto.TaskDTO;
import com.softserve.betterlearningroom.dto.TestDTO;
import com.softserve.betterlearningroom.entity.Material;
import com.softserve.betterlearningroom.entity.Questions;
import com.softserve.betterlearningroom.entity.Task;
import com.softserve.betterlearningroom.entity.Test;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class MaterialConverter {

    public MaterialDTO materialToMaterialDTO(Material material){
        MaterialDTO materialDTO;
        switch (material.getMaterialType()){
            case TEST: materialDTO = new TestDTO();
                break;
            case TASK: materialDTO = new TaskDTO();
                break;
            case QUESTIONS: materialDTO = new QuestionsDTO();
                break;
            default: materialDTO = new MaterialDTO();
        }
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
            case QUESTIONS: material = new Questions();
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
        material.setUrls(material.getUrls());
        material.setTitle(material.getTitle());
        material.setText(material.getText());
        material.setId(material.getId());
        material.setMaterialType(material.getMaterialType());
        return material;
    }

}
