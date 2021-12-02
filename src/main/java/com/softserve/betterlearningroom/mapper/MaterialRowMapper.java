package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.dao.CriterionDao;
import com.softserve.betterlearningroom.dao.LinkDao;
import com.softserve.betterlearningroom.dao.QuestionDao;
import com.softserve.betterlearningroom.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MaterialRowMapper implements RowMapper<Material> {

    private LinkDao linkDao;
    private QuestionDao questionDao;
    private CriterionDao criterionDao;
    //TODO: Use UserDao instead of new LinkedList<User>();
    @Override
    public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
        Material material;
        MaterialType materialType = MaterialType.valueOf(rs.getString("materialType"));
        switch(rs.getString("materialType")){
            case "TEST":material = new Test(rs.getLong("materialId"),
                                            materialType,
                                            rs.getString("title"),
                                            rs.getString("materialText"),
                                            linkDao.getAllLinks(rs.getLong("materialId")),
                                            rs.getDate("startDate").toLocalDate().atStartOfDay(),
                                            rs.getDate("dueDate").toLocalDate().atStartOfDay(),
                                            criterionDao.getAllCriterions(rs.getLong("materialId")),
                                            new LinkedList<User>(),
                                            rs.getByte("maxScore"),
                                            rs.getString("testUrl")); break;
            case "TASK":material = new Task(rs.getLong("materialId"),
                                            materialType,
                                            rs.getString("title"),
                                            rs.getString("materialText"),
                                            linkDao.getAllLinks(rs.getLong("materialId")),
                                            rs.getDate("startDate").toLocalDate().atStartOfDay(),
                                            rs.getDate("dueDate").toLocalDate().atStartOfDay(),
                                            criterionDao.getAllCriterions(rs.getLong("materialId")),
                                            new LinkedList<User>(),
                                            rs.getByte("maxScore"),
                                            rs.getString("task"),
                                            rs.getString("answer")); break;
            case "QUESTIONS": material = new Questions(rs.getLong("materialId"),
                                            materialType,
                                            rs.getString("title"),
                                            rs.getString("materialText"),
                                            linkDao.getAllLinks(rs.getLong("materialId")),
                                            rs.getDate("startDate").toLocalDate().atStartOfDay(),
                                            rs.getDate("dueDate").toLocalDate().atStartOfDay(),
                                            criterionDao.getAllCriterions(rs.getLong("materialId")),
                                            new LinkedList<User>(),
                                            rs.getByte("maxScore"),
                                            questionDao.getAllQuestions(rs.getLong("materialId"))); break;
            default: material = new Material(rs.getLong("materialId"),
                                            materialType,
                                            rs.getString("title"),
                                            rs.getString("materialText"),
                                            linkDao.getAllLinks(rs.getLong("materialId")));
        }
        return material;
    }
}
