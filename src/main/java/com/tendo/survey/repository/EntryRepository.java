package com.tendo.survey.repository;

import com.tendo.survey.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    Optional<Entry> findEntryByPatient_Id(String patientId);
}

