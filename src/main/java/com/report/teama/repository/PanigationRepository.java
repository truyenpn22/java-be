package com.report.teama.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.teama.model.Report;

@Repository
public interface PanigationRepository extends JpaRepository<Report, Long> {
	 Page<Report> findAll(Pageable pageable);
}