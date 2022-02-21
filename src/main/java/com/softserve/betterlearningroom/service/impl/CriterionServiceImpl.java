package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.CriterionDAOImpl;
import com.softserve.betterlearningroom.dto.CriterionDTO;
import com.softserve.betterlearningroom.mapper.CriterionMapper;
import com.softserve.betterlearningroom.service.CriterionServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CriterionServiceImpl implements CriterionServise {

    @Autowired
    private CriterionDAOImpl criterionDAOImpl;

    public CriterionDTO findById(Long id) {
        return CriterionMapper.toDTO(criterionDAOImpl.findById(id));
    }

    @Override
    public void delete(Long id) {
        criterionDAOImpl.delete(id);
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
    public CriterionDTO update(Long criterionId, CriterionDTO criterionDTO) {

        CriterionDTO oldCriterionDTO = findById(criterionId);
        log.info(oldCriterionDTO.toString());
        oldCriterionDTO.setTitle(criterionDTO.getTitle());
        oldCriterionDTO.setDescription(criterionDTO.getDescription());
        oldCriterionDTO.setMaterialId(criterionDTO.getMaterialId());
        return CriterionMapper.toDTO(criterionDAOImpl.update(CriterionMapper.toEntity(oldCriterionDTO)));
    }

    @Override
    public List<CriterionDTO> findAllByMaterialId(Long materialId){
        return criterionDAOImpl.findAllByMaterialId(materialId).stream().map(CriterionMapper::toDTO).collect(Collectors.toList());
    }
}
