package com.demo.labmanagement.labmanagement;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/category")
    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> getCategory(@PathVariable("id") Long id) {
        return categoryRepository.findById(id);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@RequestBody Category category, 
            @PathVariable("id") Long id) {
        if (!category.getId().equals(id)) {
            throw new IllegalArgumentException("Id value provided on URL does not match id on request body");
        }
        return categoryRepository.save(category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
    }
}