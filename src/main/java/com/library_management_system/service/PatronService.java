package com.library_management_system.service;

import com.library_management_system.entity.Patron;

import java.util.List;

public interface PatronService {

    public List<Patron> getAllPatrons();
    public Patron getPatronById(Long id);
    public Patron addPatron(Patron patron);
    public Patron updatePatron(Long id, Patron updatedPatron);
    public void deletePatron(Long id);
}
