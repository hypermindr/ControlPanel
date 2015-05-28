package com.hypermindr.controlpanel.persistance.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.hypermindr.controlpanel.entities.Login;
import com.hypermindr.controlpanel.persistance.service.base.BaseService;

public class LoginService extends BaseService{

	public List<Login> buscarPorExemplo(Login usuario) {
		Session session = (Session) getEntityManager().getDelegate();  
    	Example example = super.createExample(usuario);
    	Criteria criteria = session.createCriteria(Login.class);
    	criteria.add(example);
    	criteria.setCacheable(true);
    	return (List<Login>) criteria.list();
	}

}
