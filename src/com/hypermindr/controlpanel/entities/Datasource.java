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
@Table(name = "datasource") 
public class Datasource extends SecurityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6995860985689169055L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String cname;
	
	private Boolean active;

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
		
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void criar() {
		ControlPanelFacade.getInstance().criarDatasource(this);
		
	}
	
	public void apagar() {
		ControlPanelFacade.getInstance().apagarDatasource(this.id);
		
	}
	
	
	public void atualizar() {
		ControlPanelFacade.getInstance().criarDatasource(this);
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cadastro efetuado","Cadastro efetuado"); 
		FacesContext.getCurrentInstance().addMessage(null, fm);
		
	}
	
	@PostConstruct
	public void init(){
		List<Datasource> datasources = ControlPanelFacade.getInstance().buscaDatasources();
		((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("datasources",datasources);
	}
	
	

}
