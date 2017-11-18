/**
 * 
 */
package com.ecc.user.handler;

import com.ecc.user.entity.User;
import com.shared.common.request.UserRequest;
import com.shared.common.response.UserResponse;

/**
 * @author ROHIT
 *
 */
public class BaseRequestHandler {
	
	/**
	 * @param userRequest
	 * @return
	 */
	public User converUserRequestToEntity(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setEmail(userRequest.getEmail());
		user.setMobileNo(userRequest.getMobileNo());
		user.setRoleId(userRequest.getRoleId());
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
	
	public UserResponse converUserEntityToResponse(User user) {
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
		userResponse.setRoleId(user.getRoleId());
		userResponse.setTzOffset(user.getTzOffset());
		return userResponse;
	}

}
