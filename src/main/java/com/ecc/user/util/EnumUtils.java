package com.ecc.user.util;

/**
 * @author Rohit
 *
 */
public class EnumUtils {

	public enum TemplateName {
		FORGOT_PASSWORD("/templates/forgot_password.vm");

		public final String name;

		TemplateName(String name) {
			this.name = name;
		}
	}

	public enum SignupStatus {
		EMAILVERIFICATIONPENDING("EMAIL_VERIFICATION_PENDING"), PROFILEPENDING("PROFILE_PENDING"), SUBSCRIPTIONPENDING(
				"SUBSCRIPTION_PENDING"), USERCREATIONPENDING("USER_CREATION_PENDING"), SIGNUPCOMPLETED("SIGNUP_COMPLETED");

		public final String status;

		SignupStatus(String status) {
			this.status = status;
		}
	}

	public enum AuthGrantType{
		implicit, refresh_token, password, authorization_code
	}
	
	public enum RequiredFields{
		email , userId , password,  roleId
	}
	
}
