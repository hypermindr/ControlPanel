package com.hypermindr.controlpanel.persistance.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hypermindr.controlpanel.entities.Datasource;
import com.hypermindr.controlpanel.persistance.service.base.BaseService;

public class DatasourceService extends BaseService {

	public void criar(Datasource datasource) {
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		session.persist(datasource);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public List<Datasource> listarTodos() {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria criteria = session.createCriteria(Datasource.class);
		criteria.setCacheable(true);
		return (List<Datasource>) criteria.list();
	}

	public Datasource buscaPorId(Integer endpointId) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria criteria = session.createCriteria(Datasource.class);
		criteria.add(Restrictions.eq("id", endpointId));
		criteria.setCacheable(true);
		List<Datasource> list = criteria.list();
		return list.get(0);
	}

	public void atualizar(Datasource endpoint) {
		// TODO Auto-generated method stub

	}

	public void apagar(Integer id) {
		
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Datasource.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setCacheable(true);
		List<Datasource> list = criteria.list();
		Datasource endpoint = list.get(0);
		session.delete(endpoint);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

}
