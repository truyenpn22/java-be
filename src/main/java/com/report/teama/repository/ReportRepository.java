package com.report.teama.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.report.teama.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Các phương thức truy vấn sẽ được Spring Data JPA tạo tự động

}