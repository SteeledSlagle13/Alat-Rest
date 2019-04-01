package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.LegendEntity;
import com.apexlegendsat.springmvc.backend.view.LegendView;

public interface LegendService {

	LegendEntity convertLegendViewToLegendEntity(LegendView legendView);
	
	LegendView convertLegendEntityToLegendView(LegendEntity legendEnt);
	
	void deleteLegendById(int id);
	
	boolean doesLegendExist(LegendView legendView);
	
	List<LegendView> findAllLegends();
	
	LegendView findById(int id);
	
	LegendView findByName(String name);
	
	void saveLegend(LegendView legendView);
	
}
