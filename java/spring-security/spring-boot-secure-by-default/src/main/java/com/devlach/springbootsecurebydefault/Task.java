package com.devlach.springbootsecurebydefault;

import org.springframework.data.annotation.Id;

/**
 * Task Entity
 * This is a record class, which is a new feature in Java 14. It is a
 * lightweight data class that is immutable by default. It is a great fit for
 * entities in a database.
 * Note: This class is not annotated with @Entity because we are using the
 * Spring Data JDBC module, which does not require entities to be annotated.
 *
 * @author Luis Carbonel
 * @param id
 * @param description
 * @param completed
 * @param owner
 */

public record Task(@Id Long id, String description, boolean completed, String owner) {
    public Task(String description, boolean completed, String owner) {
        this(null, description, completed, owner);
    }
}
