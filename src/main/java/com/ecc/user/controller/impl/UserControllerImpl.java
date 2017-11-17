/**
 * 
 */
package com.ecc.user.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecc.user.controller.UserController;
import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;

/**
 * @author ROHIT
 *
 */
@RestController
public class UserControllerImpl extends AbstractControllerImpl implements UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	@RequestMapping(method=RequestMethod.POST)
	public BaseResponse createUser(@RequestBody UserRequest userRequest, HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" createUser ");
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setResponseMessage("created successfully");
		return baseResponse;
	}
	
	@Override
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public UserResponse userById(@PathVariable("userId") String userId , HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" userById ");
		UserResponse userResponse = new UserResponse();
		userResponse.setResponseMessage("created successfully");
		return userResponse;
	}
}
