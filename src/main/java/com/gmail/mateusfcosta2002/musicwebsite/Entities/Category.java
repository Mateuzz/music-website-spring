package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int nesting = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @Column(nullable = false, length = 100)
    private String name;

    public Category() {}

    public static Category of(String name) {
        var c = new Category();
        c.name = name;
        return c;
    }

    public static Category of(String name, Category parent) {
        var c = new Category();
        c.name = name;
        c.parent = parent;
        c.nesting = parent.nesting + 1;
        return c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNesting() {
        return nesting;
    }

    public void setNesting(int nesting) {
        this.nesting = nesting;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
