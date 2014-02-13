package com.instantChat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser")
public class Tuser implements java.io.Serializable {

	// Fields

	private String id;
	private String email;
	private String password;
	private String name;
	private Date createtime;
	private Integer ifaccept;
	private String intro;
	private String iconUrl;
	private Integer grade;
	private Integer ifOnline;
	private String sex;
	private Set<Tquestion> tquestions = new HashSet<Tquestion>(0);
	private Set<Ttag> ttags = new HashSet<Ttag>(0);
	private Set<TquestionPicture> tquestionPictures = new HashSet<TquestionPicture>(
			0);
	private Set<TuserQuestion> tuserQuestions = new HashSet<TuserQuestion>(0);
	private Set<Trespond> tresponds = new HashSet<Trespond>(0);
	private Set<Tgrade> tgrades = new HashSet<Tgrade>(0);

	// Constructors

	/** default constructor */
	public Tuser() {
	}

	/** minimal constructor */
	public Tuser(String id, String email, String password, String name,
			Date createtime, Integer ifaccept, String sex) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.createtime = createtime;
		this.ifaccept = ifaccept;
		this.sex = sex;
	}

	/** full constructor */
	public Tuser(String id, String email, String password, String name,
			Date createtime, Integer ifaccept, String intro, String iconUrl, Integer grade, Integer ifOnline,
			Set<Tquestion> tquestions, Set<Ttag> ttags,
			Set<TquestionPicture> tquestionPictures,
			Set<TuserQuestion> tuserQuestions, Set<Trespond> tresponds,
			Set<Tgrade> tgrades) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.createtime = createtime;
		this.ifaccept = ifaccept;
		this.intro = intro;
		this.iconUrl = iconUrl;
		this.grade  =grade;
		this.ifOnline = ifOnline;
		this.tquestions = tquestions;
		this.ttags = ttags;
		this.tquestionPictures = tquestionPictures;
		this.tuserQuestions = tuserQuestions;
		this.tresponds = tresponds;
		this.tgrades = tgrades;
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

	@Column(name = "email", nullable = false, length = 36)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 36)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 36)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createtime", nullable = false, length = 10)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "ifaccept", nullable = false)
	public Integer getIfaccept() {
		return this.ifaccept;
	}

	public void setIfaccept(Integer ifaccept) {
		this.ifaccept = ifaccept;
	}

	@Column(name = "intro")
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "iconurl")
	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	@Column(name = "grade", length = 10)
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	@Column(name = "ifonline", length = 1)
	public Integer getIfOnline() {
		return ifOnline;
	}
	public void setIfOnline(Integer ifOnline) {
		this.ifOnline = ifOnline;
	}
	
	@Column(name = "sex", length = 36)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Tquestion> getTquestions() {
		return this.tquestions;
	}

	public void setTquestions(Set<Tquestion> tquestions) {
		this.tquestions = tquestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Ttag> getTtags() {
		return this.ttags;
	}

	public void setTtags(Set<Ttag> ttags) {
		this.ttags = ttags;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<TquestionPicture> getTquestionPictures() {
		return this.tquestionPictures;
	}

	public void setTquestionPictures(Set<TquestionPicture> tquestionPictures) {
		this.tquestionPictures = tquestionPictures;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<TuserQuestion> getTuserQuestions() {
		return this.tuserQuestions;
	}

	public void setTuserQuestions(Set<TuserQuestion> tuserQuestions) {
		this.tuserQuestions = tuserQuestions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Trespond> getTresponds() {
		return this.tresponds;
	}

	public void setTresponds(Set<Trespond> tresponds) {
		this.tresponds = tresponds;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tuser")
	public Set<Tgrade> getTgrades() {
		return this.tgrades;
	}

	public void setTgrades(Set<Tgrade> tgrades) {
		this.tgrades = tgrades;
	}


}