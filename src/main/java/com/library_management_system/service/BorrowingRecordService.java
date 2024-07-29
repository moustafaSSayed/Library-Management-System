package com.library_management_system.service;

import com.library_management_system.entity.BorrowingRecord;

public interface BorrowingRecordService {
    BorrowingRecord borrowBook(Long bookId, Long patronId);
    BorrowingRecord returnBook(Long bookId, Long patronId);
}
