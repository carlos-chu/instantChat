package com.instantChat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TuserFriends entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tuser_friends")
public class TuserFriends implements java.io.Serializable {

	private static final long serialVersionUID = 4128610044865727927L;
	private Long id;
	private String userId;
	private String friendId;

	// Constructors

	/** default constructor */
	public TuserFriends() {
	}

	/** minimal constructor */
	public TuserFriends(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TuserFriends(Long id, String userId, String friendId) {
		this.id = id;
		this.userId = userId;
		this.friendId = friendId;
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

	@Column(name = "user_id", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "friend_id", length = 36)
	public String getFriendId() {
		return this.friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

}