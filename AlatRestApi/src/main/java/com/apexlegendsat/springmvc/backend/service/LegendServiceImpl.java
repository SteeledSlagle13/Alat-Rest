package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apexlegendsat.springmvc.backend.DAO.LegendDAO;
import com.apexlegendsat.springmvc.backend.entity.LegendEntity;

@Service
@Transactional
public class LegendServiceImpl implements LegendService {

	static Logger Logger = LogManager.getLogger(LegendServiceImpl.class.getName());

	@Autowired
	private LegendDAO legendDao;

	@Override
	public void deleteLegendById(int id) {
		legendDao.deleteLegendEntityById(id);
	}

	@Override
	public boolean doesLegendExist(LegendEntity legendEntity) {
		return findByName(legendEntity.getName()) != null;
	}

	@Override
	public List<LegendEntity> findAllLegends() {
		List<LegendEntity> legendEntities = legendDao.findAllLegendEntities();

		return legendEntities;
	}

	@Override
	public LegendEntity findById(int id) {
		return legendDao.findLegendEntityById(id);
	}

	@Override
	public LegendEntity findByName(String name) {
		return legendDao.findLegendEntityByName(name);
	}

	@Override
	public void saveLegend(LegendEntity legendEntity) {
		legendDao.saveLegendEntity(legendEntity);
	}

}
