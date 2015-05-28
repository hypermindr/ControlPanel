package com.hypermindr.controlpanel.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;




public class HolderBean {

	public Map<String, BigDecimal> getMethodsTime() {
		return methodsTime;
	}

	public void setMethodsTime(Map<String, BigDecimal> methodsTime) {
		this.methodsTime = methodsTime;
	}

	private BigDecimal timeElapsed;
	
	private BigDecimal deviation;
	
	private String details;
	
	private String timestamp;
	
	
	
	private Map<String, BigDecimal> methodsTime;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public HolderBean(BigDecimal timeelapsed2, String logDetailsString, String time) {
		this.timeElapsed = timeelapsed2;
		this.details = logDetailsString;
		this.timestamp = time;
	}

	public BigDecimal getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(BigDecimal timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getDeviation() {
		return deviation;
	}

	public void setDeviation(BigDecimal deviation) {
		this.deviation = deviation;
	}

}
