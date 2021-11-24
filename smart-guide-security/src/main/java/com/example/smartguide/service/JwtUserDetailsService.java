package com.example.smartguide.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.smartguide.mapper.MemberMapper;
import com.example.smartguide.model.Member;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberMapper.selectUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(member.getRole().name()));
		return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
	}

	public Member authenticationByUsernameAndPassword(String username, String password) {
		Member member = memberMapper.selectUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
		
		if(!passwordEncoder.matches(password, member.getPassword())) {
			throw new BadCredentialsException("Password not matched");
		}
		
		return member;
	}
}
