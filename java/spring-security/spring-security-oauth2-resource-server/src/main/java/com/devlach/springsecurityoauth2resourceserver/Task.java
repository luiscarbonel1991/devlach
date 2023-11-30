package com.devlach.springsecurityoauth2resourceserver;

import org.springframework.data.annotation.Id;

public record Task(
        @Id Long id,
        String description,
        boolean completed,
        String owner
) {
    public Task(String description, boolean completed, String owner) {
        this(null, description, completed, owner);
    }
}
