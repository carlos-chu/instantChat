package com.instantChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TuserRespondWarn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser_respond_warn")
public class TuserRespondWarn implements java.io.Serializable {

	// Fields

	private String id;
	private Long questionId;
	private int ifScan;

	// Constructors
	/** default constructor */
	public TuserRespondWarn() {
	}

	/** full constructor */
	public TuserRespondWarn(String id, Long questionId, int ifScan) {
		this.id = id;
		this.questionId = questionId;
		this.ifScan = ifScan;
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

	@Column(name = "question_id", nullable = false, length = 11)
	public Long getQuestionId() {
		return this.questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	@Column(name = "ifscan", nullable = false)
	public int getIfScan() {
		return ifScan;
	}

	public void setIfScan(int ifScan) {
		this.ifScan = ifScan;
	}

}