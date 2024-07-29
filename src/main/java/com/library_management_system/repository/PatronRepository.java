package com.library_management_system.repository;

import com.library_management_system.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
