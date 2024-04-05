package com.mk.bibliotheque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.bibliotheque.repositories.RoleRepository;
import com.mk.bibliotheque.repositories.UserRepository;

@Service
public class AuthService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
}
