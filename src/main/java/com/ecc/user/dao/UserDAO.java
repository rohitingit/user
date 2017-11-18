/**
 * 
 */
package com.ecc.user.dao;

import com.ecc.user.entity.User;

/**
 * @author Rohit
 *
 */
public interface UserDAO {

	/**
	 * @param user
	 * @return
	 */
	public Long save(User user);

	/**
	 * @param userId
	 * @return
	 */
	public User findById(Long userId);
	
	/**
	 * @param userId
	 * @return
	 */
	public User findByEmail(String email);

	/**
	 * @param user
	 * @return
	 */
	public User update(User user);
}
