package com.library_management_system.controller;

import com.library_management_system.entity.BorrowingRecord;
import com.library_management_system.service.BorrowingRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BorrowingRecordControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BorrowingRecordController(borrowingRecordService)).build();
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testBorrowBook() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L); // Ensure ID is set
        when(borrowingRecordService.borrowBook(1L, 1L)).thenReturn(record);

        mockMvc.perform(post("/api/borrow/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L)); // Ensure correct path
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testReturnBook() throws Exception {
        BorrowingRecord record = new BorrowingRecord();
        record.setId(1L); // Ensure ID is set
        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(record);

        mockMvc.perform(put("/api/return/1/patron/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L)); // Ensure correct path
    }
}
