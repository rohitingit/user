/**
 * 
 */
package com.ecc.user.service.impl;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecc.user.dao.UserDAO;
import com.ecc.user.entity.User;
import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.service.UserService;
import com.shared.common.exception.BadRequestException;
import com.shared.common.exception.ConflictException;
import com.shared.common.exception.NotFoundException;

/**
 * @author ROHIT
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private MessageByLocale messageByLocale;

	@Autowired
	private UserDAO userDAO;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Transactional
	public Long save(User user) throws ConflictException{
		logger.info(" save ");
		User existUser = userDAO.findByEmail(user.getEmail());
		if (existUser != null) {
			throw new ConflictException(messageByLocale.getMessage("user.email.exist"));
		}
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		return userDAO.save(user);
	}

	@Override
	@Transactional
	public User findById(Long userId) throws NotFoundException {
		logger.info(" findById ");
		User user = userDAO.findById(userId);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		return user;
	}

	@Override
	@Transactional
	public User findByEmail(String email) throws NotFoundException {
		logger.info(" findByEmail ");
		User user = userDAO.findByEmail(email);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		return user;
	}
	
	@Override
	@Transactional
	public void update(User user) {
		logger.info(" update ");
		userDAO.update(user);
	}
}
