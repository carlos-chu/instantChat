package com.instantChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TquestionPicture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tquestion_picture")
public class TquestionPicture implements java.io.Serializable {

	// Fields

	private String id;
	private Tuser tuser;
	private Tquestion tquestion;
	private String picUrl;

	// Constructors

	/** default constructor */
	public TquestionPicture() {
	}

	/** minimal constructor */
	public TquestionPicture(String id, Tuser tuser, String picUrl) {
		this.id = id;
		this.tuser = tuser;
		this.picUrl = picUrl;
	}

	/** full constructor */
	public TquestionPicture(String id, Tuser tuser, Tquestion tquestion,
			String picUrl) {
		this.id = id;
		this.tuser = tuser;
		this.tquestion = tquestion;
		this.picUrl = picUrl;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@Column(name = "pic_url", nullable = false)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}