package com.spring.baseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.baseproject.entity.Usuario;

@Service
public class SecutiryUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariosSistemaService userService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {		
		Usuario user = userService.findUserByEmail(userName);
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + userName + " not found");
		}
		return user;
	}

}
