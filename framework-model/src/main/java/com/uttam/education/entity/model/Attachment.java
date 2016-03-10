package com.uttam.education.entity.model;
// Generated Jul 17, 2015 10:52:06 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Attachment generated by hbm2java
 */
public class Attachment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long attachmentId;
	private String storagePath;
	private String attachmentNm;
	private Date uploadTs;
	private String fileMimeTypeCd;
	private Date crtdDt;
	private String createUsrId;
	private Date updtDt;
	private String updtUserId;

	public Attachment() {
	}

	public Attachment(Long attachmentId, String storagePath, String attachmentNm, Date uploadTs, Date crtdDt,
			String createUsrId) {
		this.attachmentId = attachmentId;
		this.storagePath = storagePath;
		this.attachmentNm = attachmentNm;
		this.uploadTs = uploadTs;
		this.crtdDt = crtdDt;
		this.createUsrId = createUsrId;
	}

	public Attachment(Long attachmentId, String storagePath, String attachmentNm, Date uploadTs, String fileMimeTypeCd,
			Date crtdDt, String createUsrId, Date updtDt, String updtUserId) {
		this.attachmentId = attachmentId;
		this.storagePath = storagePath;
		this.attachmentNm = attachmentNm;
		this.uploadTs = uploadTs;
		this.fileMimeTypeCd = fileMimeTypeCd;
		this.crtdDt = crtdDt;
		this.createUsrId = createUsrId;
		this.updtDt = updtDt;
		this.updtUserId = updtUserId;
	}

	public Long getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getStoragePath() {
		return this.storagePath;
	}

	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	public String getAttachmentNm() {
		return this.attachmentNm;
	}

	public void setAttachmentNm(String attachmentNm) {
		this.attachmentNm = attachmentNm;
	}

	public Date getUploadTs() {
		return this.uploadTs;
	}

	public void setUploadTs(Date uploadTs) {
		this.uploadTs = uploadTs;
	}

	public String getFileMimeTypeCd() {
		return this.fileMimeTypeCd;
	}

	public void setFileMimeTypeCd(String fileMimeTypeCd) {
		this.fileMimeTypeCd = fileMimeTypeCd;
	}

	public Date getCrtdDt() {
		return this.crtdDt;
	}

	public void setCrtdDt(Date crtdDt) {
		this.crtdDt = crtdDt;
	}

	public String getCreateUsrId() {
		return this.createUsrId;
	}

	public void setCreateUsrId(String createUsrId) {
		this.createUsrId = createUsrId;
	}

	public Date getUpdtDt() {
		return this.updtDt;
	}

	public void setUpdtDt(Date updtDt) {
		this.updtDt = updtDt;
	}

	public String getUpdtUserId() {
		return this.updtUserId;
	}

	public void setUpdtUserId(String updtUserId) {
		this.updtUserId = updtUserId;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachmentId == null) ? 0 : attachmentId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attachment other = (Attachment) obj;
		if(this.attachmentId == null && other.attachmentId == null){
			return false;//can't compare
		}
		if (attachmentId == null) {
			if (other.attachmentId != null)
				return false;
		} else if (!attachmentId.equals(other.attachmentId))
			return false;
		return true;
	}
	
	
}
