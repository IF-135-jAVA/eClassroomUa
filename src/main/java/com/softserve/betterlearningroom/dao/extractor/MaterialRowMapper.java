package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.dao.CriterionDao;
import com.softserve.betterlearningroom.dao.LinkDao;
import com.softserve.betterlearningroom.dao.QuestionDao;
import com.softserve.betterlearningroom.entity.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
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
                    case QUESTIONS:
                        material = new Material();
                        material.setStartDate(rs.getDate("startdate").toLocalDate().atStartOfDay());
                        material.setDueDate(rs.getDate("duedate").toLocalDate().atStartOfDay());
                        material.setMaxScore(rs.getByte("maxScore"));
                        break;
                    case TEST:
                        material = new Test();
                        material.setStartDate(rs.getDate("startdate").toLocalDate().atStartOfDay());
                        material.setDueDate(rs.getDate("duedate").toLocalDate().atStartOfDay());
                        material.setMaxScore(rs.getByte("maxScore"));
                        material.setUrl(rs.getString("testUrl"));
                        break;
                    case TASK:
                        material = new Task();
                        material.setStartDate(rs.getDate("startdate").toLocalDate().atStartOfDay());
                        material.setDueDate(rs.getDate("duedate").toLocalDate().atStartOfDay());
                        material.setMaxScore(rs.getByte("maxScore"));
                        material.setTask(rs.getString("task"));
                        break;
                    case MATERIAL:
                        material = new Material();
                        break;
                }
                material.setId(materialId);
                material.setText(rs.getString("materialtext"));
                material.setMaterialType(type);
                materialMap.put(materialId, material);
            }
            List<Link> links = material.getUrls();
            if(links == null){
                links = new LinkedList<>();
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
                    questions = new LinkedList<>();
                    material.setQuestions(questions);
                }
                Question question = new Question();
                question.setId(rs.getLong("linkid"));
                question.setQuestion(rs.getString("question"));
                questions.add(question);
            }
            if(type != MATERIAL){
                List<Criterion> criterions = material.getCriterions();
                if(criterions == null){
                    criterions = new LinkedList<>();
                    material.setCriterions(criterions);
                }
                Criterion criterion = new Criterion();
                criterion.setId(rs.getLong("criterionid"));
                criterion.setTitle(rs.getString("title"));
                criterion.setDescription(rs.getString("description"));
                criterions.add(criterion);
                List<Level> levels = criterion.getLevels();
                if(levels == null){
                    levels = new LinkedList<>();
                    criterion.setLevels(levels);
                }
                Level level = new Level();
                level.setId(rs.getLong("levelid"));
                level.setTitle(rs.getString("leveltitle"));
                level.setDescription(rs.getString("leveldesription"));
                level.setMark(rs.getInt("mark"));
                levels.add(level);
            }
        }
        return new LinkedList<Material>(materialMap.values());
    }
}
