package com.apexlegendsat.springmvc.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apexlegendsat.springmvc.backend.DAO.UserDAO;
import com.apexlegendsat.springmvc.backend.entity.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	static Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDAO userDao;

	@Override
	public void deleteUserById(int id) {
		userDao.deleteUserEntityById(id);
	}

	@Override
	public boolean doesUserExist(UserEntity user) {
		return findByName(user.getUsername()) != null;
	}

	@Override
	public List<UserEntity> findAllUsers() {

		List<UserEntity> userEntities = new ArrayList<UserEntity>();
		userEntities = userDao.findAllUserEntities();

		return userEntities;
	}

	@Override
	public UserEntity findById(int id) {
		return userDao.findUserEntityById(id);
	}

	@Override
	public UserEntity findByName(String name) {
		return userDao.findUserEntityByName(name);
	}

	@Override
	public void saveUser(UserEntity user) {
		userDao.saveUserEntity(user);
	}

	@Override
	public void updateUser(UserEntity user) {
		userDao.updateUserEntity(user);
	}

}
