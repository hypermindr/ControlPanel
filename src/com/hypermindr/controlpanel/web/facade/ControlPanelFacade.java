package com.hypermindr.controlpanel.web.facade;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.hypermindr.controlpanel.entities.Datasource;
import com.hypermindr.controlpanel.entities.Endpoint;
import com.hypermindr.controlpanel.entities.Login;
import com.hypermindr.controlpanel.persistance.service.DatasourceService;
import com.hypermindr.controlpanel.persistance.service.EndpointService;
import com.hypermindr.controlpanel.persistance.service.LoginService;
import com.hypermindr.controlpanel.web.e.LoginControlPanelException;


public class ControlPanelFacade extends ControlPanelConstantes{

	private static ControlPanelFacade instance = new ControlPanelFacade();
	
	private LoginService loginService = new LoginService();
	private EndpointService endPointService = new EndpointService();
	private DatasourceService datasourceService = new DatasourceService();
	
	private ControlPanelFacade() {
		
	}
	
	public static ControlPanelFacade getInstance(){
		return instance;
	}
	
	public String executaLogin(String login, String senha, Login up) throws LoginControlPanelException{
		Login loginBean = new Login(login,senha);
		List<Login> l  = loginService.buscarPorExemplo(loginBean);
		if (l.size() == 0) {
			throw new LoginControlPanelException("Usuario inválido");
		}else{
			for (Login user : l) {
				if (user.getLogin().equals(login)) {
					if (user.getSenha().equals(senha)) {
						up.setId(user.getId());
						up.setNome(user.getNome());
						up.setLogin(user.getLogin());
						return STR_LOGIN;
					}else{
						throw new LoginControlPanelException("Senha inválida");
					}
				}
			}
			throw new LoginControlPanelException("Usuário inválido");
		}
	}

	public ArrayList<SelectItem> buscaListaEndpoints() {
		List<Endpoint> listaEndpoint = endPointService.listarTodos();
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for(Endpoint endpoint:listaEndpoint) {
			ret.add(new SelectItem( endpoint.getCname(),endpoint.getCname()));
		}
		return ret;
	}

	public void criarEndpoint(Endpoint endpoint) {
		endPointService.criar(endpoint);
		
	}

	public List<Endpoint> buscaEndpoints() {
		return endPointService.listarTodos();
	}

	public void atualizaEndpoint(Endpoint endpoint) {
		endPointService.atualizar(endpoint);
		
	}

	public void apagarEndpoint(Integer id) {
		endPointService.apagar(id);
		
	}

	public void criarDatasource(Datasource datasource) {
		datasourceService.criar(datasource);
		
	}

	public void apagarDatasource(Integer id) {
		datasourceService.apagar(id);
		
	}

	public List<Datasource> buscaDatasources() {
		return datasourceService.listarTodos();
	}

	public ArrayList<SelectItem> buscaListaDatasources() {
		List<Datasource> listaDs = datasourceService.listarTodos();
		ArrayList<SelectItem> ret = new ArrayList<SelectItem>();
		for(Datasource ds :listaDs) {
			ret.add(new SelectItem( ds.getCname(),ds.getName()));
		}
		return ret;
	}
		

	
	
}
