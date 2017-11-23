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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Rohit
 */
@Entity
@Table(name = "qualification")
@XmlRootElement
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;
	@GenericGenerator(name = "qualificationSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "qualifications_id_seq"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Id
	@GeneratedValue(generator = "qualificationSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "qualification")
    private String qualification;
    @Basic(optional = false)
    @Column(name = "specialization")
    private String specialization;
    @Basic(optional = false)
    @Column(name = "institute")
    private String institute;
    @Basic(optional = false)
    @Column(name = "institute_name")
    private String instituteName;
    @Basic(optional = false)
    @Column(name = "obtain_marks")
    private int obtainMarks;
    @Basic(optional = false)
    @Column(name = "total_marks")
    private int totalMarks;
    @Basic(optional = false)
    @Column(name = "percentage")
    private int percentage;
    @Basic(optional = false)
    @Column(name = "addmission_date")
    @Temporal(TemporalType.DATE)
    private Date addmissionDate;
    @Basic(optional = false)
    @Column(name = "complition_date")
    @Temporal(TemporalType.DATE)
    private Date complitionDate;
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;
    @Basic(optional = false)
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "attachment_url")
    private String attachmentUrl;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public Qualification() {
    }

    public Qualification(Long id) {
        this.id = id;
    }

    public Qualification(Long id, String qualification, String specialization, String institute, String instituteName, int obtainMarks, int totalMarks, int percentage, Date addmissionDate, Date complitionDate, boolean isActive, Date createdDate) {
        this.id = id;
        this.qualification = qualification;
        this.specialization = specialization;
        this.institute = institute;
        this.instituteName = instituteName;
        this.obtainMarks = obtainMarks;
        this.totalMarks = totalMarks;
        this.percentage = percentage;
        this.addmissionDate = addmissionDate;
        this.complitionDate = complitionDate;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public int getObtainMarks() {
        return obtainMarks;
    }

    public void setObtainMarks(int obtainMarks) {
        this.obtainMarks = obtainMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Date getAddmissionDate() {
        return addmissionDate;
    }

    public void setAddmissionDate(Date addmissionDate) {
        this.addmissionDate = addmissionDate;
    }

    public Date getComplitionDate() {
        return complitionDate;
    }

    public void setComplitionDate(Date complitionDate) {
        this.complitionDate = complitionDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
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

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualification)) {
            return false;
        }
        Qualification other = (Qualification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ecc.user.entity.Qualification[ id=" + id + " ]";
    }
    
}
