package com.hypermindr.controlpanel.entities;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import com.hypermindr.controlpanel.entities.materialized.SecurityBean;
import com.hypermindr.controlpanel.web.e.LoginControlPanelException;
import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;

@Entity
@Table(name = "login") 
public class Login extends SecurityBean implements Serializable {
	@Id
	private Integer id;

	private String login;

	private String senha;

	private String nome;

	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}
	public Login(String login2, String senha2) {
		this.login = login2;
		this.senha = senha2;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public String executaLogin() {
		try{
			ControlPanelFacade.getInstance().executaLogin(login,senha,this);
			((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("userId",this.getId());
            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("login",this.getLogin());
            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("nome",this.getNome());
            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).setAttribute("password",this.getSenha());
            addAToken();
            return "login";  
		}catch(LoginControlPanelException lae){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL,lae.getLocalizedMessage(),"Falha de login de novo");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return "fail";
        }

	}

}
