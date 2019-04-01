package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.view.WeaponView;

public interface WeaponService {
	
	void deleteWeaponById(int id);
	
	boolean doesWeaponExist(WeaponView weapon);

	List<WeaponView> findAllWeapons();
	
	WeaponView findById(int id);

	WeaponView findByName(String name);

	void saveWeapon(WeaponView weapon);

	void updateWeapon(WeaponView weapon);

}
