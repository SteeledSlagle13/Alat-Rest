package com.apexlegendsat.springmvc.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apexlegendsat.springmvc.backend.DAO.LegendDAO;
import com.apexlegendsat.springmvc.backend.entity.LegendEntity;
import com.apexlegendsat.springmvc.backend.view.LegendView;

@Service
@Transactional
public class LegendServiceImpl implements LegendService {
	
	static Logger Logger = LogManager.getLogger(LegendServiceImpl.class.getName());

	@Autowired
	private LegendDAO legendDao;
	
	@Override
	public LegendEntity convertLegendViewToLegendEntity(LegendView legendView) {
		if(legendView == null) {
			return null;
		}
		
		return new LegendEntity(legendView.getId(), legendView.getNickName(), legendView.getRole()
				, legendView.getName(), legendView.getAge(), legendView.getTacticalAbility()
				, legendView.getPassiveAbility(), legendView.getUltimateAbility(), legendView.getImageSource());
	}

	@Override
	public LegendView convertLegendEntityToLegendView(LegendEntity legendEnt) {
		if(legendEnt == null) {
			return null;
		}
		
		return new LegendView(legendEnt.getId(), legendEnt.getNickName(), legendEnt.getRole()
				, legendEnt.getName(), legendEnt.getAge(), legendEnt.getTacticalAbility()
				, legendEnt.getPassiveAbility(), legendEnt.getUltimateAbility(), legendEnt.getImageSource());
	}

	@Override
	public void deleteLegendById(int id) {
		legendDao.deleteLegendEntityById(id);
	}
	
	@Override
	public boolean doesLegendExist(LegendView legendView) {
		return findByName(legendView.getName()) != null;
	}

	@Override
	public List<LegendView> findAllLegends() {
		List<LegendEntity> legendEntities = legendDao.findAllLegendEntities();
		
		List<LegendView> legendViews = new ArrayList<LegendView>();
		for (LegendEntity legendEntity : legendEntities) {
			legendViews.add(convertLegendEntityToLegendView(legendEntity));
		}
		
		return legendViews;
	}

	@Override
	public LegendView findById(int id) {
		return convertLegendEntityToLegendView(legendDao.findLegendEntityById(id));
	}

	@Override
	public LegendView findByName(String name) {
		return convertLegendEntityToLegendView(legendDao.findLegendEntityByName(name));
	}

	@Override
	public void saveLegend(LegendView legendView) {
		legendDao.saveLegendEntity(convertLegendViewToLegendEntity(legendView));
	}
	
	

}
