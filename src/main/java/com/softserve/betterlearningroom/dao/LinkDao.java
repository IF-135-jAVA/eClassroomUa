package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Link;
import com.softserve.betterlearningroom.entity.Material;

import java.util.List;

public interface LinkDao {
    List<Link> getAllLinks(Long materialId);
    List<Link> getAllLinks(Material material);
    int addLink(Link link, Long materialId);
    int addLink(Link link, Material material);
    int updateLink(Link link);
    int removeLink(Long linkId);
    int removeLink(Link link);
}
