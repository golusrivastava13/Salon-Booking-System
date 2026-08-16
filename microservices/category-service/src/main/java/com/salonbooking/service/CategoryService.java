package com.salonbooking.service;

import com.salonbooking.dto.SalonDTO;
import com.salonbooking.modal.Category;

import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoriesBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long Id, Long salonId) throws Exception;
}
