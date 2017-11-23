/**
 * 
 */
package com.ecc.user.service;

import com.ecc.user.entity.Login;
import com.ecc.user.entity.User;
import com.shared.common.request.UserRequest;
import com.shared.common.response.UserResponse;

/**
 * @author Rohit
 *
 */
public interface BaseApiService {
	/**
	 * @param userRequest
	 * @return
	 */
	public default Login converUserRequestToLoginEntity(UserRequest userRequest) {
		Login login = new Login();
		login.setEmail(userRequest.getEmail());
		login.setUsername(userRequest.getUsername());
		login.setLoginWith(userRequest.getLoginWith());
		login.setSocialId(userRequest.getSocialId());
		login.setDeviceId(userRequest.getDeviceId());
		return login;
	}
	
	/**
	 * @param userRequest
	 * @return
	 */
	public default User converUserRequestToEntity(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setEmail(userRequest.getEmail());
		user.setMobileNo(userRequest.getMobileNo());
		user.setTzOffset(userRequest.getTzOffset());
		if (userRequest.getIsEmailVerified() ==null) {
			user.setIsEmailVerified(false);	
		}else {
			user.setIsEmailVerified(userRequest.getIsEmailVerified());	
		}
		if (userRequest.getIsEmailVerified() ==null) {
			user.setIsMobileVerified(false);	
		}else {
			user.setIsMobileVerified(userRequest.getIsMobileVerified());
		}
		
		return user;
	}
	
	/**
	 * @param user
	 * @return
	 */
	public default UserResponse converUserEntityToResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setMiddleName(user.getMiddleName());
		userResponse.setLastName(user.getLastName());
		userResponse.setGender(user.getGender());
		userResponse.setIsEmailVerified(user.getIsEmailVerified());
		userResponse.setIsMobileVerified(user.getIsMobileVerified());
		userResponse.setEmail(user.getEmail());
		userResponse.setMobileNo(user.getMobileNo());
		userResponse.setTzOffset(user.getTzOffset());
		return userResponse;
	}
}
