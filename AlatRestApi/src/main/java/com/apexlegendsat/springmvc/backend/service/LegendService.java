package com.apexlegendsat.springmvc.backend.service;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.LegendEntity;

public interface LegendService {

	void deleteLegendById(int id);

	boolean doesLegendExist(LegendEntity legendEntity);

	List<LegendEntity> findAllLegends();

	LegendEntity findById(int id);

	LegendEntity findByName(String name);

	void saveLegend(LegendEntity legendEntity);

}
