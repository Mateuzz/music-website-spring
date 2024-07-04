package com.gmail.mateusfcosta2002.musicwebsite.Entities;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "musics")
public class Music {
    @Id @GeneratedValue
    private Integer id;

    // TODO
    private List<String> tags = new ArrayList<>();

    private String name;
    
    // TODO AUTHOR
    
    @OneToOne(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
        fetch = FetchType.LAZY
    )
    private Category category;

    private Duration duration;

    private URI uri;
}
