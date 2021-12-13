package com.softserve.betterlearningroom.dao.extractor;

import com.softserve.betterlearningroom.entity.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getLong("questionId"),
                rs.getString("question"));
    }
}
