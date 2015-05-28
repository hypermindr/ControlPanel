package com.hypermindr.controlpanel.persistance.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.hypermindr.controlpanel.entities.Endpoint;
import com.hypermindr.controlpanel.persistance.service.base.BaseService;

public class EndpointService extends BaseService {

	public void criar(Endpoint endpoint) {
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		session.persist(endpoint);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

	public List<Endpoint> listarTodos() {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria criteria = session.createCriteria(Endpoint.class);
		criteria.setCacheable(true);
		return (List<Endpoint>) criteria.list();
	}

	public Endpoint buscaPorId(Integer endpointId) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria criteria = session.createCriteria(Endpoint.class);
		criteria.add(Restrictions.eq("id", endpointId));
		criteria.setCacheable(true);
		List<Endpoint> list = criteria.list();
		return list.get(0);
	}

	public void atualizar(Endpoint endpoint) {
		// TODO Auto-generated method stub

	}

	public void apagar(Integer id) {
		
		Session session = (Session) getEntityManager().getDelegate();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Endpoint.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setCacheable(true);
		List<Endpoint> list = criteria.list();
		Endpoint endpoint = list.get(0);
		session.delete(endpoint);
		session.getTransaction().commit();
		session.flush();
		session.close();
	}

}
