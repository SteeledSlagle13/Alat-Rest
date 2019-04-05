package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.UserEntity;

public interface UserService {
	
void deleteUserById(int id);
	
	public boolean doesUserExist(UserEntity user);

	List<UserEntity> findAllUsers();
	
	UserEntity findById(int id);

	UserEntity findByName(String name);
	
	void saveUser(UserEntity user);

	void updateUser(UserEntity user);

}
