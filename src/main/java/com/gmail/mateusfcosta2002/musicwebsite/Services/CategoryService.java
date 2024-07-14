package com.gmail.mateusfcosta2002.musicwebsite.Services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Category;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.CategoryDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinkDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.LinksDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Util.LinksDTOUtils;

@Component
public class CategoryService {
    private LinksDTOUtils linksDTOUtils;

    public CategoryService(LinksDTOUtils linksDTOUtils) {
        this.linksDTOUtils = linksDTOUtils;
    }

    public LinkDTO getCanonicalLink(long id) {
        return linksDTOUtils.linkDTO("categories/" + id, List.of("GET", "DELETE"));
    }

    public LinkDTO getRootLink() {
        return linksDTOUtils.linkDTO("categories/", List.of("GET", "POST"));
    }

    public CategoryDTO createDTO(Category category) {
        var parent_id = category.getParent() == null ? null : category.getParent().getId();
        return new CategoryDTO(
            parent_id,
            category.getId(),
            category.getNesting(),
            category.getName(),
            LinksDTO.withCanonical(getCanonicalLink(category.getId()))
        );
    }
}
