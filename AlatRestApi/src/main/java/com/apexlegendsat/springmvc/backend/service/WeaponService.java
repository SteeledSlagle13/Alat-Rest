package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;

public interface WeaponService {
	
	void deleteWeaponById(int id);
	
	boolean doesWeaponExist(WeaponEntity weapon);

	List<WeaponEntity> findAllWeapons();
	
	WeaponEntity findById(int id);

	WeaponEntity findByName(String name);

	void saveWeapon(WeaponEntity weapon);

	void updateWeapon(WeaponEntity weapon);

}
