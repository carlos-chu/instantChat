package com.instantChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ttag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ttag")
public class Ttag implements java.io.Serializable {

	// Fields

	private String id;
	private Tuser tuser;
	private Tquestion tquestion;
	private String title;
	private Integer userorquestionflag;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public Ttag() {
	}

	/** full constructor */
	public Ttag(String id, Tuser tuser, Tquestion tquestion, String title, Integer userorquestionflag, Integer isDelete) {
		this.id = id;
		this.tuser = tuser;
		this.tquestion = tquestion;
		this.title = title;
		this.userorquestionflag = userorquestionflag;
		this.isDelete = isDelete;
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
	@JoinColumn(name = "user_id", nullable = true)
	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = true)
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@Column(name = "title", nullable = false, length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "userorquestionflag", nullable = false)
	public Integer getUserorquestionflag() {
		return userorquestionflag;
	}

	public void setUserorquestionflag(Integer userorquestionflag) {
		this.userorquestionflag = userorquestionflag;
	}
	
	@Column(name = "isdelete")
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}