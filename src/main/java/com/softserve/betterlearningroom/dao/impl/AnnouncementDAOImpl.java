package com.softserve.betterlearningroom.dao.impl;

import com.softserve.betterlearningroom.dao.AnnouncementDAO;
import com.softserve.betterlearningroom.entity.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@PropertySource(value = "classpath:/db/announcements/announcement_queries.properties")
public class AnnouncementDAOImpl implements AnnouncementDAO {
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${findById.announcement}")
    private String getById;
    
    @Value("${save.announcement}")
    private String save;
    
    @Value("${update.announcement}")
    private String edit;
    
    @Value("${remove.announcement}")
    private String remove;
    
    @Value("${findBy.courseId}")
    private String getByCourseId;

    @Autowired
    public AnnouncementDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Announcement> findByCourseId(Long courseId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("courseId", courseId);
        return namedParameterJdbcTemplate.query(getByCourseId, parameterSource, BeanPropertyRowMapper.newInstance(Announcement.class));
    }

    @Override
    public Announcement findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Announcement result = DataAccessUtils.singleResult(namedParameterJdbcTemplate.query(getById,
                parameterSource, BeanPropertyRowMapper.newInstance(Announcement.class)));
        if (result == null) {
            throw new DataRetrievalFailureException("Announcement with id - " + id + ", not found.");
        }
        return result;
    }

    @Override
    public Announcement save(Announcement announcement) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(announcement);
        namedParameterJdbcTemplate.update(save, parameterSource, keyHolder, new String[]{"id"});
        return findById(keyHolder.getKeyAs(Long.class));
    }

    @Override
    public Announcement update(Announcement updatedAnnouncement) {
        BeanPropertySqlParameterSource parameterSource =
                new BeanPropertySqlParameterSource(updatedAnnouncement);
        namedParameterJdbcTemplate.update(edit, parameterSource);
        return findById(updatedAnnouncement.getId());
    }

    @Override
    public void delete(Long id) {
        findById(id);
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(remove, parameterSource);
    }
}











