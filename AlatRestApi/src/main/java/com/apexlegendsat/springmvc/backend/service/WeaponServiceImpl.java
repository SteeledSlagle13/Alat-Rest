package com.apexlegendsat.springmvc.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apexlegendsat.springmvc.backend.DAO.WeaponDAO;
import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;

@Service
@Transactional
public class WeaponServiceImpl implements WeaponService {

	static Logger logger = LogManager.getLogger(WeaponServiceImpl.class.getName());

	@Autowired
	private WeaponDAO weaponDao;

	@Override
	public void deleteWeaponById(int id) {
		weaponDao.deleteWeaponEntityById(id);
	}

	@Override
	public boolean doesWeaponExist(WeaponEntity weapon) {
		return findByName(weapon.getName()) != null;
	}

	@Override
	public List<WeaponEntity> findAllWeapons() {

		logger.info("Getting all weapons");

		List<WeaponEntity> weaponEntities = new ArrayList<WeaponEntity>();

		logger.info(weaponEntities.size());

		weaponEntities = weaponDao.findAllWeaponEntities();

		logger.debug(weaponEntities.size());

		return weaponEntities;
	}

	@Override
	public WeaponEntity findById(int id) {
		return weaponDao.findWeaponEntityById(id);
	}

	@Override
	public WeaponEntity findByName(String name) {
		return weaponDao.findWeaponEntityByName(name);
	}

	@Override
	public void saveWeapon(WeaponEntity weapon) {
		weaponDao.saveWeaponEntity(weapon);
	}

	@Override
	public void updateWeapon(WeaponEntity weapon) {
		weaponDao.updateWeaponEntity(weapon);
	}
}
