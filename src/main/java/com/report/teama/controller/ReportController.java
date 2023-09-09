package com.report.teama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.report.teama.model.Report;
import com.report.teama.repository.ReportRepository;
import com.report.teama.service.ReportService;
import com.report.teama.Interface.CustomResponse;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = {"authorization","content-type"})
@RestController
@RequestMapping("/api/reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportRepository reportRepository;


	@GetMapping
	public ResponseEntity<CustomResponse> getAllReports
			(@RequestParam(name = "page" ) int page,
			@RequestParam(name = "size") int size) 
			
	{
		 Pageable pageable = PageRequest.of(page - 1, size);
		try {
			List<Report> totalItem = reportRepository.findAll();
			Page<Report> a = reportService.getAllReports(pageable);
			CustomResponse customResponse = new CustomResponse(1000, "Find All report successfully!", a.getContent());
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse(2000, "Failed to get all report: " + e.getMessage(),
					null);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		}

	}

	@PostMapping
	public ResponseEntity<CustomResponse> createReport(@RequestBody Report report) {
		CustomResponse customResponse;

		try {
			customResponse = new CustomResponse(1000, "Report created successfully!", null);

			Object auth = report.getAuth();

			if (auth == null) {
				customResponse.setMessage("Auth is not empty!");
				customResponse.setStatusCode(2000);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}
			Report savedReport = reportRepository.save(report);
			customResponse.setData(savedReport);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		} catch (Exception e) {
			customResponse = new CustomResponse(2000, "Failed to create report: " + e.getMessage(), null);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomResponse> getReportById(@PathVariable Long id) {
		Report report = reportRepository.findById(id).orElse(null);
		try {
			if (report != null) {
				CustomResponse customResponse = new CustomResponse(1000, "Report found.", report);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			} else {
				CustomResponse customResponse = new CustomResponse(2000, "Report not found.", null);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse(2000, "Failed get report by id: " + e.getMessage(),
					null);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomResponse> updateReport(@PathVariable Long id, @RequestBody Report updatedReport) {
		java.util.Optional<Report> existingReportOptional = reportRepository.findById(id);
		try {
			if (existingReportOptional.isPresent()) {
				Report existingReport = existingReportOptional.get();
				existingReport.setTitle(updatedReport.getTitle());
				existingReport.setDescription(updatedReport.getDescription());
				existingReport.setLast_time_update(updatedReport.getLast_time_update());
				existingReport.setName_modify(updatedReport.getName_modify());
				Report savedReport = reportRepository.save(existingReport);
				CustomResponse customResponse = new CustomResponse(1000, "Report updated successfully.", savedReport);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			} else {
				CustomResponse customResponse = new CustomResponse(2000, "Report not found.", null);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse(2000, "Failed update report: " + e.getMessage(), null);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomResponse> deleteReport(@PathVariable Long id) {
		java.util.Optional<Report> existingReportOptional = reportRepository.findById(id);
		try {

			if (existingReportOptional.isPresent()) {
				reportRepository.deleteById(id);
				CustomResponse customResponse = new CustomResponse(1000, "Report deleted successfully.", null);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			} else {
				CustomResponse customResponse = new CustomResponse(2000, "Report not found.", null);
				return new ResponseEntity<>(customResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			CustomResponse customResponse = new CustomResponse(2000, "Failed delete report: " + e.getMessage(), null);
			return new ResponseEntity<>(customResponse, HttpStatus.OK);
		}
	}

}
