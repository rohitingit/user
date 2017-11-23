/**
 * 
 */
package com.ecc.user.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecc.user.dao.UserDAO;
import com.ecc.user.entity.User;
import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.service.UserService;
import com.shared.common.exception.NotFoundException;
import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;

/**
 * @author ROHIT
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageByLocale messageByLocale;

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public BaseResponse save(UserRequest userRequest) throws RequestException {
		logger.info(" save ");
		UserResponse userResponse = new UserResponse();
		User pesistUser = null;
		try {
			pesistUser = userDAO.findByEmail(userRequest.getEmail());
			return converUserEntityToResponse(userDAO.save(pesistUser));
		} catch (Exception e) {
			logger.debug("user not exist");
		}
		pesistUser = userDAO.save(converUserRequestToEntity(userRequest));
		userResponse = converUserEntityToResponse(pesistUser);
		return userResponse;
	}

	@Override
	@Transactional
	public BaseResponse findById(Long userId) throws NotFoundException {
		logger.info(" findById ");
		UserResponse userResponse = new UserResponse();
		User user = userDAO.findById(userId);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		userResponse = converUserEntityToResponse(user);
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}

	@Override
	@Transactional
	public BaseResponse findByEmail(String email) throws NotFoundException {
		logger.info(" findByEmail ");
		UserResponse userResponse = new UserResponse();
		User user = userDAO.findByEmail(email);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		userResponse = converUserEntityToResponse(user);
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}
}
