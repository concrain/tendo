package com.tendo.survey.service;

import com.tendo.survey.model.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryService {

    Entry addEntry(Entry entry);
    Entry getEntryById(Long entryId);
    void updateEntry(Entry entry);
    void deleteEntryById(Long entryId);
    List<Entry> getAllEntries();
    Optional<Entry> findEntryByPatient_Id(String patientId);
}
