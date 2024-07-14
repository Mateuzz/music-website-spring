package com.gmail.mateusfcosta2002.musicwebsite.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.mateusfcosta2002.musicwebsite.Entities.Category;
import com.gmail.mateusfcosta2002.musicwebsite.Entities.Dto.CategoryDTO;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CategoryRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CategoryRowRepository;
import com.gmail.mateusfcosta2002.musicwebsite.Repositories.CategoryRowRepository.CategoryRow;
import com.gmail.mateusfcosta2002.musicwebsite.Services.CategoryService;
import com.gmail.mateusfcosta2002.musicwebsite.Web.Exceptions.NotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

record CategoryPost(@Size(max = 100) String name, Long parent_id) {}

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private CategoryRowRepository rowRepository;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService,
            CategoryRowRepository rowRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.rowRepository = rowRepository;
    }

    @GetMapping
    public List<CategoryRow> index() {
        return rowRepository.getAllCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@Valid CategoryPost form) throws NotFoundException {
        Category category;

        if (form.parent_id() != null) {
            var parent = categoryRepository
                .findById(form.parent_id())
                .orElseThrow(() -> new NotFoundException("Parent category with id " + form.parent_id() + " not found"));

            category = Category.of(form.name(), parent);
        } else {
            category = Category.of(form.name());
        }

        categoryRepository.save(category);

        return categoryService.createDTO(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

