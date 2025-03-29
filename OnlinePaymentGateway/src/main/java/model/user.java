package model;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		//this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean verifyPassword(String rawPassword) {
		return BCrypt.checkpw(rawPassword, this.password);
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	@Column(nullable = false)
	private String role = "USER"; //keeping default role as user

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
