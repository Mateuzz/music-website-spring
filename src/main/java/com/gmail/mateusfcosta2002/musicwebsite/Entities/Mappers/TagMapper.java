package com.gmail.mateusfcosta2002.musicwebsite.Entities.Mappers;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Tag;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.TagDTO;

public class TagMapper {
    public static TagDTO createDTO(Tag tag) {
        return new TagDTO(tag.getName());
    }    
}
