package com.library_management_system.service.impl;

import com.library_management_system.entity.Patron;
import com.library_management_system.repository.PatronRepository;
import com.library_management_system.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;

    @Override
    @Cacheable(value = "patrons", unless = "#result == null")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Override
    @Cacheable(value = "patrons", key = "#id")
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new RuntimeException("Patron not found"));
    }

    @Override
    @CacheEvict(value = "patrons", allEntries = true)
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    @CacheEvict(value = "patrons", allEntries = true)
    public Patron updatePatron(Long id, Patron updatedPatron) {
        Patron oldpatron = getPatronById(id);
        oldpatron.setName(updatedPatron.getName());
        oldpatron.setContactInformation(updatedPatron.getContactInformation());
        return patronRepository.save(oldpatron);
    }

    @Override
    @CacheEvict(value = "patrons", allEntries = true)
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
