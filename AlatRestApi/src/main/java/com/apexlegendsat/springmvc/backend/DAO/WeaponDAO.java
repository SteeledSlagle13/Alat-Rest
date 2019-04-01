package com.apexlegendsat.springmvc.backend.DAO;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;

public interface WeaponDAO {

	void deleteWeaponEntityById(int weaponId);
	
	List<WeaponEntity> findAllWeaponEntities();
	
	WeaponEntity findWeaponEntityById(int weaponId);
	
	WeaponEntity findWeaponEntityByName(String weaponName);
	
	void saveWeaponEntity(WeaponEntity weaponEntity);
	
	void updateWeaponEntity(WeaponEntity weaponEntity);
}
