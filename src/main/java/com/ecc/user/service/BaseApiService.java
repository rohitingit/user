/**
 * 
 */
package com.ecc.user.service;

import java.text.ParseException;

import com.ecc.user.entity.Login;
import com.ecc.user.entity.User;
import com.shared.common.exception.BadRequestException;
import com.shared.common.request.UserRequest;
import com.shared.common.response.UserResponse;
import com.shared.common.util.Utils;

/**
 * @author Rohit
 *
 */
public interface BaseApiService {
	/**
	 * @param userRequest
	 * @return
	 */
	public default Login convertUserRequestToLoginEntity(UserRequest userRequest) {
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
	public default User convertUserRequestToEntity(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setEmail(userRequest.getEmail());
		user.setMobileNo(userRequest.getMobileNo());
		user.setTzOffset(userRequest.getTzOffset());
		if (userRequest.getIsEmailVerified() == null) {
			user.setIsEmailVerified(false);
		} else {
			user.setIsEmailVerified(userRequest.getIsEmailVerified());
		}
		if (userRequest.getIsEmailVerified() == null) {
			user.setIsMobileVerified(false);
		} else {
			user.setIsMobileVerified(userRequest.getIsMobileVerified());
		}

		return user;
	}

	/**
	 * @param user
	 * @return
	 */
	public default UserResponse convertUserEntityToResponse(User user) {
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

	/**
	 * @param user
	 * @param userRequest
	 * @return
	 */
	public default User updatedUserEntity(User user, UserRequest userRequest) throws BadRequestException {
		if (Utils.isPresent(userRequest.getFirstName()))
			user.setFirstName(userRequest.getFirstName());
		if (Utils.isPresent(userRequest.getMiddleName()))
			user.setMiddleName(userRequest.getMiddleName());
		if (Utils.isPresent(userRequest.getLastName()))
			user.setLastName(userRequest.getLastName());
		if (Utils.isPresent(userRequest.getEmail()))
			user.setEmail((userRequest.getEmail()));
		if (Utils.isPresent(userRequest.getDob()))
			try {
				user.setDob(Utils.parseDate(userRequest.getDob()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid dob");
			}
		if (Utils.isPresent(userRequest.getGender()))
			user.setGender(userRequest.getGender());
		if (Utils.isPresent(userRequest.getMobileNo()))
			user.setMobileNo(userRequest.getMobileNo());
		if (Utils.isPresent(userRequest.getIsEmailVerified()))
			user.setIsEmailVerified(userRequest.getIsEmailVerified());
		if (Utils.isPresent(userRequest.getIsEmailVerified()))
			user.setIsMobileVerified(userRequest.getIsEmailVerified());
		if (Utils.isPresent(userRequest.getProfileImageUrl()))
			user.setProfileImageUrl(userRequest.getProfileImageUrl());
		if (Utils.isPresent(userRequest.getStatus()))
			user.setStatus(userRequest.getStatus());
		return user;
	}
}
