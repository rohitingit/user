/**
 * 
 */
package com.ecc.user.validation;

import java.util.regex.Pattern;

import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.util.EnumUtils.RequiredFields;
import com.shared.common.request.UserRequest;
import com.shared.common.util.EnumUtils.MessageType;

/**
 * @author ROHIT
 *
 */
public class APIRequestValidator extends Validator {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			Pattern.CASE_INSENSITIVE);

	public APIRequestValidator(MessageByLocale messageByLocale) {
		super(messageByLocale);
	}

	public void validateSignUp(UserRequest userRequest) {
		validateString(RequiredFields.email.name(), userRequest.getEmail());
		validateString(RequiredFields.password.name(), userRequest.getEmail());
		validateLong(RequiredFields.roleId.name(), userRequest.getRoleId());
	}

	public void validateSave(UserRequest userRequest) {
		validateString(RequiredFields.email.name(), userRequest.getEmail());
	}

	public void validateLogin(UserRequest userRequest) {
		validateString(RequiredFields.email.name(), userRequest.getEmail());
		validateString(RequiredFields.password.name(), userRequest.getEmail());
	}

	public void validateString(String field, String value) {
		if (value == null || value.trim().isEmpty()) {
			addMessage(MessageType.ERROR, "exception.field.required", field);
		}
	}

	public void validateLong(String field, Long value) {
		if (value == null || value == 0) {
			addMessage(MessageType.ERROR, "exception.field.required", field);
		}
	}

}
