package com.instantChat.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Tquestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tquestion")
public class Tquestion implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Tuser tuser;
	private String title;
	private String content;
	private String createtime;
	private Integer iswhat;
	private Integer deleteable;
	private Integer	ifSolve;
	private Set<Ttag> ttags = new HashSet<Ttag>(0);
	private Set<TquestionPicture> tquestionPictures = new HashSet<TquestionPicture>(
			0);
	private Set<Trespond> tresponds = new HashSet<Trespond>(0);

	// Constructors

	/** default constructor */
	public Tquestion() {
	}

	/** minimal constructor */
	public Tquestion(Long id, Tuser tuser, String title,
			String createtime, Integer deleteable, Integer	ifSolve) {
		this.id = id;
		this.tuser = tuser;
		this.title = title;
		this.createtime = createtime;
		this.deleteable = deleteable;
		this.ifSolve = ifSolve;
	}

	/** full constructor */
	public Tquestion(Long id, Tuser tuser, String title, String content,
			String createtime, Integer iswhat, Integer deleteable, Integer ifSolve,
			Set<Ttag> ttags, Set<TquestionPicture> tquestionPictures,
			Set<Trespond> tresponds) {
		this.id = id;
		this.tuser = tuser;
		this.title = title;
		this.content = content;
		this.createtime = createtime;
		this.iswhat = iswhat;
		this.deleteable = deleteable;
		this.ifSolve = ifSolve;
		this.ttags = ttags;
		this.tquestionPictures = tquestionPictures;
		this.tresponds = tresponds;
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
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String dateTime) {
		this.createtime = dateTime;
	}
	@Column(name = "ifsolve")
	public Integer getIfSolve() {
		return ifSolve;
	}
	public void setIfSolve(Integer ifSolve) {
		this.ifSolve = ifSolve;
	}
	
	@Column(name = "iswhat")
	public Integer getIswhat() {
		return this.iswhat;
	}

	public void setIswhat(Integer iswhat) {
		this.iswhat = iswhat;
	}

	@Column(name = "deleteable", nullable = false)
	public Integer getDeleteable() {
		return this.deleteable;
	}

	public void setDeleteable(Integer deleteable) {
		this.deleteable = deleteable;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tquestion")
	public Set<Ttag> getTtags() {
		return this.ttags;
	}

	public void setTtags(Set<Ttag> ttags) {
		this.ttags = ttags;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tquestion")
	public Set<TquestionPicture> getTquestionPictures() {
		return this.tquestionPictures;
	}

	public void setTquestionPictures(Set<TquestionPicture> tquestionPictures) {
		this.tquestionPictures = tquestionPictures;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tquestion")
	public Set<Trespond> getTresponds() {
		return this.tresponds;
	}

	public void setTresponds(Set<Trespond> tresponds) {
		this.tresponds = tresponds;
	}

}