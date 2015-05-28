package com.hypermindr.controlpanel.entities;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import com.hypermindr.controlpanel.entities.materialized.SecurityBean;
import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;

@Entity
@Table(name = "endpoint") 
public class Endpoint extends SecurityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995860985689169055L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String cname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	public void criar() {
		ControlPanelFacade.getInstance().criarEndpoint(this);
		
	}
	
	public void apagar() {
		ControlPanelFacade.getInstance().apagarEndpoint(this.id);
		
	}
	
	
	public void atualizar() {
		ControlPanelFacade.getInstance().atualizaEndpoint(this);
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastro efetuado","Cadastro efetuado"); 
		FacesContext.getCurrentInstance().addMessage(null, fm);
		
	}
	
	@PostConstruct
	public void init(){
		List<Endpoint> endpoints = ControlPanelFacade.getInstance().buscaEndpoints();
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("endpoints",endpoints);
	}
	
	

}
