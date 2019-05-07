package webtech.gr14.config.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccR aR;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Acc user = aR.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if (user.getRole() == 0) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}

		if (user.getRole() == 1) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_HOST"));
		}

		if (user.getRole() == 2) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

}
