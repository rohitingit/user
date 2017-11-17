/**
 * 
 */
package com.ecc.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;

/**
 * @author ROHIT
 *
 */
public interface UserController {

	/**
	 * @param userRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws RequestException
	 */
	BaseResponse createUser(UserRequest userRequest, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;

	/**
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 * @throws RequestException
	 */
	UserResponse userById(String userId, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;

}
