/**
 * 
 */
package com.ecc.user.dao;

import com.ecc.user.entity.Login;

/**
 * @author Rohit
 *
 */
public interface LoginDAO {

	/**
	 * @param login
	 * @return
	 */
	public Long save(Login login);

	/**
	 * @param email
	 * @return
	 */
	public Login findByEmail(String email);

}
