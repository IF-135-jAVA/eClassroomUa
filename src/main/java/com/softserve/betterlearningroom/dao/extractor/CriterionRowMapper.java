package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.dao.LevelDao;
import com.softserve.betterlearningroom.entity.Criterion;
import com.softserve.betterlearningroom.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CriterionRowMapper implements ResultSetExtractor<List<Criterion>> {

    @Override
    public List<Criterion> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Long, Criterion> criterionMap = new HashMap<>();
        while (rs.next()) {
            Long criterionId = rs.getLong("criterionid");
            Criterion criterion = criterionMap.get(criterionId);
            if(criterion == null){
                criterion = new Criterion();
                criterion.setId(criterionId);
                criterion.setDescription(rs.getString("description"));
                criterion.setTitle(rs.getString("title"));
                criterionMap.put(criterionId, criterion);
            }
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
        return new LinkedList<>(criterionMap.values());
    }
}
