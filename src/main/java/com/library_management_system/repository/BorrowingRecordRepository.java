package com.library_management_system.repository;

import com.library_management_system.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);
}
