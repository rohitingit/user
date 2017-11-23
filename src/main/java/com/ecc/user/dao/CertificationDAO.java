/**
 * 
 */
package com.ecc.user.dao;

import java.util.List;

/**
 * @author Rohit
 *
 */
public interface CertificationDAO {
	
	/**
	 * @param id
	 */
	public void deleteById(Long id);
	
	/**
	 * @param ids
	 */
	public void deleteByIds(List<Long> ids);

}
