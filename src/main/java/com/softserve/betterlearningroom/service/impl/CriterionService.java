package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDAO;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.mapper.CriterionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterionService {

    @Autowired
    private CriterionDAO criterionDAO;

    public CriterionDTO findById(Integer id) {

        return CriterionMapper.toDTO(criterionDAO.findById(id));
    }

    public void removeById(Integer id) {

        criterionDAO.removeById(id);
    }


    public List<CriterionDTO> findAll() {

        return criterionDAO.findAll().stream().map(CriterionMapper::toDTO).collect(Collectors.toList());

    }

    public CriterionDTO save(CriterionDTO criterionDTO) {

      return CriterionMapper.toDTO(criterionDAO.save(CriterionMapper.toEntity(criterionDTO)));
    }

    public void update(CriterionDTO criterionDTO) {

        criterionDAO.update(CriterionMapper.toEntity(criterionDTO));
    }



}
