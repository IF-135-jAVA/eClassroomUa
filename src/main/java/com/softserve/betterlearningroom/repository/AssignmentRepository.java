package com.softserve.betterlearningroom.repository;

import com.softserve.betterlearningroom.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.softserve.betterlearningroom.model.Assignment;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query(value = "select * from Assignment where ASSIGNMENT_TYPE is not null")
    List<Assignment> findAllByUser(@Param("userId")Long userId);

    @Query(value = "select * from Assignment where ASSIGNMENT_TYPE like LOWER(:TYPE)", nativeQuery = true)
    List<Assignment> findAllByType(@Param("TYPE")String type);

}