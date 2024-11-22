package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService 
{
	@Autowired
	private UserRepository UserRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public List<User> listAll()
	{
		return (List<User>) UserRepo.findAll();
	}
	
	
	public List<Role> listRoles()
	{
		return(List<Role>)roleRepo.findAll();
	}


	public void save(User user) 
	{
		encodePassword(user);
		UserRepo.save(user);
		
	}
	
	private void encodePassword(User user) 
	{
		String encodedPassWord = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassWord);
	
	}
}
