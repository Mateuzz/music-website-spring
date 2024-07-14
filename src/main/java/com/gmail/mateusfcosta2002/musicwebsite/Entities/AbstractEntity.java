package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;

/**
 * Used for Entities that does not have version and have manual id so can work with 
 * spring data jpa nicely
 */
@MappedSuperclass
public abstract class AbstractEntity<ID> implements Persistable<ID> {
    @Transient
    private transient boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    @PostLoad
    void markNotNew() {
        isNew = false;
    }
}
