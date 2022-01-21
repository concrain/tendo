package com.tendo.survey.service;

import com.tendo.survey.model.Report;

import java.util.List;

public interface ReportService {

    Report addReport(Report report);
    Report getReportById(Long reportId);
    void updateReport(Report report);
    void deleteReportById(Long reportId);
    List<Report> getAllReports();
}
