/**
 * 
 */
package com.ecc.user.service;

import javax.ws.rs.NotFoundException;

import com.ecc.user.entity.User;
import com.shared.common.exception.BadRequestException;

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
