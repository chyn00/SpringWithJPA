package com.study.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = "password")
@Entity
@Table(name = "member")
public class UserEntity implements UserDetails {



	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no")
	int no;

	@Id
	@Column(name = "id", length = 50)
	private String id;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "point", nullable = false)
	private int point;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}