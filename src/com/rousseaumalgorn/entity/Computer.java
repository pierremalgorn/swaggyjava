package com.rousseaumalgorn.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "computer")
public class Computer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name = "id", unique=true, nullable = false)
	private Long id;
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date introduced;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinued;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	public Computer() {
		
	}
	
	public Computer(Long id, String name, Date introduced, Date discontinued,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		if(introduced == null) {
			return null;
		}
		return introduced.toString().substring(0, 10);
	}
	public void setIntroduced(String introduced) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.introduced = formatter.parse(introduced);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getDiscontinued() {
		if(discontinued == null) {
			return null;
		}
		return discontinued.toString().substring(0, 10);
	}
	public void setDiscontinued(String discontinued) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.discontinued = formatter.parse(discontinued);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}