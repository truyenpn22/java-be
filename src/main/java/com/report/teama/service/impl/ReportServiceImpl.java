package com.report.teama.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.report.teama.model.Report;
import com.report.teama.repository.ReportRepository;
import com.report.teama.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportRepository reportRepository;

	@Override
	public Page<Report> getAllReports(Pageable pageable) {
		return reportRepository.findAll(pageable);
	}
}
