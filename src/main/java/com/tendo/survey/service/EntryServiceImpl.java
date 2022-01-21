package com.tendo.survey.service;

import com.tendo.survey.model.Entry;
import com.tendo.survey.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepository repository;

    @Override
    public Entry addEntry(Entry entry) {
        return repository.save(entry);
    }
    @Override
    public Entry getEntryById(Long entryId) {
        return repository.findById(entryId).get();
    }
    @Override
    public List<Entry> getAllEntries(){
        return repository.findAll();
    }

    @Override
    public Optional<Entry> findEntryByPatient_Id(String patientId) {
        return repository.findEntryByPatient_Id(patientId);
    }

    @Override
    public void updateEntry(Entry entry) {
        Optional<Entry> entryDB = repository.findById(entry.getId());
        repository.save(entry);
    }

    @Override
    public void deleteEntryById(Long reportId) {
        try {
            repository.deleteById(reportId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
