/**
 * 
 */
package com.ecc.user.service.impl;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
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
import com.ecc.user.service.LoginService;
import com.shared.common.exception.ConflictException;
import com.shared.common.exception.UnauthorizedException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;

/**
 * @author Rohit
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageByLocale messageByLocale;

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public BaseResponse save(UserRequest userRequest) throws ConflictException {
		logger.debug(" save ");
		UserResponse userResponse = new UserResponse();
		Login existLogin = loginDAO.findByEmail(userRequest.getEmail());
		if (existLogin != null) {
			throw new ConflictException(messageByLocale.getMessage("user.email.exist"));
		}
		Login login = converUserRequestToLoginEntity(userRequest);
		login.setPassword(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()));
		loginDAO.save(login);
		userResponse.setStatus(HttpStatus.CREATED.value());
		userResponse.setResponseMessage(
				messageByLocale.getMessage("record.success", messageByLocale.getMessage("record.created")));
		return userResponse;
	}

	@Override
	@Transactional
	public BaseResponse validate(String email, String password) throws UnauthorizedException {
		UserResponse userResponse = new UserResponse();
		Login login = loginDAO.findByEmail(email);
		if (login == null) {
			throw new UnauthorizedException(messageByLocale.getMessage("login.failed"));
		}
		if (!BCrypt.checkpw(password, login.getPassword())) {
			throw new UnauthorizedException(messageByLocale.getMessage("login.failed"));
		}
		User user = userDAO.findByEmail(login.getEmail());
		if (user != null) {
			userResponse = converUserEntityToResponse(user);
		}
		userResponse.setStatus(HttpStatus.OK.value());
		userResponse.setResponseMessage(messageByLocale.getMessage("login.success"));
		return userResponse;
	}

}
