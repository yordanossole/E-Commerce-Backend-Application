package com.yordanos.dreamShops.service.category;

import com.yordanos.dreamShops.exceptions.AlreadyExistsException;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.Category;
import com.yordanos.dreamShops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName()+" already exists."));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);

//        return Optional.ofNullable(getCategoryById(id)
//                .map(oldCategory -> {
//                    oldCategory.setName(category.getName());
//                    return categoryRepository.save(oldCategory);
//                })).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }


    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository :: delete, () -> {
                    throw new ResourceNotFoundException("Category not found!");
                        });
    }
}
