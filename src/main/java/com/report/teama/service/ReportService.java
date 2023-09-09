package com.report.teama.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.report.teama.model.Report;

@Service
public interface ReportService {

//	public List<Report> getData();
    Page<Report> getAllReports(Pageable pageable);

}
