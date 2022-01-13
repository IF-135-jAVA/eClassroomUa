package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.impl.LevelDaoImpl;
import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.mapper.LevelMapper;
import com.softserve.betterlearningroom.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {


    @Autowired
    private LevelDaoImpl levelDaoImpl;

    @Override
    public LevelDTO findById(Long id) {

        return LevelMapper.toDTO(levelDaoImpl.findById(id));
    }

    @Override
    public void removeById(Long id) {

        levelDaoImpl.removeById(id);
    }

    @Override
    public List<LevelDTO> findAll() {

        return levelDaoImpl.findAll().stream().map(LevelMapper::toDTO).collect(Collectors.toList());
    }

    public List<LevelDTO> findAllDeleted() {

        return levelDaoImpl.findAllDeleted().stream().map(LevelMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public LevelDTO save(LevelDTO levelDTO) {

        return LevelMapper.toDTO(levelDaoImpl.save(LevelMapper.toEntity(levelDTO)));
    }

    @Override
    public LevelDTO update(LevelDTO levelDTO) {

        return LevelMapper.toDTO(levelDaoImpl.update(LevelMapper.toEntity(levelDTO)));
    }




}
