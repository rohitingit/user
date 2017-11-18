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

import com.ecc.user.dao.UserDAO;
import com.ecc.user.entity.User;

/**
 * @author Rohit
 *
 */
@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long save(User user){
		user.setCreatedDate(new Date());
		return (Long) getSession().save(user);
	}
	
	@Override
	public User update(User user){
		return (User) getSession().merge(user);
	}
	
	@Override
	public User findById(Long userId){
		return getSession().get(User.class, userId);
	}
	
	@Override
	public User findByEmail(String email){
		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
