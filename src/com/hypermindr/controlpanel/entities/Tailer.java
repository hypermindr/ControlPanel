package com.hypermindr.controlpanel.entities;

import java.util.Date;

import com.ibm.icu.util.Calendar;


public class Tailer {

	private String _id;
	
	private String lastEvent;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getLastEvent() {
		return new Date(Long.parseLong(lastEvent.trim())).toString();
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
	
}
