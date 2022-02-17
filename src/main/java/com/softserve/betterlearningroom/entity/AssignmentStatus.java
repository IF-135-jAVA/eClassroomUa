package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AssignmentStatus {

    IN_PROGRESS(1L), REVIEWED(2L), DONE(3L);

    private Long id;
}
