/**
 * 
 */
package com.ecc.user.controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecc.user.controller.UserController;
import com.ecc.user.handler.UserRequestHandler;
import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.util.EnumUtils.RequiredFields;
import com.ecc.user.validation.APIRequestValidator;
import com.shared.common.exception.BadRequestException;
import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;

/**
 * @author ROHIT
 *
 */
@RestController
public class UserControllerImpl extends AbstractControllerImpl implements UserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageByLocale messageByLocale;
	
	@Autowired
	private UserRequestHandler userRequestHandler; 
	
	@Override
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST)
	public BaseResponse save(@RequestBody UserRequest userRequest, HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" save ");
		APIRequestValidator apiRequestValidator = new APIRequestValidator(messageByLocale);
		apiRequestValidator.validateSave(userRequest);
		if (apiRequestValidator.hasErrors()) {	
		    String errorString = apiRequestValidator.getErrors();
			throw new BadRequestException(errorString);
		}
		return userRequestHandler.save(userRequest);
	}
	
	@Override
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public BaseResponse findById(@PathVariable("userId") Long userId , HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" findById ");
		APIRequestValidator apiRequestValidator = new APIRequestValidator(messageByLocale);
		apiRequestValidator.validateLong(RequiredFields.userId.name(), userId);
		if (apiRequestValidator.hasErrors()) {	
		    String errorString = apiRequestValidator.getErrors();
			throw new BadRequestException(errorString);
		}
		return userRequestHandler.findById(userId);
	}
	
	@Override
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public BaseResponse login(@RequestBody UserRequest userRequest , HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" login ");
		APIRequestValidator apiRequestValidator = new APIRequestValidator(messageByLocale);
		apiRequestValidator.validateLogin(userRequest);
		if (apiRequestValidator.hasErrors()) {	
		    String errorString = apiRequestValidator.getErrors();
			throw new BadRequestException(errorString);
		}
		return userRequestHandler.login(userRequest);
	}
	
	@Override
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/findByEmail/{email:.+}", method=RequestMethod.GET)
	public BaseResponse findByEmail(@PathVariable("email") String email , HttpServletRequest request, HttpServletResponse response) throws RequestException{
		logger.info(" findByEmail ");
		APIRequestValidator apiRequestValidator = new APIRequestValidator(messageByLocale);
		apiRequestValidator.validateString(RequiredFields.email.name(), email);
		if (apiRequestValidator.hasErrors()) {	
		    String errorString = apiRequestValidator.getErrors();
			throw new BadRequestException(errorString);
		}
		return userRequestHandler.findByEmail(email);
	}
}
