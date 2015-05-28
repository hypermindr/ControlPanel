package com.hypermindr.controlpanel.entities.materialized;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SecurityBean {
	
	
	 
    /** Creates a new instance of SecurityBean */
    public SecurityBean() {
    }
    
    public void addAToken() {
         ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("atoken",new com.hypermindr.controlpanel.security.SessionToken());
    }

}