package com.softserve.betterlearningroom.dao;

import com.softserve.betterlearningroom.entity.Link;

import java.util.List;

public interface LinkDao {
    
    List<Link> findAllByMaterialId(Long materialId);

    int save(Link link, Long materialId);

    int update(Link link);
    
    int delete(Long linkId);
}
