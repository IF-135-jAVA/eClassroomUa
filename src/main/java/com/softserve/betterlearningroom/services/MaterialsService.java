package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.MaterialsDTO;
import com.softserve.betterlearningroom.entity.Materials;
import com.softserve.betterlearningroom.repository.MaterialsDAO;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Works with materials.
 */
@Service
public class MaterialsService {

    @Autowired
    private MaterialsDAO materialsDAO;

    /**
     * Validates URL string.
     *
     * @param url string for validation.
     * @return true if URL is valid or else is false.
     */
    public boolean validateUrl(String url) {
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }

    public Materials findById(Integer id) {

        return materialsDAO.findById(id).orElseThrow(() -> new RuntimeException("material by id is not saved"));
    }

    public Materials findByTitle(String title) {
        return materialsDAO.findByTitle(title).orElseThrow(() -> new RuntimeException("material didn't find"));
    }

    public int removeById(Integer id) {

        return materialsDAO.removeById(id);
    }

    public int removeByTitle(String title) {

        return materialsDAO.removeByTitle(title);
    }


    public List<Materials> findAll() {

        return materialsDAO.findAll();
    }

    public void save(MaterialsDTO materialsDTO) {

        materialsDAO.save(toEntity(materialsDTO));
    }

    public void update(MaterialsDTO materialsDTO) {

        materialsDAO.update(toEntity(materialsDTO));
    }

    public Materials toEntity(MaterialsDTO materialsDTO) {
        Materials materials = new Materials();
        materials.builder()
                .text(materialsDTO.getText())
                .title(materialsDTO.getTitle())
                .startDate((java.sql.Date) materialsDTO.getStartDate())
                .dueDate((java.sql.Date) materialsDTO.getDueDate())
                .answer(materialsDTO.getAnswer())
                .task(materialsDTO.getTask());
        return materials;

    }

    public MaterialsDTO toDTO(Materials materials) {
        MaterialsDTO materialsDTO = new MaterialsDTO();
        materialsDTO.builder()
                .text(materials.getText())
                .title(materials.getTitle())
                .startDate(materials.getStartDate())
                .dueDate(materials.getDueDate())
                .answer(materials.getAnswer())
                .task(materials.getTask());
        return materialsDTO;

    }

}
