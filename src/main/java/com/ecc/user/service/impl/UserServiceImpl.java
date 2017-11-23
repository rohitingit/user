/**
 * 
 */
package com.ecc.user.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecc.user.dao.LoginDAO;
import com.ecc.user.dao.UserDAO;
import com.ecc.user.entity.Login;
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
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public BaseResponse save(UserRequest userRequest) throws RequestException {
		logger.info(" save ");
		UserResponse userResponse = new UserResponse();
		Login login = null;
		User user = null;
		login = loginDAO.findByEmail(userRequest.getEmail());
		if (login == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		user = userDAO.findByEmail(userRequest.getEmail());
		if (user == null) {/** create user **/
			user = convertUserRequestToEntity(userRequest);
			user.setCreatedDate(new Date());
		} else {/** update user **/
			user = updatedUserEntity(user, userRequest);
		}
		user.setModifiedDate(new Date());
		user.setLogin(login);
		user = userDAO.save(user);
		userResponse = convertUserEntityToResponse(user);
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
		userResponse = convertUserEntityToResponse(user);
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
		userResponse = convertUserEntityToResponse(user);
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}
}
