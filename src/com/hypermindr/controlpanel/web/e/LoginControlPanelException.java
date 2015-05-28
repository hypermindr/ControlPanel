package com.hypermindr.controlpanel.web.e;

public class LoginControlPanelException extends Exception{
	

	 public Exception otherException;
	    /** Creates a new instance of LoginAdminException */
	    public LoginControlPanelException() {
	    	super();
	        
	    }
	    
	    public LoginControlPanelException(String s) {
	    	super(s);
	        
	    }
	    
	     public LoginControlPanelException(Exception e) {
	        this.otherException = e;
	    }

}
