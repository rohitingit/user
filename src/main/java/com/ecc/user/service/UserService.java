/**
 * 
 */
package com.ecc.user.service;

import com.shared.common.exception.NotFoundException;
import com.shared.common.exception.RequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;

/**
 * @author ROHIT
 *
 */
public interface UserService extends BaseApiService{

	/**
	 * @param userRequest
	 * @return
	 */
	public BaseResponse save(UserRequest userRequest) throws RequestException;

	/**
	 * @param userId
	 * @return
	 * @throws NotFoundException
	 */
	public BaseResponse findById(Long userId) throws NotFoundException;

	/**
	 * @param email
	 * @return
	 * @throws NotFoundException
	 */
	public BaseResponse findByEmail(String email) throws NotFoundException;

}
