/**
 * 
 */
package com.ecc.user.service;

import com.ecc.user.entity.User;
import com.shared.common.exception.BadRequestException;
import com.shared.common.exception.NotFoundException;

/**
 * @author ROHIT
 *
 */
public interface UserService {

	/**
	 * @param user
	 * @return
	 */
	public Long save(User user) throws BadRequestException;

	/**
	 * @param user
	 */
	public void update(User user);

	/**
	 * @param userId
	 * @return
	 * @throws NotFoundException
	 */
	public User findById(Long userId) throws NotFoundException;
	
	/**
	 * @param email
	 * @return
	 * @throws NotFoundException
	 */
	public User findByEmail(String email) throws NotFoundException;

}
