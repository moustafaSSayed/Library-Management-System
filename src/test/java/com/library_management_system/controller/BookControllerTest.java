package com.library_management_system.controller;

import com.library_management_system.entity.Book;
import com.library_management_system.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
public class BookControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private List<Book> bookList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new BookController(bookService))
                .apply(sharedHttpSession())
                .build();

        bookList = Arrays.asList(
                new Book(1L, "Title 1", "Author 1", 2020, "1234567890"),
                new Book(2L, "Title 2", "Author 2", 2021, "0987654321")
        );
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(bookList.size()))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testGetBookById() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookList.get(0));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title 1"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testAddBook() throws Exception {
        Book newBook = new Book(null, "New Title", "New Author", 2022, "1112223334");
        when(bookService.addBook(any(Book.class))).thenReturn(new Book(3L, "New Title", "New Author", 2022, "1112223334"));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"New Title\",\"author\":\"New Author\",\"publicationYear\":2022,\"isbn\":\"1112223334\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book(1L, "Updated Title", "Updated Author", 2020, "1234567890");
        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Title\",\"author\":\"Updated Author\",\"publicationYear\":2020,\"isbn\":\"1234567890\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }
}
