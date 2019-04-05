package com.apexlegendsat.springmvc.backend.test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.apexlegendsat.springmvc.backend.DAO.UserDaoImpl;
import com.apexlegendsat.springmvc.backend.DAO.WeaponDaoImpl;
import com.apexlegendsat.springmvc.backend.entity.UserEntity;
import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;
import com.apexlegendsat.springmvc.backend.service.UserServiceImpl;
import com.apexlegendsat.springmvc.backend.service.WeaponServiceImpl;

public class UserServiceTest {

	@Mock
	private UserDaoImpl userDAO;

	@InjectMocks
	private UserServiceImpl userService;

	@Spy
	private List<UserEntity> users = new ArrayList<UserEntity>();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		users = getUsers();
	}

	@After
	public void tearDown() throws Exception {
		users = null;
	}

	@Test
	public void findAllWeapons_Positive() {
		when(userDAO.findAllUserEntities()).thenReturn(users);
		Assert.assertEquals(userService.findAllUsers(), users);
	}

	@Test
	public void findById_Positive() {
		UserEntity user = users.get(0);
		when(userDAO.findUserEntityById((anyInt()))).thenReturn(user);
		Assert.assertEquals(userService.findById(user.getId()), user);
	}
	
	@Test
	public void findByName_Positive() {
		UserEntity user = users.get(0);
		when(userDAO.findUserEntityByName(anyString())).thenReturn(user);
		Assert.assertEquals(userService.findByName(user.getUsername()), user);
	}

	@Test
	public void saveWeapon_Positive() {
		doNothing().when(userDAO).saveUserEntity(any(UserEntity.class));
		userService.saveUser(new UserEntity(20, "bill", "HERE", "bill@here.com", "abcEZ45123"));
		verify(userDAO, atLeastOnce()).saveUserEntity(any(UserEntity.class));
	}
	
	@Test
    public void updateEmployee(){
        UserEntity user = users.get(0);
        doNothing().when(userDAO).updateUserEntity(any(UserEntity.class));
        userService.updateUser(user);
        verify(userDAO, atLeastOnce()).updateUserEntity(any(UserEntity.class));
    }
 
	@Test
    public void deleteWeaponById_Positive(){
        doNothing().when(userDAO).deleteUserEntityById((anyInt()));
        userService.deleteUserById(anyInt());
        verify(userDAO, atLeastOnce()).deleteUserEntityById(anyInt());
    }

	public List<UserEntity> getUsers() {

		users.add(new UserEntity(1, "scorch", "asdf", "asdf", "lkasndfl"));
		users.add(new UserEntity(2, "cyclonous", "asdf", "asdf", "lkasndfl"));
		users.add(new UserEntity(3, "galvatron", "asdf", "asdf", "lkasndfl"));
		users.add(new UserEntity(4, "unicron", "asdf", "asdf", "lkasndfl"));

		return users;
	}

}
