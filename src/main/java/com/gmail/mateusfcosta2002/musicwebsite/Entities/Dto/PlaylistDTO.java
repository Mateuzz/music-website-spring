package com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto;

import java.time.Instant;

public record PlaylistDTO (
    long id,
    String name,
    UserDTO createdBy,
    String uriImage,
    Instant createDate,
    Instant updateDate,
    LinksDTO links
) {}
