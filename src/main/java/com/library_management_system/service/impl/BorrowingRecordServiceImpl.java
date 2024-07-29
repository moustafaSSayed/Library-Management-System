package com.library_management_system.service.impl;

import com.library_management_system.entity.Book;
import com.library_management_system.entity.BorrowingRecord;
import com.library_management_system.entity.Patron;
import com.library_management_system.repository.BookRepository;
import com.library_management_system.repository.BorrowingRecordRepository;
import com.library_management_system.repository.PatronRepository;
import com.library_management_system.service.BorrowingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImpl implements BorrowingRecordService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Override
    @Transactional
    @CacheEvict(value = "borrowingRecords", allEntries = true)
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());

        return borrowingRecordRepository.save(record);
    }

    @Override
    @Transactional
    @CacheEvict(value = "borrowingRecords", allEntries = true)
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (record == null) {
            throw new RuntimeException("Borrowing record not found");
        }
        if (record.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned");
        }
        record.setReturnDate(LocalDate.now());

        return borrowingRecordRepository.save(record);
    }
}
