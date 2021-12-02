package com.softserve.betterlearningroom.mapper;

import com.softserve.betterlearningroom.model.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(rs.getLong("questionId"),
                rs.getString("question"),
                rs.getString("question"));
    }
}
