/**
 * 
 */
package com.ecc.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecc.user.dao.DocumentDAO;

/**
 * @author Rohit
 *
 */
@Repository("documentDAO")
public class DocumentDAOImpl implements DocumentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteById(Long id) {
		Query query = getSession().createQuery("DELETE from Document where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
	}
	
	@Override
	public void deleteByIds(List<Long> ids) {
		for (Long id : ids) {
			deleteById(id);
		}
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
