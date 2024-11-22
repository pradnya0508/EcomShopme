package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserRepositoryTests 
{
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUserwithOneRole() 
	{
		Role roleAdmin= entityManager.find(Role.class, 2);
		User UserPradnya = new User("pradnya.0508@gmail.com","Pradnya06","Pradnya","Borde");
		UserPradnya.addRole(roleAdmin);
		
		User savedUser = repo.save(UserPradnya);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	
	@Test
	public void testCreateUserwithTwoRole() 
	{
		//User UserRaj = new User("Raj@Gmail.com","Raj01","Raj","Vengurlekar");	
		User UserMayuri = new User("kolte.Mayuri@gmail.com","Kolte01","Mayuri","Kolte");
		
		Role roleEditor = new Role(4);
		Role roleSalesPerson = new Role(3);
		
		UserMayuri.addRole(roleSalesPerson);
		UserMayuri.addRole(roleEditor);
		
		User savedUser= repo.save(UserMayuri);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() 
	{
		List <User> listUsers= (List<User>) repo.findAll();	
		
		listUsers.forEach(user -> System.out.println(user));
		
	}
	
	@Test
	public void testGetUserIdByid() 
	{
	 User userNam =repo.findById(1).get();
	 System.out.println(userNam);
	 userNam.setEnabled(true);
	 userNam.setEmail("Pradnya05.work@gmail.com");
	 assertThat(userNam).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() 
	{
	 User userNam =repo.findById(1).get();
	 userNam.setEnabled(true);
	 userNam.setEmail("Pradnya05.work@gmail.com");
	 
	 repo.save(userNam);
	}
	
	
	@Test
	public void testUserDetails() {
	User UserMayuri = repo.findById(2).get();
	Role roleEditor = new Role(4);
	Role roleShipper = new Role(5);
	
	UserMayuri.getRoles().remove(roleEditor);
	UserMayuri.addRole(roleShipper);
	
	repo.save(UserMayuri);
	}
	
	@Test
	public void deleteUser()
	{
		Integer UserId =2;
		repo.deleteById(UserId);
	}
}










