package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.mapper.MaterialRowMapper;
import com.softserve.betterlearningroom.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MaterialDao {

    private JdbcTemplate jdbcTemplate;

    private CriterionDao criterionDao;

    private QuestionDao questionDao;

    private LinkDao linkDao;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public MaterialDao(CriterionDao criterionDao, QuestionDao questionDao, LinkDao linkDao) {
        this.criterionDao = criterionDao;
        this.questionDao = questionDao;
        this.linkDao = linkDao;
    }

    public List<Material> getById(Long materialId) {
        return jdbcTemplate.query("SELECT * FROM materials where materialid=?", new Object[] { materialId }, new MaterialRowMapper());
    }

    public List<Material> getAllByClassroom(Long classroomId) { // TODO: Change materialId to classroomId
        return jdbcTemplate.query("SELECT * FROM materials where materialid=?", new Object[] { classroomId }, new MaterialRowMapper());
    }

    public List<Material> getAllByTopic(Long classroomId, Long topicId) { // TODO: Change materialId to topicId and add classroomId check
        return jdbcTemplate.query("SELECT * FROM materials where materialid=?", new Object[] { topicId }, new MaterialRowMapper());
    }

    //TODO: Create getAllByUser;

    public List<Material> getAllByName(String name) { // TODO: Change materialId to topicId
        return jdbcTemplate.query("SELECT * FROM materials where title like ?", new Object[] { name }, new MaterialRowMapper());
    }

    public List<Material> getAllByType(Long classroomId, MaterialType materialType) { // TODO: Add classroomId check
        return jdbcTemplate.query("SELECT * FROM materials where \"materialType\" like ?", new Object[] { materialType.toString() }, new MaterialRowMapper());
    }

    public boolean addMaterial(Material material) {
        MaterialType materialType = material.getMaterialType();
        for (Link link : material.getUrls()) {
            linkDao.addLink(link, material);
        }
        for (Criterion criterion : material.getCriterions()) {
            criterionDao.addCriterion(criterion, material);
        }
        for (Question question : material.getQuestions()) {
            questionDao.addQuestion(question, material);
        }
        return jdbcTemplate.update("INSERT INTO materials (title, materialtext, startdate, duedate, \"materialType\", materialid, task, answer, \"testUrl\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", material.getTitle(), material.getText(), material.getStartDate(), material.getDueDate(), material.getMaterialType(), material.getId(), material.getTask(), material.getAnswer(), material.getUrl()) == 1;
    }

    public boolean updateMaterial(Material material) {
        for (Link link : material.getUrls()) {
            linkDao.updateLink(link);
        }
        for (Criterion criterion : material.getCriterions()) {
            criterionDao.updateCriterion(criterion);
        }
        for (Question question : material.getQuestions()) {
            questionDao.updateQuestion(question);
        }
        return jdbcTemplate.update("UPDATE materials SET title=?, materialtext=?, startdate=?, duedate=?, \"materialType\"=?, task=?, answer=?, \"testUrl\"=? WHERE materialid=?", material.getTitle(), material.getText(), material.getStartDate(), material.getDueDate(), material.getMaterialType(), material.getTask(), material.getAnswer(), material.getUrl(), material.getId()) == 1;
    }

    public boolean removeMaterial(Long materialId) {
        return removeMaterial(getById(materialId).stream().findFirst().get());
    }

    public boolean removeMaterial(Material material) {
        for (Link link : material.getUrls()) {
            linkDao.removeLink(link);
        }
        for (Criterion criterion : material.getCriterions()) {
            criterionDao.removeCriterion(criterion);
        }
        for (Question question : material.getQuestions()) {
            questionDao.removeQuestion(question);
        }
        return jdbcTemplate.update("DELETE FROM materials WHERE materialId=?", material.getId()) == 1;
    }

}
