package carrental.domain;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	/**
	 * 
	 */

	// Constants
	// ----------------------------------------------------------------------------------------------------
	private static final long serialVersionUID = -2834778306232001744L;

	// Fields
	// ----------------------------------------------------------------------------------------------------
	private String email, password, firstname, lastname, phone;
	private UserStatus userStatus;
	private Integer id;

	// Constructors
	// ----------------------------------------------------------------------------------------------------

	public User() {
	}

	public User(String email, String password, String firstname, String lastname, String phone, UserStatus userStatus, Integer userId) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.userStatus = userStatus;
		this.id = userId;
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
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "userId")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
