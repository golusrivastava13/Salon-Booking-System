package com.salonbooking.repository;

import com.salonbooking.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findBySalonId(Long salonId);

    Long id(Long id);
}
