package com.hypermindr.controlpanel.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogDetail implements Comparable<LogDetail>{
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
	
	private String _id;
	
	private String tracerid;
	
	private BigDecimal timestamp;
	
	private BigDecimal timeElapsed;
	
	private String module_name;
	
	private String class_name;
	
	private String method_name;
	
	private String message;
	
	private String server;
	
	private String stage;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTracerid() {
		return tracerid;
	}

	public void setTracerid(String tracerid) {
		this.tracerid = tracerid;
	}

	public BigDecimal getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigDecimal timestamp) {
		this.timestamp = timestamp;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	

	public BigDecimal getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(BigDecimal timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogDetail=> ");
		builder.append("<i>Tracerid: ").append(tracerid).append("</i> :: ");
		builder.append("Server: ").append(server).append(" :: ");
		builder.append("<b>Timestamp: ").append(formatter.format(new Date(timestamp.longValue()))).append("</b> :: ");
		builder.append("<b>Time elapsed: ").append(timeElapsed.toPlainString()).append("</b> :: ");
		builder.append("Module: ").append(module_name).append(" :: ");
		builder.append("Class: ").append(class_name).append(" :: ");
		builder.append("Method: ").append(method_name).append(" :: ");
		builder.append("Message: ").append(message).append("  <br/>  ");
		return builder.toString();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((class_name == null) ? 0 : class_name.hashCode());
		result = prime * result
				+ ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result
				+ ((method_name == null) ? 0 : method_name.hashCode());
		result = prime * result
				+ ((module_name == null) ? 0 : module_name.hashCode());
		result = prime * result + ((server == null) ? 0 : server.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result
				+ ((tracerid == null) ? 0 : tracerid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LogDetail))
			return false;
		LogDetail other = (LogDetail) obj;
		
		if (class_name == null) {
			if (other.class_name != null)
				return false;
		} else if (!class_name.equals(other.class_name))
			return false;
		if (formatter == null) {
			if (other.formatter != null)
				return false;
		} else if (!formatter.equals(other.formatter))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (method_name == null) {
			if (other.method_name != null)
				return false;
		} else if (!method_name.equals(other.method_name))
			return false;
		if (module_name == null) {
			if (other.module_name != null)
				return false;
		} else if (!module_name.equals(other.module_name))
			return false;
		if (server == null) {
			if (other.server != null)
				return false;
		} else if (!server.equals(other.server))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (tracerid == null) {
			if (other.tracerid != null)
				return false;
		} else if (!tracerid.equals(other.tracerid))
			return false;
		return true;
	}

	@Override
	public int compareTo(LogDetail o) {
		return timestamp.compareTo(o.getTimestamp());
	}
	
	public String getFormattedMethod () {
		return this.getModule_name()+":"+this.getClass_name()+":"+this.getMethod_name();
	}

	

}
