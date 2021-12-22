package com.softserve.betterlearningroom.service.impl;

import com.softserve.betterlearningroom.dao.LevelDAO;
import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {


    @Autowired
    private LevelDAO levelDAO;

    public LevelDTO findById(Integer id) {
        return toDTO(levelDAO.findById(id).orElseThrow(() -> new RuntimeException("level didn't find")));
    }

    public void removeById(Integer id) {
        levelDAO.removeById(id);
    }


    public List<LevelDTO> findAll() {
        return levelDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void save(LevelDTO levelDTO) {
        levelDAO.save(toEntity(levelDTO));
    }

    public void update(LevelDTO levelDTO) {
        levelDAO.update(toEntity(levelDTO));
    }

    public Level toEntity(LevelDTO levelDTO) {
        return Level.builder()
                .levelid(levelDTO.getId())
                .criterionid(levelDTO.getCriterionId())
                .title(levelDTO.getTitle())
                .description(levelDTO.getDescription())
                .mark(levelDTO.getMark())
                .build();

    }

    public LevelDTO toDTO(Level level) {
        return  LevelDTO.builder()
                .id(level.getLevelid())
                .criterionId(level.getCriterionid())
                .title(level.getTitle())
                .description(level.getDescription())
                .mark(level.getMark())
                .build();
    }


}
