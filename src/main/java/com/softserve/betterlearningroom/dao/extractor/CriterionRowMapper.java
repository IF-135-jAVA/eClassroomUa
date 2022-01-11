package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.Criterion;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class CriterionRowMapper implements RowMapper<Criterion> {

    @Override
    public Criterion mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Criterion criterion = Criterion.builder()
//                .criterionId(rs.getLong("id"))
//                .materialId()
//        criterion.setId(rs.getLong("id"));
//        criterion.setText(rs.getString("text"));
//        criterion.setAuthorId(rs.getLong("authorId"));
//        criterion.setAnnouncementId(rs.getLong("announcementId"));
//        comment.setUserAssignmentId(rs.getLong("userAssignmentId"));
//        comment.setMaterialId(rs.getLong("materialId"));
//        comment.setDate(rs.getDate("date").toLocalDate().atStartOfDay());

        return null;
    }
}


