package com.tendo.survey.command;

import com.tendo.survey.model.Doctor;
import com.tendo.survey.model.Entry;
import com.tendo.survey.model.Report;
import com.tendo.survey.parser.JSONParser;
import com.tendo.survey.service.DoctorServiceImpl;
import com.tendo.survey.service.EntryServiceImpl;
import com.tendo.survey.service.PatientServiceImpl;
import com.tendo.survey.service.ReportServiceImpl;
import com.tendo.survey.shell.InputReader;
import com.tendo.survey.shell.ShellHelper;
import com.tendo.survey.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Transactional
@ShellComponent
public class PromptCommand {

    @Autowired
    private ShellHelper shellHelper;

    @Autowired
    private InputReader inputReader;

    @Autowired
    private PatientServiceImpl patientService;

    @Autowired
    private DoctorServiceImpl doctorService;

    @Autowired
    private ReportServiceImpl reportService;

    @Autowired
    private EntryServiceImpl entryService;

    @Autowired
    private JSONParser jsonParser;

    private Optional<Entry> entry;

    private static final String FEEDBACK_ERROR = "This question can not be empty? Please enter value between 1-10.";
    private static final String VALUE_ERROR = "Please enter value between 1-10.";

    // BATCH PROCESS ======>
    @ShellMethod("\t\tImport data")
    public void importJSON() throws IOException {
        jsonParser.parseJSON();
    }

    // SURVEY ======>
    @ShellMethod("\t\tTake patient survey")
    public void createSurvey(@ShellOption({"name"}) String name) {
        entry = findEntry(name);
        if (entry.isPresent()) {
            Optional<Report> originalReport = entry.get().getReport();
            shellHelper.printInfo("Hello, please take a moment to record your feedback from your latest doctor visit.");
            if (originalReport.isPresent()) {
                shellHelper.printInfo(String.format("A patient report already exists for %s", name));
                shellHelper.printInfo("Do you want to update this report? \n");
                displayReport(name);
                do {
                    String updateBoolean = inputReader.prompt("yes/no ");
                    if (updateBoolean.equalsIgnoreCase("y") || updateBoolean.equalsIgnoreCase("yes")) {
                        updateSurvey(name, false);
                    } else {
                        return;
                    }
                } while (true);
            }
            updateSurvey(name, true);
        }
    }

    @ShellMethod("\t\tUpdate patient survey")
    public void updateSurvey(@ShellOption({"name", "boolean"}) String name, boolean newReport) {
        Report report;
        if (newReport) {
            report = (new Report());
            runQuestionOne(report);
            runQuestionTwo(report);
            runQuestionThree(report, name, true);
        } else {
            entry = findEntry(name);
            if (entry.isPresent()) {
                report = entry.get().getReport().get();
                runQuestionOne(report);
                runQuestionTwo(report);
                runQuestionThree(report, name, false);
            }
        }
    }

    private void runQuestionOne(Report report) {
        do {
            shellHelper.printInfo(String.format("Hi %s, on a scale of 1-10, would you recommend Dr" +
                    " %s to a friend or family member? 1 = Would not recommend, 10 = Would strongly recommend.",
                entry.get().getPatient().getFamilyName(), entry.get().getDoctor().getGivenName()));

            String doctorRecommendation = inputReader.prompt(" ");
            String regex = "^[1-10]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(doctorRecommendation);
            if (!StringUtils.hasText(doctorRecommendation)) {
                shellHelper.printWarning(FEEDBACK_ERROR);
            } else if (!matcher.matches()) {
                shellHelper.printWarning(VALUE_ERROR);
            } else {
                report.setDoctorRecommendation(Integer.parseInt(doctorRecommendation));
            }
        } while (report.getDoctorRecommendation() == null);
    }

    private void runQuestionTwo(Report report) {
        do {
            shellHelper.printInfo(String.format("Thank you. You were diagnosed with %s. " +
                    "Did Dr '%s' explain how to manage this diagnosis in a way you could understand?",
                entry.get().getDiagnosis().getSystem(), entry.get().getDoctor().getGivenName()));

            String diagnosisFeedback = inputReader.prompt(" ");
            if (StringUtils.hasText(diagnosisFeedback)) {
                report.setDiagnosisFeedback(diagnosisFeedback);
            } else {
                shellHelper.printWarning(FEEDBACK_ERROR);
            }
        } while (report.getDiagnosisFeedback() == null);
    }

    private void runQuestionThree(Report report, String name, boolean newReport) {
        do {
            shellHelper.printInfo(String.format("We appreciate the feedback, " +
                "one last question: how do you feel about being diagnosed with %s?",
                entry.get().getDiagnosis().getSystem()));

            String appointmentFeedback = inputReader.prompt(" ");
            if (StringUtils.hasText(appointmentFeedback)) {
                report.setAppointmentFeedback(appointmentFeedback);
            } else {
                shellHelper.printWarning(FEEDBACK_ERROR);
            }
        } while (report.getDiagnosisFeedback() == null);

        if (newReport) {
            reportService.addReport(report);
        } else {
            reportService.updateReport(report);
        }
        shellHelper.printInfo("\nThanks again! Here’s what we heard:");
        displayReport(name);
        //TODO make this a log
        shellHelper.printSuccess("---> SUCCESS created report with id:" + report.getId());
    }


    // REPORTS ======>
    //TODO add security to this method
    @ShellMethod("\t\tDisplay list of reports")
    public void reportList() {
        Iterable<Patient> patients = patientService.getAllPatients();
        for (Patient patient : patients) {
            displayReport(patient.getName());
        }
    }

    @ShellMethod("\tDisplay the report")
    public void displayReport(@ShellOption({"firstName", "lastName"}) String name) {
        entry = findEntry(name);
        StringBuilder sb = new StringBuilder();
        sb.append("\t=========================\n");
        sb.append("\tDoctor Recommendation:   ").append("\n").append(entry.get().getReport().get().getDoctorRecommendation()).append("\n");
        sb.append("\tDiagnosis Feedback:      ").append("\n").append(entry.get().getReport().get().getDiagnosisFeedback()).append("\n");
        sb.append("\tAppointment Feedback:    ").append("\n").append(entry.get().getReport().get().getAppointmentFeedback()).append("\n");
        sb.append("\t=========================");
        System.out.println(sb);
    }

    private Optional<Entry> findEntry(String name) {
        Optional<Patient> patient = patientService.getPatientByName(name);
        if (patient == null) {
            shellHelper.printWarning(String.format("We could not find patient %s in the database.", name));
        }
        return entryService.findEntryByPatient_Id(patient.get().getId());
    }

    //TODO add security to this method
    @ShellMethod("\t\tDisplay list of patients")
    public void patientList() {
        Iterable<Patient> patients = patientService.getAllPatients();
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("active", "Active");
        headers.put("first_name", "First Name");
        headers.put("last_name", "Last Name");
        headers.put("gender", "Gender");
        headers.put("birth_date", "Birth Date");
        headers.put("contacts", "Contacts");
        headers.put("addresses", "Addresses");

        TableModel model = new BeanListTableModel<>(patients, headers);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));
    }

    //TODO add security to this method
    @ShellMethod("\t\tDisplay list of doctors")
    public void doctorList() {
        Iterable<Doctor> doctors = doctorService.getAllDoctors();
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("resource_type", "Resource Type");
        headers.put("family_name", "Family Name");
        headers.put("given_name", "Given Name");

        TableModel model = new BeanListTableModel<>(doctors, headers);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));
    }

}
