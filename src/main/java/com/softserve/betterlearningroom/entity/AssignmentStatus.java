package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AssignmentStatus {

    TODO(1L), ONREVIEW(2L), DONE(3L);

    private Long id;
}
