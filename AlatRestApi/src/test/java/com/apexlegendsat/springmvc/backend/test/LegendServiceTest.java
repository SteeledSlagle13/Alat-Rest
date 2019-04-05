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

import com.apexlegendsat.springmvc.backend.DAO.LegendDAOImpl;
import com.apexlegendsat.springmvc.backend.entity.LegendEntity;
import com.apexlegendsat.springmvc.backend.service.LegendServiceImpl;

public class LegendServiceTest {

	@Mock
	private LegendDAOImpl legendDao;

	@InjectMocks
	private LegendServiceImpl legendService;

	@Spy
	private List<LegendEntity> legends = new ArrayList<LegendEntity>();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		legends = getLegends();
	}

	@After
	public void tearDown() throws Exception {
		legends = null;
	}

	@Test
	public void findAllWeapons_Positive() {
		when(legendDao.findAllLegendEntities()).thenReturn(legends);
		Assert.assertEquals(legendService.findAllLegends(), legends);
	}

	@Test
	public void findById_Positive() {
		LegendEntity legend = legends.get(0);
		when(legendDao.findLegendEntityById((anyInt()))).thenReturn(legend);
		Assert.assertEquals(legendService.findById(legend.getId()), legend);
	}
	
	@Test
	public void findByName_Positive() {
		LegendEntity legend = legends.get(0);
		when(legendDao.findLegendEntityByName(anyString())).thenReturn(legend);
		Assert.assertEquals(legendService.findByName(legend.getName()), legend);
	}

	@Test
	public void saveWeapon_Positive() {
		doNothing().when(legendDao).saveLegendEntity(any(LegendEntity.class));
		legendService.saveLegend(new LegendEntity(45, "shellly", "defense",  "shelly Gill", "21", "an Ability", "digesting food", "unleashing the stuff", "shelly/family/photo.png"));
		verify(legendDao, atLeastOnce()).saveLegendEntity(any(LegendEntity.class));
	}
 
	@Test
    public void deleteWeaponById_Positive(){
        doNothing().when(legendDao).deleteLegendEntityById((anyInt()));
        legendService.deleteLegendById(anyInt());
        verify(legendDao, atLeastOnce()).deleteLegendEntityById(anyInt());
    }

	public List<LegendEntity> getLegends() {

		legends.add(new LegendEntity(1, "billy", "support",  "Bill Gill", "20", "an Ability", "digesting food", "unleashing the stuff", "bill/family/photo.png"));
		legends.add(new LegendEntity(2, "lilly", "snacker",  "lill Gill", "22", "an Ability", "digesting food", "unleashing the stuff", "lill/family/photo.png"));
		legends.add(new LegendEntity(3, "nilly", "defense",  "nill Gill", "21", "an Ability", "digesting food", "unleashing the stuff", "nill/family/photo.png"));
		legends.add(new LegendEntity(4, "willy", "medium",   "will Gill", "26", "an Ability", "digesting food", "unleashing the stuff", "will/family/photo.png"));
		legends.add(new LegendEntity(5, "boabo", "attacker", "bobo Gill", "31", "an Ability", "digesting food", "unleashing the stuff", "bobo/family/photo.png"));
		
		return legends;
	}

}
