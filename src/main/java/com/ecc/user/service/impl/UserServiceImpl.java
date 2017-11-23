/**
 * 
 */
package com.ecc.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecc.user.dao.CertificationDAO;
import com.ecc.user.dao.DocumentDAO;
import com.ecc.user.dao.LoginDAO;
import com.ecc.user.dao.QualificationDAO;
import com.ecc.user.dao.UserDAO;
import com.ecc.user.entity.Certification;
import com.ecc.user.entity.Document;
import com.ecc.user.entity.Login;
import com.ecc.user.entity.Qualification;
import com.ecc.user.entity.User;
import com.ecc.user.locale.MessageByLocale;
import com.ecc.user.service.UserService;
import com.shared.common.exception.NotFoundException;
import com.shared.common.exception.RequestException;
import com.shared.common.request.CertificationRequest;
import com.shared.common.request.DocumentRequest;
import com.shared.common.request.QualificationRequest;
import com.shared.common.request.UserRequest;
import com.shared.common.response.BaseResponse;
import com.shared.common.response.UserResponse;
import com.shared.common.util.Utils;

/**
 * @author ROHIT
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageByLocale messageByLocale;

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CertificationDAO certificationDAO;

	@Autowired
	private DocumentDAO documentDAO;

	@Autowired
	private QualificationDAO qualificationDAO;

	@Override
	@Transactional
	public BaseResponse save(UserRequest userRequest) throws RequestException {
		logger.info(" save ");
		UserResponse userResponse = new UserResponse();
		Login login = null;
		User user = null;
		login = loginDAO.findByEmail(userRequest.getEmail());
		if (login == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		user = userDAO.findByEmail(userRequest.getEmail());
		if (user == null) {/** create user **/
			user = convertUserRequestToEntity(userRequest);
			user.setCreatedDate(new Date());
		} else {/** update user **/
			user = updatedUser(user, userRequest);
		}
		user.setModifiedDate(new Date());
		user.setLogin(login);

		if (Utils.isPresent(userRequest.getCertifications())) {
			List<Certification> certifications = new ArrayList<Certification>();
			if (Utils.isPresent(user.getCertificationList())) {
				for (CertificationRequest certificationRequest : userRequest.getCertifications()) {
					if (!Utils.isPresent(certificationRequest
							.getId())) {/** add new certificate **/
						certifications.add(convertCertificationRequestToEntity(certificationRequest));
					} else {
						Certification certification = user.getCertificationList().stream()
								.filter(c -> c.getId().equals(certificationRequest.getId())).findFirst().orElse(null);
						if (certification != null) { /** update certificate **/
							certifications.add(updatedCertification(certification, certificationRequest));
						} 
					}
				}
				user.getCertificationList().removeAll(certifications);
				certificationDAO.deleteByIds(user.getCertificationList().stream().map(c -> c.getId()).collect(Collectors.toList()));
			} else {
				for (CertificationRequest certificationRequest : userRequest.getCertifications()) {
					certifications.add(convertCertificationRequestToEntity(certificationRequest));
				}
			}
			user.setCertificationList(certifications);
		}

		if (Utils.isPresent(userRequest.getDocuments())) {
			List<Document> documents = new ArrayList<Document>();
			if (Utils.isPresent(user.getDocumentList())) {
				for (DocumentRequest documentRequest : userRequest.getDocuments()) {
					if (!Utils.isPresent(
							documentRequest.getId())) {/** add new document **/
						documents.add(convertDocumentRequestToEntity(documentRequest));
					} else {
						Document document = user.getDocumentList().stream()
								.filter(d -> d.getId().equals(documentRequest.getId())).findFirst().orElse(null);
						if (document != null) { /** update document **/
							documents.add(updatedDocument(document, documentRequest));
						}
					}
				}
				user.getDocumentList().removeAll(documents);
				documentDAO.deleteByIds(user.getDocumentList().stream().map(d -> d.getId()).collect(Collectors.toList()));
			} else {
				for (DocumentRequest documentRequest : userRequest.getDocuments()) {
					documents.add(convertDocumentRequestToEntity(documentRequest));
				}
			}
			user.setDocumentList(documents);
		}

		if (Utils.isPresent(userRequest.getQualifications())) {
			List<Qualification> qualifications = new ArrayList<Qualification>();
			if (Utils.isPresent(user.getQualificationList())) {
				for (QualificationRequest qualificationRequest : userRequest.getQualifications()) {
					if (!Utils.isPresent(qualificationRequest
							.getId())) {/** add new qualification **/
						qualifications.add(convertQualificationRequestToEntity(qualificationRequest));
					} else {
						Qualification qualification = user.getQualificationList().stream()
								.filter(q -> q.getId().equals(qualificationRequest.getId())).findFirst().orElse(null);
						if (qualification != null) { /** * update qualification **/
							qualifications.add(updatedQualification(qualification, qualificationRequest));
						}
					}
				}
				user.getQualificationList().removeAll(qualifications);
				qualificationDAO.deleteByIds(user.getQualificationList().stream().map(q -> q.getId()).collect(Collectors.toList()));
			} else {
				for (QualificationRequest qualificationRequest : userRequest.getQualifications()) {
					qualifications.add(convertQualificationRequestToEntity(qualificationRequest));
				}
			}
			user.setQualificationList(qualifications);
		}

		user = userDAO.save(user);
		userResponse = convertUserEntityToResponse(user);
		return userResponse;
	}

	@Override
	@Transactional
	public BaseResponse findById(Long userId) throws NotFoundException {
		logger.info(" findById ");
		UserResponse userResponse = new UserResponse();
		User user = userDAO.findById(userId);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		userResponse = convertUserEntityToResponse(user);
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}

	@Override
	@Transactional
	public BaseResponse findByEmail(String email) throws NotFoundException {
		logger.info(" findByEmail ");
		UserResponse userResponse = new UserResponse();
		User user = userDAO.findByEmail(email);
		if (user == null) {
			throw new NotFoundException(messageByLocale.getMessage("user.not.found"));
		}
		userResponse = convertUserEntityToResponse(user);
		userResponse.setStatus(HttpStatus.OK.value());
		return userResponse;
	}
}
