package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.softserve.betterlearningroom.entity.MaterialType.*;

public class MaterialRowMapper implements ResultSetExtractor<List<Material>> {

    @Override
    public List<Material> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Material> materialMap = new HashMap<>();
        while (rs.next()) {
            Long materialId = rs.getLong("materialid");
            Material material = materialMap.get(materialId);
            MaterialType type = valueOf(rs.getString("materialType"));
            if(material == null){
                switch (type){
                    case TEST:
                        material = new Test();
                        break;
                    case TASK:
                        material = new Task();
                        break;
                    default:
                        material = new Material();
                        break;
                }
                material.setTask(rs.getString("task"));
                material.setUrl(rs.getString("testUrl"));
                material.setStartDate(rs.getDate("startdate").toLocalDate().atStartOfDay());
                material.setDueDate(rs.getDate("duedate").toLocalDate().atStartOfDay());
                material.setMaxScore(rs.getByte("maxScore"));
                material.setId(materialId);
                material.setText(rs.getString("materialtext"));
                material.setMaterialType(type);
                material.setTitle(rs.getString("title"));
                materialMap.put(materialId, material);
            }
            List<Link> links = material.getUrls();
            if(links == null){
                links = new ArrayList<>();
                material.setUrls(links);
            }
            Link link = new Link();
            link.setId(rs.getLong("linkid"));
            link.setText(rs.getString("linktext"));
            link.setUrl(rs.getString("url"));
            links.add(link);
            if(type == QUESTIONS){
                List<Question> questions = material.getQuestions();
                if(questions == null){
                    questions = new ArrayList<>();
                    material.setQuestions(questions);
                }
                Question question = new Question();
                question.setId(rs.getLong("linkid"));
                question.setQuestion(rs.getString("question"));
                questions.add(question);
            }
        }
        return new ArrayList<>(materialMap.values());
    }
}
