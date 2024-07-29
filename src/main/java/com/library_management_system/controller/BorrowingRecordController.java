package com.library_management_system.controller;

import com.library_management_system.entity.BorrowingRecord;
import com.library_management_system.service.BorrowingRecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.borrowBook(bookId, patronId);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord record = borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(record);
    }
}
