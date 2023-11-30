package com.devlach.springsecurityoauth2resourceserver;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findAllByOwner(String owner);
}
