/**
 * 
 */
package com.ecc.user.handler;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecc.user.entity.User;
import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.service.UserService;
import com.shared.common.exception.RequestException;
import com.shared.common.exception.UnauthorizedException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;

/**
 * @author ROHIT
 *
 */
@Service("userRequestHandler")
public class UserRequestHandler extends BaseRequestHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageByLocale messageByLocale;

	@Autowired
	private UserService userService;

	/**
	 * @param userRequest
	 * @return
	 * @throws RequestException
	 */
	public UserResponse save(UserRequest userRequest) throws RequestException {
		logger.info(" save ");
		UserResponse userResponse = new UserResponse();
		User user = converUserRequestToEntity(userRequest);
		user.setPassword(userRequest.getPassword());
		Long userId = userService.save(user);
		userResponse = converUserEntityToResponse(userService.findById(userId));
		userResponse.setStatus(HttpStatus.CREATED.value());
		userResponse.setResponseMessage(
				messageByLocale.getMessage("record.success", messageByLocale.getMessage("record.created")));
		return userResponse;
	}

	/**
	 * @param userRequest
	 * @return
	 * @throws RequestException
	 */
	public BaseResponse login(UserRequest userRequest) throws RequestException {
		BaseResponse userResponse = new BaseResponse();
		User user = userService.findByEmail(userRequest.getEmail());
		if (!BCrypt.checkpw(userRequest.getPassword(), user.getPassword())) {
			throw new UnauthorizedException(messageByLocale.getMessage("login.failed"));
		}
		userResponse.setStatus(HttpStatus.OK.value());
		userResponse.setResponseMessage(messageByLocale.getMessage("login.success"));
		return userResponse;
	}

	/**
	 * @param userId
	 * @return
	 * @throws RequestException
	 */
	@Transactional
	public UserResponse findById(Long userId) throws RequestException {
		logger.info(" findById ");
		UserResponse userResponse = new UserResponse();
		userResponse = converUserEntityToResponse(userService.findById(userId));
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}

	/**
	 * @param email
	 * @return
	 * @throws RequestException
	 */
	@Transactional
	public UserResponse findByEmail(String email) throws RequestException {
		logger.info(" findByEmail ");
		UserResponse userResponse = new UserResponse();
		userResponse = converUserEntityToResponse(userService.findByEmail(email));
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}

}
