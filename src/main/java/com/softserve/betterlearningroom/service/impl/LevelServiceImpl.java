package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.LevelDAOImpl;
import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.mapper.LevelMapper;
import com.softserve.betterlearningroom.service.LevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelDAOImpl levelDaoImpl;

    @Override
    public LevelDTO findById(Long id) {

        return LevelMapper.toDTO(levelDaoImpl.findById(id));
    }

    @Override
    public void delete(Long id) {

        levelDaoImpl.delete(id);
    }

    @Override
    public List<LevelDTO> findAll() {
        return levelDaoImpl.findAll().stream().map(LevelMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<LevelDTO> findAllByCriterionId(Long criterionId) {
        return levelDaoImpl.findAllByCriterionId(criterionId).stream().map(LevelMapper::toDTO).collect(Collectors.toList());
    }


    public List<LevelDTO> findAllDeleted() {
        return levelDaoImpl.findAllDeleted().stream().map(LevelMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public LevelDTO save(LevelDTO levelDTO) {
        return LevelMapper.toDTO(levelDaoImpl.save(LevelMapper.toEntity(levelDTO)));
    }

    @Override
    public LevelDTO update(Long levelId, LevelDTO levelDTO) {
       LevelDTO oldlevelDTO = findById(levelId);
       oldlevelDTO.setTitle(levelDTO.getTitle());
       oldlevelDTO.setDescription(levelDTO.getDescription());
       oldlevelDTO.setMark(levelDTO.getMark());
       oldlevelDTO.setCriterionId(levelDTO.getCriterionId());
        log.info(levelDTO.getTitle(), levelDTO.getDescription(), levelDTO.getMark());
        return LevelMapper.toDTO(levelDaoImpl.update(LevelMapper.toEntity(oldlevelDTO)));
    }
}
