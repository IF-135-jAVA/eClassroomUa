package com.softserve.betterlearningroom.services;

import com.softserve.betterlearningroom.dto.LevelDTO;
import com.softserve.betterlearningroom.entity.Level;
import com.softserve.betterlearningroom.repository.LevelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService {


    @Autowired
    private LevelDAO levelDAO;

    public Level findById(Integer id) {

        return levelDAO.findById(id).orElseThrow(() -> new RuntimeException("level didn't find"));
    }

    public int removeById(Integer id) {

        return levelDAO.removeById(id);
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
        Level level = new Level();
        level.builder()
                .title(levelDTO.getTitle())
                .description(levelDTO.getDescription());
        return level;

    }

    public LevelDTO toDTO(Level level) {
        LevelDTO levelDTO = new LevelDTO();
        levelDTO.builder()
                .title(level.getTitle())
                .description(level.getDescription());
        return levelDTO;

    }


}
