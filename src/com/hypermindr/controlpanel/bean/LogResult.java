package com.hypermindr.controlpanel.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LogResult {
	
	
	private String _id;
	
	private BigDecimal maxtimestamp;
	
	private BigDecimal mintimestamp;
	
	private Double countdocs;
	
	private Set<LogDetail> details;
	
	private BigDecimal timeelapsed;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public BigDecimal getMaxtimestamp() {
		return maxtimestamp;
	}

	public void setMaxtimestamp(BigDecimal maxtimestamp) {
		this.maxtimestamp = maxtimestamp;
	}

	public BigDecimal getMintimestamp() {
		return mintimestamp;
	}

	public void setMintimestamp(BigDecimal mintimestamp) {
		this.mintimestamp = mintimestamp;
	}

	public Double getCountdocs() {
		return countdocs;
	}

	public void setCountdocs(Double countdocs) {
		this.countdocs = countdocs;
	}

	public Set<LogDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<LogDetail> details) {
		this.details = details;
	}

	public BigDecimal getTimeelapsed() {
		return timeelapsed;
	}

	public void setTimeelapsed(BigDecimal timeelapsed) {
		this.timeelapsed = timeelapsed;
	}
	
	

	public String getLogDetailsString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tempo decorrido: ").append(timeelapsed).append(" segundos.  <br/><br/>");
		ArrayList<LogDetail> sortedList = new ArrayList<LogDetail>(details);
		Collections.sort(sortedList);
		for (LogDetail detail : sortedList) {
			builder.append(detail.toString());
		}
		
		return builder.toString();
	}
	
	
}
