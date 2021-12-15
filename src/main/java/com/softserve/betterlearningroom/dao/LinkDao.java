package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Link;
import com.softserve.betterlearningroom.entity.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:linkQuery.properties")
public class LinkDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${get.all.links}")
    private String getAllQuery;

    @Value("${add.new.link}")
    private String addQuery;

    @Value("${update.link}")
    private String updateQuery;

    @Value("${remove.link}")
    private String removeQuery;

    @Autowired
    public LinkDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Link> getAllLinks(Long materialId) {
        return jdbcTemplate.query(getAllQuery, new MapSqlParameterSource("materialid", materialId), BeanPropertyRowMapper.newInstance(Link.class));
    }

    public List<Link> getAllLinks(Material material) {
        return getAllLinks(material.getId());
    }

    public int addLink(Link link, Long materialId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("materialid", materialId);
        param.addValue("linktext", link.getText());
        param.addValue("url", link.getUrl());
        return jdbcTemplate.update(addQuery, param);
    }

    public int addLink(Link link, Material material) {
        return addLink(link, material.getId());
    }

    public int updateLink(Link link) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("linkid", link.getId());
        param.addValue("linktext", link.getText());
        param.addValue("url", link.getUrl());
        return jdbcTemplate.update(updateQuery, param);    }

    public int removeLink(Long linkId) {
        return jdbcTemplate.update(removeQuery, new MapSqlParameterSource("linkid", linkId));
    }

    public int removeLink(Link link) {
        return removeLink(link.getId());
    }

}