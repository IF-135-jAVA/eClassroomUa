package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.repository.CriterionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterionService {

    @Autowired
    private CriterionDAO criterionDAO;

    public Criterion findById(Integer id) {

        return criterionDAO.findById(id).orElseThrow(() -> new RuntimeException("criterion didn't find"));
    }

    public void removeById(Integer id) {

        criterionDAO.removeById(id);
    }


    public List<CriterionDTO> findAll() {

        return criterionDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void save(CriterionDTO criterionDTO) {
        criterionDAO.save(toEntity(criterionDTO));
    }

    public void update(CriterionDTO criterionDTO) {
        criterionDAO.update(toEntity(criterionDTO));
    }

    public Criterion toEntity(CriterionDTO criterionDTO) {
        return Criterion.builder()
                .title(criterionDTO.getTitle())
                .description(criterionDTO.getDescription())
                .build();
    }

    public CriterionDTO toDTO(Criterion criterion) {
        return CriterionDTO.builder()
                .title(criterion.getTitle())
                .description(criterion.getDescription())
                .build();
    }

}
