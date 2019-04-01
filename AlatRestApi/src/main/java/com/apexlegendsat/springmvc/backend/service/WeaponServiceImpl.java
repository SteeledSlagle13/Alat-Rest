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
import com.apexlegendsat.springmvc.backend.view.WeaponView;

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
	public boolean doesWeaponExist(WeaponView weapon) {
		return findByName(weapon.getName()) != null;
	}

	@Override
	public List<WeaponView> findAllWeapons() {

		logger.info("Getting all weapons");
		List<WeaponView> weaponViews = new ArrayList<WeaponView>();
		weaponDao.findAllWeaponEntities().stream()
				.forEach(weaponEnt -> weaponViews.add(convertWeaponEntityToWeaponView(weaponEnt)));
		logger.info(weaponViews.size());
		return weaponViews;
	}

	@Override
	public WeaponView findById(int id) {
		return convertWeaponEntityToWeaponView(weaponDao.findWeaponEntityById(id));
	}

	@Override
	public WeaponView findByName(String name) {
		return convertWeaponEntityToWeaponView(weaponDao.findWeaponEntityByName(name));
	}

	@Override
	public void saveWeapon(WeaponView weapon) {
		weaponDao.saveWeaponEntity(convertWeaponViewToWeaponEntity(weapon));
	}

	@Override
	public void updateWeapon(WeaponView weapon) {
		weaponDao.updateWeaponEntity(convertWeaponViewToWeaponEntity(weapon));
	}

	private WeaponEntity convertWeaponViewToWeaponEntity(WeaponView weaponView) {
		if (weaponView == null) {
			return null;
		}
		WeaponEntity weaponEntity = new WeaponEntity(weaponView.getId(), weaponView.getName(), weaponView.getType(),
				weaponView.getImageSource(), weaponView.getLowDps(), weaponView.getHighDps(), weaponView.getClipSize(),
				weaponView.getReloadTime(), weaponView.getFireRate(), weaponView.getRaiseTime(),
				weaponView.getDamageRank(), weaponView.getSpeedRank(), weaponView.getRangeRank(),
				weaponView.getAccuracyRank(), weaponView.getHeadShot(), weaponView.getBodyShot());

		return weaponEntity;
	}

	private WeaponView convertWeaponEntityToWeaponView(WeaponEntity weaponEnt) {
		if (weaponEnt == null) {
			return null;
		}
		WeaponView weaponView = new WeaponView(weaponEnt.getId(), weaponEnt.getName(), weaponEnt.getType(),
				weaponEnt.getImageSource(), weaponEnt.getLowDps(), weaponEnt.getHighDps(), weaponEnt.getClipSize(),
				weaponEnt.getReloadTime(), weaponEnt.getFireRate(), weaponEnt.getRaiseTime(), weaponEnt.getDamageRank(),
				weaponEnt.getSpeedRank(), weaponEnt.getRangeRank(), weaponEnt.getAccuracyRank(),
				weaponEnt.getHeadShot(), weaponEnt.getBodyShot());

		return weaponView;
	}
}
