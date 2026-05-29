package com.hu.huspring.services;

import com.hu.huspring.models.Category;
import com.hu.huspring.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public long count() {
        return categoryRepository.count();
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }
}
