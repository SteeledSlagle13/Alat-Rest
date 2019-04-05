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
import com.apexlegendsat.springmvc.backend.DAO.WeaponDaoImpl;
import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;
import com.apexlegendsat.springmvc.backend.service.WeaponServiceImpl;

public class WeaponServiceTest {

	@Mock
	private WeaponDaoImpl weaponDAO;

	@InjectMocks
	private WeaponServiceImpl weaponService;

	@Spy
	private List<WeaponEntity> weapons = new ArrayList<WeaponEntity>();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		weapons = getWeapons();
	}

	@After
	public void tearDown() throws Exception {
		weapons = null;
	}

	@Test
	public void findAllWeapons_Positive() {
		when(weaponDAO.findAllWeaponEntities()).thenReturn(weapons);
		Assert.assertEquals(weaponService.findAllWeapons(), weapons);
	}

	@Test
	public void findById_Positive() {
		WeaponEntity weapon = weapons.get(0);
		when(weaponDAO.findWeaponEntityById((anyInt()))).thenReturn(weapon);
		Assert.assertEquals(weaponService.findById(weapon.getId()), weapon);
	}
	
	@Test
	public void findByName_Positive() {
		WeaponEntity weapon = weapons.get(0);
		when(weaponDAO.findWeaponEntityByName(anyString())).thenReturn(weapon);
		Assert.assertEquals(weaponService.findByName(weapon.getName()), weapon);
	}

	@Test
	public void saveWeapon_Positive() {
		doNothing().when(weaponDAO).saveWeaponEntity(any(WeaponEntity.class));
		weaponService.saveWeapon(new WeaponEntity(30, "SaveWeaponTestGun", "Test", "/path/2/image.png", 34, 45, 6, 0.5f, 11.3f, 0.3f, 30, 10,
				10, 50, 78, 49));
		verify(weaponDAO, atLeastOnce()).saveWeaponEntity(any(WeaponEntity.class));
	}
	
	@Test
    public void updateEmployee(){
        WeaponEntity emp = weapons.get(0);
        doNothing().when(weaponDAO).updateWeaponEntity(any(WeaponEntity.class));
        weaponService.updateWeapon(emp);
        verify(weaponDAO, atLeastOnce()).updateWeaponEntity(any(WeaponEntity.class));
    }
 
	@Test
    public void deleteWeaponById_Positive(){
        doNothing().when(weaponDAO).deleteWeaponEntityById((anyInt()));
        weaponService.deleteWeaponById(anyInt());
        verify(weaponDAO, atLeastOnce()).deleteWeaponEntityById(anyInt());
    }

	public List<WeaponEntity> getWeapons() {

		weapons.add(new WeaponEntity(1, "some gun", "pistol", "/path/2/image.png", 20, 30, 5, 0.5f, 11.3f, 0.3f, 30, 10,
				10, 50, 78, 49));
		weapons.add(new WeaponEntity(2, "the gun", "shotgun", "/path/2/image.png", 12, 30, 5, 0.5f, 11.3f, 0.3f, 30, 10,
				10, 50, 78, 49));
		weapons.add(new WeaponEntity(3, "a gun", "light machine gun", "/path/2/image.png", 34, 30, 5, 0.5f, 11.3f, 0.3f,
				30, 10, 10, 50, 78, 49));
		weapons.add(new WeaponEntity(4, "one gun", "sniper", "/path/2/image.png", 56, 30, 5, 0.5f, 11.3f, 0.3f, 30, 10,
				10, 50, 78, 49));

		return weapons;
	}

}
