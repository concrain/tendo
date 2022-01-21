package com.tendo.survey.service;

import com.tendo.survey.model.Report;
import com.tendo.survey.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository repository;

    @Override
    public Report addReport(Report report) {
        return repository.save(report);
    }
    @Override
    public Report getReportById(Long reportId) {
        return repository.findById(reportId).get();
    }
    @Override
    public List<Report> getAllReports(){
        return repository.findAll();
    }

    @Override
    public void updateReport(Report report) {
        Optional<Report> reportDB = repository.findById(report.getId());
        repository.save(report);
    }

    @Override
    public void deleteReportById(Long reportId) {
        try {
            repository.deleteById(reportId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
