package com.apexlegendsat.springmvc.backend.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;
import com.apexlegendsat.springmvc.backend.service.WeaponService;

@RestController
@RequestMapping(value = "weapon")
public class ApiWeaponRestController {

	static Logger logger = LogManager.getLogger(ApiWeaponRestController.class.getName());

	@Autowired
	private WeaponService weaponService;

	@RequestMapping(value = "", method = RequestMethod.GET, headers = {
			"Accept=application/json,application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<List<WeaponEntity>> listAllWeapons() {
		logger.error("Getting weapon list");
		List<WeaponEntity> weapons = weaponService.findAllWeapons();

		if (weapons.isEmpty()) {
			logger.error("weapons list is empty");
			return new ResponseEntity<List<WeaponEntity>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.error("weapons list is not empty");
		return new ResponseEntity<List<WeaponEntity>>(weapons, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<WeaponEntity> getWeapon(@PathVariable("id") int id) {
		logger.debug("Fetching Weapon with id " + id);
		WeaponEntity weapon = weaponService.findById(id);
		if (weapon == null) {
			logger.error("Weapon with id " + id + " not found");
			return new ResponseEntity<WeaponEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<WeaponEntity>(weapon, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> createWeapon(@RequestBody WeaponEntity weapon) {
		logger.debug("Creating Weapon " + weapon.getName());

		if (weaponService.doesWeaponExist(weapon)) {
			logger.error("A Weapon with name " + weapon.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		weaponService.saveWeapon(weapon);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<WeaponEntity> updateWeapon(@RequestBody WeaponEntity weapon) {
		logger.debug("Updating Weapon " + weapon.getId());
		logger.debug(weapon);

		WeaponEntity currentWeapon = weaponService.findById(weapon.getId());

		if (currentWeapon == null) {
			logger.error("Weapon with id " + weapon.getId() + " not found");
			return new ResponseEntity<WeaponEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		weaponService.updateWeapon(weapon);
		return new ResponseEntity<WeaponEntity>(weapon, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteWeapon(@PathVariable("id") int id) {
		logger.debug("Fetching & Deleting Weapon with id " + id);

		WeaponEntity weapon = weaponService.findById(id);
		if (weapon == null) {
			logger.error("Unable to delete. Weapon with id " + id + " not found");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		weaponService.deleteWeaponById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}