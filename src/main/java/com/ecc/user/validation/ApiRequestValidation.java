/**
 * 
 */
package com.ecc.user.validation;

import java.util.regex.Pattern;
import com.ecc.user.locale.MessageByLocale;

/**
 * @author ROHIT
 *
 */
public class ApiRequestValidation extends Validator {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
			Pattern.CASE_INSENSITIVE);
	
	public ApiRequestValidation(MessageByLocale messageByLocale) {
		super(messageByLocale);
	}

}
