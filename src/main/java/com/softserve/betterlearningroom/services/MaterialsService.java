package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.entity.Materials;
import com.softserve.betterlearningroom.repository.MaterialsRepository;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Works with materials.
 */
public class MaterialService {

    @Autowired
    private MaterialsRepository materialsRepository;

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
        return materialsRepository.readById(id);
    }
    public List<Materials> findAll(){
        return materialsRepository.getAll();
    }
   // public MaterialsRepository delete(Materials materials){



}
