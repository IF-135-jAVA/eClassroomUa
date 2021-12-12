package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.repository.CriterionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriterionService {

    @Autowired
    private CriterionDAO criterionDAO;

    public Criterion findById(Integer id) {

        return criterionDAO.findById(id).orElseThrow(() -> new RuntimeException("criterion by id is not saved"));
    }

    public int removeById(Integer id) {

        return criterionDAO.removeById(id);
    }


    public List<Criterion> findAll() {

        return criterionDAO.findAll();
    }

    public void save(CriterionDTO criterionDTO) {

        criterionDAO.save(toEntity(criterionDTO));
    }

    public void update(CriterionDTO criterionDTO) {

        criterionDAO.update(toEntity(criterionDTO));
    }

    public Criterion toEntity(CriterionDTO criterionDTO) {
        Criterion criterion = new Criterion();
        criterion.builder()
                .title(criterionDTO.getTitle())
                .description(criterionDTO.getDescription());
        //todo list?
        return criterion;

    }

    public CriterionDTO toDTO(Criterion criterion) {
        CriterionDTO criterionDTO = new CriterionDTO();
        criterionDTO.builder()
                .title(criterion.getTitle())
                .description(criterion.getDescription());
        return criterionDTO;

    }

}
