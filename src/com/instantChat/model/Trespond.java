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
 * Trespond entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "trespond")
public class Trespond implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4382160324725580384L;
	private Long id;
	private Tuser tuser;
	private Tquestion tquestion;
	private String content;
	private String createtime;
	private String picUrl;

	// Constructors
	/** default constructor */
	public Trespond() {
	}

	/** minimal constructor */
	public Trespond(Long id, Tuser tuser, Tquestion tquestion,
			String createtime) {
		this.id = id;
		this.tuser = tuser;
		this.tquestion = tquestion;
		this.createtime = createtime;
	}

	/** full constructor */
	public Trespond(Long id, Tuser tuser, Tquestion tquestion,
			String content, String createtime, String picUrl) {
		this.id = id;
		this.tuser = tuser;
		this.tquestion = tquestion;
		this.content = content;
		this.createtime = createtime;
		this.picUrl = picUrl;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	public Tquestion getTquestion() {
		return this.tquestion;
	}
	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}
	
	@Column(name = "content", length = 400)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", nullable = false, length = 36)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "pic_url", length = 200)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}