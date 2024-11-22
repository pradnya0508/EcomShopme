package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests 
{
	@Autowired
	private RoleRepository repo;
	
	
	@Test
	public void testCreateFirstRole() 
	{
		Role roleAdmin = new Role("Admin","Manage everything");
		Role savedRole = repo.save(roleAdmin);
		
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRole() 
	{
		Role roleSalesPerson = new Role("Salesperson","Manage product price, customers ,shipping order and sales Report");	
		Role roleEditor = new Role("Editor","Manage categories, brands, product, articles & menus");
		Role roleShipper = new Role("Shipper","Manage view products, view order & update order status");
		Role roleAssitant = new Role("Assitant","Manage Questions and review product");
		
		repo.saveAll(List.of(roleSalesPerson , roleEditor,roleShipper ,roleAssitant ));
	}
}
