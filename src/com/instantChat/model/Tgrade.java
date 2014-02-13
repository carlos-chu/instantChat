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
 * Tgrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tgrade")
public class Tgrade implements java.io.Serializable {

	// Fields

	private Long id;
	private Tuser tuser;
	private Long grade;

	// Constructors

	/** default constructor */
	public Tgrade() {
	}

	/** minimal constructor */
	public Tgrade(Long id, Tuser tuser) {
		this.id = id;
		this.tuser = tuser;
	}

	/** full constructor */
	public Tgrade(Long id, Tuser tuser, Long grade) {
		this.id = id;
		this.tuser = tuser;
		this.grade = grade;
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

	@Column(name = "grade")
	public Long getGrade() {
		return this.grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

}