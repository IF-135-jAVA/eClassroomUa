package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDaoImpl;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.mapper.CriterionMapper;
import com.softserve.betterlearningroom.service.CriterionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CriterionServiceImpl implements CriterionServise {

    @Autowired
    private CriterionDaoImpl criterionDAOImpl;

    public CriterionDTO findById(Long id) {

        return CriterionMapper.toDTO(criterionDAOImpl.findById(id));
    }

    @Override
    public void removeById(Long id) {

        criterionDAOImpl.removeById(id);
    }

    @Override
    public List<CriterionDTO> findAll() {

        return criterionDAOImpl.findAll().stream().map(CriterionMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public CriterionDTO save(CriterionDTO criterionDTO) {

        return CriterionMapper.toDTO(criterionDAOImpl.save(CriterionMapper.toEntity(criterionDTO)));
    }

    @Override
    public CriterionDTO update(CriterionDTO criterionDTO) {

        return CriterionMapper.toDTO(criterionDAOImpl.update(CriterionMapper.toEntity(criterionDTO)));
    }

    @Override
    public List<CriterionDTO> findAllByMaterialId(Long materialId){

        return criterionDAOImpl.findByMaterialId(materialId).stream().map(CriterionMapper::toDTO).collect(Collectors.toList());
    }
}
