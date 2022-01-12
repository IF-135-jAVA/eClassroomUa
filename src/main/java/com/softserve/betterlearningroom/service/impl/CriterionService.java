package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDaoImpl;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.mapper.CriterionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterionService {

    @Autowired
    private CriterionDaoImpl criterionDAOImpl;

    public CriterionDTO findById(Long id) {

        return CriterionMapper.toDTO(criterionDAOImpl.findById(id));
    }

    public void removeById(Long id) {

        criterionDAOImpl.removeById(id);
    }


    public List<CriterionDTO> findAll() {

        return criterionDAOImpl.findAll().stream().map(CriterionMapper::toDTO).collect(Collectors.toList());

    }

    public CriterionDTO save(CriterionDTO criterionDTO) {

      return CriterionMapper.toDTO(criterionDAOImpl.save(CriterionMapper.toEntity(criterionDTO)));
    }

    public CriterionDTO update(CriterionDTO criterionDTO) {

        return CriterionMapper.toDTO(criterionDAOImpl.update(CriterionMapper.toEntity(criterionDTO)));
    }



}
