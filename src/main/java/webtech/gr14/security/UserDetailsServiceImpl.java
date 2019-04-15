package webtech.gr14.security;

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

import webtech.gr14.model.Account;
import webtech.gr14.repository.AccountJpa;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountJpa accJpa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = accJpa.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		if (user.getPrivilege() == 0) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}

		if (user.getPrivilege() == 1) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_HOST"));
		}

		if (user.getPrivilege() == 2) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

}
