package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="announcements")
public class Announcement extends BaseEntity{

	@NotEmpty
	@Column(name = "body")
	private String body;
	
	@NotEmpty
	@Column(name = "header")
	private String header;
	
	@NotEmpty
	@Column(name = "tag")
	private String tag;
	
	
	@Column(name = "announcement_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "vet_id")
	private Vet vet;
}
