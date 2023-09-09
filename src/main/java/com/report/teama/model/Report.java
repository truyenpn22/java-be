package com.report.teama.model;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import java.lang.Long;
import java.time.LocalDateTime;
@Entity
@Table(name = "reports")
public class Report {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    public LocalDateTime getLast_time_update() {
		return last_time_update;
	}

	public void setLast_time_update(LocalDateTime last_time_update) {
		this.last_time_update = last_time_update;
	}

	public String getName_modify() {
		return name_modify;
	}

	public void setName_modify(String name_modify) {
		this.name_modify = name_modify;
	}

	@Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private LocalDateTime  create_time;
    
    @Column(nullable = false)
    private LocalDateTime  last_time_update;
    
    @Column(nullable = false)
    private String name_modify;

    @JoinColumn(name = "auth")
    @ManyToOne
    private User auth;
    
    
	public User getAuth() {
		return auth;
	}

	public void setAuth(User auth) {
		this.auth = auth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreate_time() {
		return create_time;
	}

	public void setCreate_time(LocalDateTime create_time) {
		this.create_time = create_time;
	}

}
