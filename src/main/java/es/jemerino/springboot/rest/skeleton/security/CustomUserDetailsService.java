package es.jemerino.springboot.rest.skeleton.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import es.jemerino.springboot.rest.skeleton.domain.DomainUser;
import es.jemerino.springboot.rest.skeleton.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DomainUser user = userRepository.findOne(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Authorities.ROLE_USER.name());
		grantedAuthorities.add(authority);
		
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
