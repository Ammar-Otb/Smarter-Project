package com.example.capstoneproject.report;

import com.example.capstoneproject.DTO.ReportDTO;
import com.example.capstoneproject.session.MySession;
import com.example.capstoneproject.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final SessionService sessionService;
    public List getReports(){
        return reportRepository.findAll();
    }
    public void createReport(Report report){
        reportRepository.save(report);
    }
    public Report findReportById(Integer id){
        return reportRepository.findById(id).get();
    }

//    public void addReport(ReportDTO reportDTO){
//        MySession session = sessionService.findSession(reportDTO.getSessionId());
//        Report report = new Report(null,reportDTO.getDate(), reportDTO.getReportDetails(), session);
//        reportRepository.save(report);
//    }
}
