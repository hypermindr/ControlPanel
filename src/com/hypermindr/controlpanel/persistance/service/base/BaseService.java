package com.hypermindr.controlpanel.persistance.service.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

public class BaseService {
	
	protected EntityManagerFactory factory;
	
    protected BaseService() {
        factory = Persistence.createEntityManagerFactory("hyperAdminDS");
      
    }    
    
    protected EntityManager getEntityManager() {
        return factory.createEntityManager();
}    

	protected final Example createExample(Object objeto) {
		Example example = Example.create(objeto); 
		example.enableLike(MatchMode.ANYWHERE);  
		example.excludeZeroes();  
		example.ignoreCase();  
		return example; 
	}

}
