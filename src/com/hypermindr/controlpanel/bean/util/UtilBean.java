package com.hypermindr.controlpanel.bean.util;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.hypermindr.controlpanel.bean.GlobalDatasourceSelector;
import com.hypermindr.controlpanel.entities.materialized.SecurityBean;
import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;

public class UtilBean extends SecurityBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7116490914545837409L;
	private ArrayList<SelectItem> listEndpoints;
	private ArrayList<SelectItem> listDatasources;
	private ArrayList<SelectItem> endPoints;
	private ArrayList<SelectItem> datasources;

	public ArrayList<SelectItem> getEndPoints() {
		endPoints = ControlPanelFacade.getInstance().buscaListaEndpoints();
		return endPoints;
	}

	public void setEndPoints(ArrayList<SelectItem> endPoints) {
		this.endPoints = endPoints;
	}
	
	
	public ArrayList<SelectItem> getDatasources() {
		datasources = ControlPanelFacade.getInstance().buscaListaDatasources();
		return datasources;
	}

	public void setDatasources(ArrayList<SelectItem> dss) {
		this.datasources = endPoints;
	}
	

	private void getOrUpdateEndPoints() {
		listEndpoints = ControlPanelFacade.getInstance().buscaListaEndpoints();
	}
	
	private void getOrUpdateDatasources() {
		listDatasources = ControlPanelFacade.getInstance().buscaListaDatasources();
	}
	
	
	public void selecionaDatasource(String ds) {
		GlobalDatasourceSelector.setDatasource(ds);
	}
}
