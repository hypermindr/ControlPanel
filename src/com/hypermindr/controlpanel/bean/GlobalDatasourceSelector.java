package com.hypermindr.controlpanel.bean;

import com.hypermindr.controlpanel.web.facade.ControlPanelFacade;

public class GlobalDatasourceSelector {

	static{
		datasource = ControlPanelFacade.getInstance().buscaDatasources().get(0).getCname();
	}
	
	private static String datasource;

	public static String getDatasource() {
		return datasource;
	}

	public static void setDatasource(String datasource) {
		GlobalDatasourceSelector.datasource = datasource;
	}
	
	
	
}
