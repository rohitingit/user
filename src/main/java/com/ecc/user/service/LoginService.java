/**
 * 
 */
package com.ecc.user.service;

import com.shared.common.exception.ConflictException;
import com.shared.common.exception.NotFoundException;
import com.shared.common.exception.UnauthorizedException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;

/**
 * @author Rohit
 *
 */
public interface LoginService extends BaseApiService {

	/**
	 * @param login
	 * @return
	 * @throws ConflictException
	 */
	public BaseResponse save(UserRequest userRequest) throws ConflictException;

	/**
	 * @param email
	 * @return
	 * @throws NotFoundException
	 */
	public BaseResponse validate(String email, String password) throws UnauthorizedException;

}
