package com.softserve.betterlearningroom.entity.roles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softserve.betterlearningroom.entity.roles.Permissions.*;

@Getter
@RequiredArgsConstructor
public enum Roles {

    TEACHER(Arrays.asList(ASSIGNMENT_READ, ASSIGNMENT_WRITE, USER_READ, USER_WRITE, CLASSROOM_READ, CLASSROOM_WRITE,
            TOPIC_READ, TOPIC_WRITE, USER_ASSIGNMENT_READ, USER_ASSIGNMENT_WRITE, COMMENT_READ, COMMENT_WRITE,
            CRITERION_READ, CRITERION_WRITE, LEVEL_READ, LEVEL_WRITE, ANNOUNCEMENT_READ, ANNOUNCEMENT_WRITE)
            .stream().collect(Collectors.toSet())),
    STUDENT(Arrays
            .asList(ASSIGNMENT_READ, ASSIGNMENT_WRITE, USER_READ, USER_WRITE, CLASSROOM_READ, TOPIC_READ,
                    USER_ASSIGNMENT_READ, USER_ASSIGNMENT_WRITE, COMMENT_READ, COMMENT_WRITE, CRITERION_READ,
                    LEVEL_READ, ANNOUNCEMENT_READ)
            .stream().collect(Collectors.toSet()));

    private final Set<Permissions> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> userPermissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        userPermissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return userPermissions;
    }
}
