package com.instantChat.pageModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.instantChat.model.*;

/**
 * Tuser entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class UserDto implements java.io.Serializable {

	// Fields

	private String id;
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private Date createtime;
	private Integer ifaccept;
	private String intro;
	private String iconUrl;
	private String sex;
	private Set<Tquestion> tquestions = new HashSet<Tquestion>(0);
	private Set<Ttag> ttags = new HashSet<Ttag>(0);
	private Set<TquestionPicture> tquestionPictures = new HashSet<TquestionPicture>(
			0);
	private Set<TuserQuestion> tuserQuestions = new HashSet<TuserQuestion>(0);
	private Set<Trespond> tresponds = new HashSet<Trespond>(0);
	private Set<Tgrade> tgrades = new HashSet<Tgrade>(0);

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getIfaccept() {
		return this.ifaccept;
	}

	public void setIfaccept(Integer ifaccept) {
		this.ifaccept = ifaccept;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIconUrl() {
		return this.iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Set<Tquestion> getTquestions() {
		return this.tquestions;
	}

	public void setTquestions(Set<Tquestion> tquestions) {
		this.tquestions = tquestions;
	}

	public Set<Ttag> getTtags() {
		return this.ttags;
	}

	public void setTtags(Set<Ttag> ttags) {
		this.ttags = ttags;
	}

	public Set<TquestionPicture> getTquestionPictures() {
		return this.tquestionPictures;
	}

	public void setTquestionPictures(Set<TquestionPicture> tquestionPictures) {
		this.tquestionPictures = tquestionPictures;
	}

	public Set<TuserQuestion> getTuserQuestions() {
		return this.tuserQuestions;
	}

	public void setTuserQuestions(Set<TuserQuestion> tuserQuestions) {
		this.tuserQuestions = tuserQuestions;
	}

	public Set<Trespond> getTresponds() {
		return this.tresponds;
	}

	public void setTresponds(Set<Trespond> tresponds) {
		this.tresponds = tresponds;
	}

	public Set<Tgrade> getTgrades() {
		return this.tgrades;
	}

	public void setTgrades(Set<Tgrade> tgrades) {
		this.tgrades = tgrades;
	}

}