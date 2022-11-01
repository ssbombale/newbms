package com.user.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerLoanApply")
public class CustomeLoanApply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer loanId;
	private String loanType;
	private BigDecimal loanAmount;
	private Date applyDate;
	private Integer durationOFLoan;
	private BigDecimal rateOfInterest;
	private Boolean status;
	private String activateStatus;
	private Integer custId;
	
	
	public CustomeLoanApply(Integer loanId, String loanType, BigDecimal loanAmount, Date applyDate,
			Integer durationOFLoan, BigDecimal rateOfInterest, Boolean status, String activateStatus, Integer custId) {
		super();
		this.loanId = loanId;
		this.loanType = loanType;
		this.loanAmount = loanAmount;
		this.applyDate = applyDate;
		this.durationOFLoan = durationOFLoan;
		this.rateOfInterest = rateOfInterest;
		this.status = status;
		this.activateStatus = activateStatus;
		this.custId = custId;
	}
	public CustomeLoanApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Integer getDurationOFLoan() {
		return durationOFLoan;
	}
	public void setDurationOFLoan(Integer durationOFLoan) {
		this.durationOFLoan = durationOFLoan;
	}
	public BigDecimal getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(BigDecimal rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getActivateStatus() {
		return activateStatus;
	}
	public void setActivateStatus(String activateStatus) {
		this.activateStatus = activateStatus;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}


	
}
