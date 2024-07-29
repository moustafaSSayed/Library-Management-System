package com.library_management_system.controller;

import com.library_management_system.entity.Patron;
import com.library_management_system.service.PatronService;
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

@SpringBootTest
public class PatronControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PatronService patronService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new PatronController(patronService)).build();
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testGetAllPatrons() throws Exception {
        List<Patron> patrons = Arrays.asList(
                new Patron(1L, "John Doe", "john@example.com"),
                new Patron(2L, "Jane Doe", "jane@example.com")
        );
        when(patronService.getAllPatrons()).thenReturn(patrons);

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testGetPatronById() throws Exception {
        Patron patron = new Patron(1L, "John Doe", "john@example.com");
        when(patronService.getPatronById(1L)).thenReturn(patron);

        mockMvc.perform(get("/api/patrons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testAddPatron() throws Exception {
        Patron newPatron = new Patron(null, "New Patron", "new@example.com");
        when(patronService.addPatron(any(Patron.class))).thenReturn(new Patron(3L, "New Patron", "new@example.com"));

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Patron\",\"email\":\"new@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("New Patron"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testUpdatePatron() throws Exception {
        Patron updatedPatron = new Patron(1L, "Updated Patron", "updated@example.com");
        when(patronService.updatePatron(eq(1L), any(Patron.class))).thenReturn(updatedPatron);

        mockMvc.perform(put("/api/patrons/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Patron\",\"email\":\"updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Patron"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    public void testDeletePatron() throws Exception {
        doNothing().when(patronService).deletePatron(1L);

        mockMvc.perform(delete("/api/patrons/1"))
                .andExpect(status().isOk());

        verify(patronService, times(1)).deletePatron(1L);
    }
}
