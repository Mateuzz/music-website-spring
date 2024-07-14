package com.gmail.mateusfcosta2002.musicwebsite.Services;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Tag;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.TagDTO;

public class TagService {
    public static TagDTO createDTO(Tag tag) {
        return new TagDTO(tag.getName());
    }    
}
