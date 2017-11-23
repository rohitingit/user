/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecc.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Rohit
 */
@Entity
@Table(name = "login")
@XmlRootElement
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "loginSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "login_id_seq"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Id
	@GeneratedValue(generator = "loginSeq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password")
	private String password;
	@Basic(optional = false)
	@Column(name = "role_id")
	private long roleId;
	@Column(name = "login_with")
	private String loginWith;
	@Column(name = "social_id")
	private String socialId;
	@Column(name = "device_id")
	private String deviceId;
	@Column(name = "refresh_token")
	private String refreshToken;
	@Column(name = "access_token")
	private String accessToken;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIME)
	private Date createdDate;
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIME)
	private Date modifiedDate;

	public Login() {
	}

	public Login(Long id) {
		this.id = id;
	}

	public Login(Long id, long roleId) {
		this.id = id;
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getLoginWith() {
		return loginWith;
	}

	public void setLoginWith(String loginWith) {
		this.loginWith = loginWith;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@XmlTransient
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Login)) {
			return false;
		}
		Login other = (Login) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ecc.user.entity.Login[ id=" + id + " ]";
	}

}
