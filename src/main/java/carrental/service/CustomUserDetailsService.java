package carrental.service;

import static carrental.domain.UserStatus.ADMIN;
import static carrental.domain.UserStatus.USER;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import carrental.domain.User;
import carrental.repository.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = userDao.find(email);
		if (u == null)
			throw new UsernameNotFoundException("User with given email " + email + " isn't found. ");
		return new CustomUserDetails(u);
	}

	private static class CustomUserDetails extends User implements UserDetails {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static final Collection<GrantedAuthority> userAuthorities, adminAuthorities;

		static {
			Collection<GrantedAuthority> tempCollection;

			tempCollection = new LinkedList<GrantedAuthority>();
			tempCollection.add(new SimpleGrantedAuthority(USER.name()));
			userAuthorities = Collections.unmodifiableCollection(tempCollection);

			tempCollection = new LinkedList<GrantedAuthority>();
			tempCollection.add(new SimpleGrantedAuthority(ADMIN.name()));
			adminAuthorities = Collections.unmodifiableCollection(tempCollection);
		}
		

		CustomUserDetails(User user) {
			super(user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getPassword(), user.getUserStatus(), user.getUserId());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return getUserStatus() == USER ? userAuthorities : adminAuthorities; 
		}
		
		@Override public String getUsername() {return getEmail();}
		@Override public boolean isAccountNonExpired() {return true;}
		@Override public boolean isAccountNonLocked() {return true;}
		@Override public boolean isCredentialsNonExpired() {return true;}
		@Override public boolean isEnabled() {return true;}

	}

}
