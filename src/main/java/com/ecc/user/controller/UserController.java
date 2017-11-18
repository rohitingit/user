/**
 * 
 */
package com.ecc.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;

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
	BaseResponse save(UserRequest userRequest, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;
	
	/**
	 * @param userRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws RequestException
	 */
	BaseResponse login(UserRequest userRequest, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;

	/**
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 * @throws RequestException
	 */
	BaseResponse findById(Long userId, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;

	/**
	 * @param email
	 * @param request
	 * @param response
	 * @return
	 * @throws RequestException
	 */
	BaseResponse findByEmail(String email, HttpServletRequest request, HttpServletResponse response)
			throws RequestException;

}
