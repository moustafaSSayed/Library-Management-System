package com.library_management_system.controller;

import com.library_management_system.entity.Patron;
import com.library_management_system.service.PatronService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@AllArgsConstructor
public class PatronController {

    @Autowired
    PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PostMapping
    public Patron addPatron(@RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public Patron updatePatron(@PathVariable Long id, @RequestBody Patron patron) {
        return patronService.updatePatron(id, patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
    }
}
