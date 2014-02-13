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
 * TfriendsUserWarn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tfriends_user_warn")
public class TfriendsUserWarn implements java.io.Serializable {

	// Fields

	private Long id;
	private Tuser friendtouserId;
	private Tuser tuser;
	private Integer ifScan;
	private Long grade;
	private String createtime;
	private Tquestion questionId;
	// Constructors

	public TfriendsUserWarn() {
	}

	/** minimal constructor */
	public TfriendsUserWarn(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TfriendsUserWarn(Long id, Tuser friendtouserId, Tuser tuser, Integer ifScan,
			String createtime, Tquestion questionId) {
		this.id = id;
		this.friendtouserId = friendtouserId;
		this.tuser = tuser;
		this.ifScan = ifScan;
		this.createtime = createtime;
		this.questionId =questionId;
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
	@JoinColumn(name = "friendtouser", nullable = true)
	public Tuser getFriendtouserId() {
		return this.friendtouserId;
	}
	public void setFriendtouserId(Tuser friendtouserId) {
		this.friendtouserId = friendtouserId;
	}
	
	@Column(name = "grade")
	public Long getGrade() {
		return grade;
	}
	public void setGrade(Long grade) {
		this.grade = grade;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionid")
	public Tquestion getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Tquestion questionId) {
		this.questionId = questionId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = true)
	public Tuser getTuser() {
		return tuser;
	}
	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}
	
	@Column(name = "ifscan")
	public Integer getIfScan() {
		return ifScan;
	}
	public void setIfScan(Integer ifScan) {
		this.ifScan = ifScan;
	}
	
	@Column(name = "createtime", nullable = false, length = 19)
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}