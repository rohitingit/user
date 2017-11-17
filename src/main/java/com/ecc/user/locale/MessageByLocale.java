package com.ecc.user.locale;


/**
 * @author ROHIT
 *
 */
public interface MessageByLocale {

	/**
	 * @param id
	 * @return
	 */
	public String getMessage(String id);

	/**
	 * @param id
	 * @param params
	 * @return
	 */
	public String getMessage(String id, String... params);
}
