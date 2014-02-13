package com.instantChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TuserQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser_question")
public class TuserQuestion implements java.io.Serializable {

	private static final long serialVersionUID = 8803906919880586274L;
	private Long id;
	private Tuser tuser;
	private Long questionId;
	private Integer isfellow;

	// Constructors

	/** default constructor */
	public TuserQuestion() {
	}

	/** minimal constructor */
	public TuserQuestion(Long id, Tuser tuser) {
		this.id = id;
		this.tuser = tuser;
	}

	/** full constructor */
	public TuserQuestion(Long id, Tuser tuser, Long questionId,
			Integer isfellow) {
		this.id = id;
		this.tuser = tuser;
		this.questionId = questionId;
		this.isfellow = isfellow;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false, length = 11)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	@Column(name = "question_id", length = 11)
	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Column(name = "isfellow")
	public Integer getIsfellow() {
		return this.isfellow;
	}

	public void setIsfellow(Integer isfellow) {
		this.isfellow = isfellow;
	}

}