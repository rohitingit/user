/**
 * 
 */
package com.ecc.user.dao.impl;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecc.user.dao.LoginDAO;
import com.ecc.user.entity.Login;

/**
 * @author Rohit
 *
 */
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long save(Login login) {
		login.setCreatedDate(new Date());
		return (Long) getSession().save(login);
	}

	@Override
	public Login findByEmail(String email) {
		Criteria criteria = getSession().createCriteria(Login.class);
		criteria.add(Restrictions.eq("email", email));
		return (Login) criteria.uniqueResult();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
