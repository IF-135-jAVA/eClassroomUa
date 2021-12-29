package com.softserve.betterlearningroom.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AssignmentStatus {

    NOTDONE(1), ONREVIEW(2), DONE(3);

    private long id;
}
