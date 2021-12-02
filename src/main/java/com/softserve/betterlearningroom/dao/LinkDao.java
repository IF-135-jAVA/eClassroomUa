package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.mapper.LinkRowMapper;
import com.softserve.betterlearningroom.model.Link;
import com.softserve.betterlearningroom.model.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class LinkDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Link> getAllLinks(Long materialId) {
        return jdbcTemplate.query("SELECT * FROM links where materialid=?", new Object[] { materialId }, new LinkRowMapper());
    }

    public List<Link> getAllLinks(Material material) {
        return jdbcTemplate.query("SELECT * FROM links where materialid=?", new Object[] { material.getId() }, new LinkRowMapper());
    }

    public boolean addLink(Link link, Material material) {
        return jdbcTemplate.update("INSERT INTO links (linktext, url, materialid) VALUES (?, ?, ?)", link.getText(), link.getUrl(), material.getId()) == 1;
    }

    public boolean addLink(Link link, Long materialId) {
        return jdbcTemplate.update("INSERT INTO links (linktext, url, materialid) VALUES (?, ?, ?)", link.getText(), link.getUrl(), materialId) == 1;
    }

    public boolean updateLink(Link link) {
        return jdbcTemplate.update("UPDATE links SET linktext=?, url=? WHERE linkid=?", link.getText(), link.getUrl(), link.getId()) == 1;
    }

    public boolean removeLink(Long linkId) {
        return jdbcTemplate.update("DELETE FROM links WHERE linkId=?", linkId) == 1;
    }

    public boolean removeLink(Link link) {
        return jdbcTemplate.update("DELETE FROM links WHERE linkId=?", link.getId()) == 1;
    }

}