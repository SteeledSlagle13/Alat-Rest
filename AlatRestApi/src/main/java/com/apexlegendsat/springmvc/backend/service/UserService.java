package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.UserEntity;
import com.apexlegendsat.springmvc.backend.view.UserView;

public interface UserService {
	
	UserEntity convertUserViewToUserEntity(UserView user);
	
	UserView convertUserEntityToUserView(UserEntity userEnt);
	
	void deleteUserById(int id);
	
	public boolean doesUserExist(UserView user);

	List<UserView> findAllUsers();
	
	UserView findById(int id);

	UserView findByName(String name);
	
	void saveUser(UserView user);

	void updateUser(UserView user);

}
