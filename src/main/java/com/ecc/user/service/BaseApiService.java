/**
 * 
 */
package com.ecc.user.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ecc.user.entity.Certification;
import com.ecc.user.entity.Document;
import com.ecc.user.entity.Login;
import com.ecc.user.entity.Qualification;
import com.ecc.user.entity.TypesData;
import com.ecc.user.entity.User;
import com.shared.common.exception.BadRequestException;
import com.shared.common.request.CertificationRequest;
import com.shared.common.request.DocumentRequest;
import com.shared.common.request.QualificationRequest;
import com.shared.common.request.UserRequest;
import com.shared.common.response.CertificationResponse;
import com.shared.common.response.DocumentResponse;
import com.shared.common.response.QualificationResponse;
import com.shared.common.response.UserResponse;
import com.shared.common.util.Utils;

/**
 * @author Rohit
 *
 */
public interface BaseApiService {
	/**
	 * @param userRequest
	 * @return
	 */
	public default Login convertUserRequestToLoginEntity(UserRequest userRequest) {
		Login login = new Login();
		login.setEmail(userRequest.getEmail());
		login.setUsername(userRequest.getUsername());
		login.setLoginWith(userRequest.getLoginWith());
		login.setSocialId(userRequest.getSocialId());
		login.setDeviceId(userRequest.getDeviceId());
		return login;
	}

	/**
	 * @param userRequest
	 * @return
	 */
	public default User convertUserRequestToEntity(UserRequest userRequest) {
		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setMiddleName(userRequest.getMiddleName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender());
		user.setEmail(userRequest.getEmail());
		user.setMobileNo(userRequest.getMobileNo());
		user.setTzOffset(userRequest.getTzOffset());
		if (userRequest.getIsEmailVerified() == null) {
			user.setIsEmailVerified(false);
		} else {
			user.setIsEmailVerified(userRequest.getIsEmailVerified());
		}
		if (userRequest.getIsEmailVerified() == null) {
			user.setIsMobileVerified(false);
		} else {
			user.setIsMobileVerified(userRequest.getIsMobileVerified());
		}

		return user;
	}

	/**
	 * @param user
	 * @return
	 */
	public default UserResponse convertUserEntityToResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getId());
		userResponse.setFirstName(user.getFirstName());
		userResponse.setMiddleName(user.getMiddleName());
		userResponse.setLastName(user.getLastName());
		userResponse.setGender(user.getGender());
		userResponse.setIsEmailVerified(user.getIsEmailVerified());
		userResponse.setIsMobileVerified(user.getIsMobileVerified());
		userResponse.setEmail(user.getEmail());
		userResponse.setMobileNo(user.getMobileNo());
		userResponse.setTzOffset(user.getTzOffset());
		userResponse.setCreatedDate(user.getCreatedDate());
		userResponse.setModifiedDate(user.getModifiedDate());
		userResponse.setProfileImageUrl(user.getProfileImageUrl());
		userResponse.setSignupStage(user.getSignupStage());
		userResponse.setProfileStatus(user.getStatus());
		if (Utils.isPresent(user.getCertificationList())) {
			userResponse.setCertifications(convertCertificationEntityToResponse(user.getCertificationList()));
		}
		if (Utils.isPresent(user.getDocumentList())) {
			userResponse.setDocuments(convertDocumentEntityResponse(user.getDocumentList()));
		}
		if (Utils.isPresent(user.getQualificationList())) {
			userResponse.setQualifications(convertQualificationEntityToResponse(user.getQualificationList()));
		}
		return userResponse;
	}

	/**
	 * @param certifications
	 * @return
	 */
	public default List<CertificationResponse> convertCertificationEntityToResponse(
			List<Certification> certifications) {
		List<CertificationResponse> certificationResponses = new ArrayList<>();
		for (Certification certification : certifications) {
			certificationResponses.add(convertCertificationEntityToResponse(certification));
		}
		return certificationResponses;
	}

	/**
	 * @param certification
	 * @return
	 */
	public default CertificationResponse convertCertificationEntityToResponse(Certification certification) {
		CertificationResponse certificationResponse = new CertificationResponse();
		certificationResponse.setId(certification.getId());
		certificationResponse.setUserId(certification.getUserId().getId());
		certificationResponse.setName(certification.getName());
		certificationResponse.setTitle(certification.getTitle());
		certificationResponse.setIsActive(certification.getIsActive());
		certificationResponse.setAttachmentUrl(certification.getAttachmentUrl());
		certificationResponse.setCreatedDate(certification.getCreatedDate());
		certificationResponse.setModifiedDate(certification.getModifiedDate());
		return certificationResponse;
	}

	/**
	 * @param documents
	 * @return
	 */
	public default List<DocumentResponse> convertDocumentEntityResponse(List<Document> documents) {
		List<DocumentResponse> documentResponses = new ArrayList<DocumentResponse>();
		for (Document document : documents) {
			documentResponses.add(convertDocumentEntityResponse(document));
		}
		return documentResponses;
	}

	/**
	 * @param documentRequest
	 * @return
	 */
	public default DocumentResponse convertDocumentEntityResponse(Document document) {
		DocumentResponse documentResponse = new DocumentResponse();
		documentResponse.setId(document.getId());
		documentResponse.setUserId(document.getUserId().getId());
		documentResponse.setTypeId(document.getTypeId().getId());
		documentResponse.setIsActive(document.getIsActive());
		documentResponse.setAttachmentUrl(document.getAttachmentUrl());
		documentResponse.setCreatedDate(document.getCreatedDate());
		documentResponse.setModifiedDate(document.getModifiedDate());
		return documentResponse;
	}

	/**
	 * @param qualifications
	 * @return
	 */
	public default List<QualificationResponse> convertQualificationEntityToResponse(
			List<Qualification> qualifications) {
		List<QualificationResponse> qualificationResponses = new ArrayList<>();
		for (Qualification qualification : qualifications) {
			qualificationResponses.add(convertQualificationEntityToResponse(qualification));
		}
		return qualificationResponses;
	}

	/**
	 * @param qualification
	 * @return
	 */
	public default QualificationResponse convertQualificationEntityToResponse(Qualification qualification) {
		QualificationResponse qualificationResponse = new QualificationResponse();
		qualificationResponse.setId(qualification.getId());
		qualificationResponse.setUserId(qualification.getUserId().getId());
		qualificationResponse.setInstitute(qualification.getInstitute());
		qualificationResponse.setInstituteName(qualification.getInstituteName());
		qualificationResponse.setAdmissionDate(qualification.getAdmissionDate());
		qualificationResponse.setComplitionDate(qualification.getComplitionDate());
		qualificationResponse.setObtainMarks(qualification.getObtainMarks());
		qualificationResponse.setTotalMarks(qualification.getTotalMarks());
		qualificationResponse.setPercentage(qualification.getPercentage());
		qualificationResponse.setSpecialization(qualification.getSpecialization());
		qualificationResponse.setIsActive(qualification.getIsActive());
		qualificationResponse.setAttachmentUrl(qualification.getAttachmentUrl());
		qualificationResponse.setCreatedDate(qualification.getCreatedDate());
		qualificationResponse.setModifiedDate(qualification.getModifiedDate());
		return qualificationResponse;
	}

	/**
	 * @param certificationRequest
	 * @return
	 */
	public default Certification convertCertificationRequestToEntity(CertificationRequest certificationRequest) {
		Certification certification = new Certification();
		certification.setUserId(new User(certificationRequest.getUserId()));
		certification.setName(certificationRequest.getName());
		certification.setTitle(certificationRequest.getTitle());
		certification.setIsActive(certificationRequest.getIsActive());
		certification.setAttachmentUrl(certificationRequest.getAttachmentUrl());
		certification.setCreatedDate(new Date());
		certification.setModifiedDate(new Date());
		return certification;
	}

	/**
	 * @param certification
	 * @param certificationRequest
	 * @return
	 */
	public default Certification updatedCertification(Certification certification,
			CertificationRequest certificationRequest) {
		certification.setId(certification.getId());
		if (Utils.isPresent(certificationRequest.getName())) {
			certification.setName(certificationRequest.getName());
		}
		if (Utils.isPresent(certificationRequest.getTitle())) {
			certification.setTitle(certificationRequest.getTitle());
		}
		if (Utils.isPresent(certificationRequest.getIsActive())) {
			certification.setIsActive(certificationRequest.getIsActive());
		}
		if (Utils.isPresent(certificationRequest.getAttachmentUrl())) {
			certification.setAttachmentUrl(certificationRequest.getAttachmentUrl());
		}
		certification.setModifiedDate(new Date());
		return certification;
	}

	/**
	 * @param documentRequest
	 * @return
	 */
	public default Document convertDocumentRequestToEntity(DocumentRequest documentRequest) {
		Document document = new Document();
		document.setUserId(new User(documentRequest.getUserId()));
		document.setTypeId(new TypesData(documentRequest.getTypeId()));
		document.setIsActive(documentRequest.getIsActive());
		document.setAttachmentUrl(documentRequest.getAttachmentUrl());
		document.setCreatedDate(new Date());
		document.setModifiedDate(new Date());
		return document;
	}

	/**
	 * @param document
	 * @param documentRequest
	 * @return
	 */
	public default Document updatedDocument(Document document, DocumentRequest documentRequest) {
		if (Utils.isPresent(documentRequest.getTypeId())) {
			document.setTypeId(new TypesData(documentRequest.getTypeId()));
		}
		if (Utils.isPresent(documentRequest.getIsActive())) {
			document.setIsActive(documentRequest.getIsActive());
		}
		if (Utils.isPresent(documentRequest.getAttachmentUrl())) {
			document.setAttachmentUrl(documentRequest.getAttachmentUrl());
		}
		document.setModifiedDate(new Date());
		return document;
	}

	/**
	 * @param documentRequest
	 * @return
	 */
	public default Qualification convertQualificationRequestToEntity(QualificationRequest qualificationRequest)
			throws BadRequestException {
		Qualification qualification = new Qualification();
		qualification.setUserId(new User(qualificationRequest.getUserId()));
		qualification.setInstitute(qualificationRequest.getInstitute());
		qualification.setInstituteName(qualificationRequest.getInstituteName());
		if (Utils.isPresent(qualificationRequest.getAdmissionDate())) {
			try {
				qualification.setAdmissionDate(Utils.parseDate(qualificationRequest.getAdmissionDate()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid admission date");
			}
		}
		if (Utils.isPresent(qualificationRequest.getComplitionDate())) {
			try {
				qualification.setComplitionDate(Utils.parseDate(qualificationRequest.getComplitionDate()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid complition date");
			}
		}
		qualification.setObtainMarks(qualificationRequest.getObtainMarks());
		qualification.setTotalMarks(qualificationRequest.getTotalMarks());
		qualification.setPercentage(qualificationRequest.getPercentage());
		qualification.setSpecialization(qualificationRequest.getSpecialization());
		qualification.setIsActive(qualificationRequest.getIsActive());
		qualification.setAttachmentUrl(qualificationRequest.getAttachmentUrl());
		qualification.setCreatedDate(new Date());
		qualification.setModifiedDate(new Date());
		return qualification;
	}

	/**
	 * @param document
	 * @param documentRequest
	 * @return
	 */
	public default Qualification updatedQualification(Qualification qualification,
			QualificationRequest qualificationRequest) throws BadRequestException {
		if (Utils.isPresent(qualificationRequest.getInstitute())) {
			qualification.setInstitute(qualificationRequest.getInstitute());
		}
		if (Utils.isPresent(qualificationRequest.getInstituteName())) {
			qualification.setInstituteName(qualificationRequest.getInstituteName());
		}
		if (Utils.isPresent(qualificationRequest.getAdmissionDate())) {
			try {
				qualification.setAdmissionDate(Utils.parseDate(qualificationRequest.getAdmissionDate()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid admission date");
			}
		}
		if (Utils.isPresent(qualificationRequest.getComplitionDate())) {
			try {
				qualification.setComplitionDate(Utils.parseDate(qualificationRequest.getComplitionDate()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid complition date");
			}
		}
		if (Utils.isPresent(qualificationRequest.getObtainMarks())) {
			qualification.setObtainMarks(qualificationRequest.getObtainMarks());
		}
		if (Utils.isPresent(qualificationRequest.getTotalMarks())) {
			qualification.setTotalMarks(qualificationRequest.getTotalMarks());
		}
		if (Utils.isPresent(qualificationRequest.getPercentage())) {
			qualification.setPercentage(qualificationRequest.getPercentage());
		}
		if (Utils.isPresent(qualificationRequest.getSpecialization())) {
			qualification.setSpecialization(qualificationRequest.getSpecialization());
		}
		if (Utils.isPresent(qualificationRequest.getIsActive())) {
			qualification.setIsActive(qualificationRequest.getIsActive());
		}
		if (Utils.isPresent(qualificationRequest.getAttachmentUrl())) {
			qualification.setAttachmentUrl(qualificationRequest.getAttachmentUrl());
		}
		qualification.setModifiedDate(new Date());
		return qualification;
	}

	/**
	 * @param user
	 * @param userRequest
	 * @return
	 */
	public default User updatedUser(User user, UserRequest userRequest) throws BadRequestException {
		if (Utils.isPresent(userRequest.getFirstName()))
			user.setFirstName(userRequest.getFirstName());
		if (Utils.isPresent(userRequest.getMiddleName()))
			user.setMiddleName(userRequest.getMiddleName());
		if (Utils.isPresent(userRequest.getLastName()))
			user.setLastName(userRequest.getLastName());
		if (Utils.isPresent(userRequest.getEmail()))
			user.setEmail((userRequest.getEmail()));
		if (Utils.isPresent(userRequest.getDob()))
			try {
				user.setDob(Utils.parseDate(userRequest.getDob()));
			} catch (ParseException e) {
				throw new BadRequestException("invalid dob");
			}
		if (Utils.isPresent(userRequest.getGender()))
			user.setGender(userRequest.getGender());
		if (Utils.isPresent(userRequest.getMobileNo()))
			user.setMobileNo(userRequest.getMobileNo());
		if (Utils.isPresent(userRequest.getIsEmailVerified()))
			user.setIsEmailVerified(userRequest.getIsEmailVerified());
		if (Utils.isPresent(userRequest.getIsEmailVerified()))
			user.setIsMobileVerified(userRequest.getIsEmailVerified());
		if (Utils.isPresent(userRequest.getProfileImageUrl()))
			user.setProfileImageUrl(userRequest.getProfileImageUrl());
		if (Utils.isPresent(userRequest.getStatus()))
			user.setStatus(userRequest.getStatus());
		return user;
	}
}
