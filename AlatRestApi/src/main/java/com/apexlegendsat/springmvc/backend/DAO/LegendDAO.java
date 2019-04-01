package com.apexlegendsat.springmvc.backend.DAO;

import java.util.List;

import com.apexlegendsat.springmvc.backend.entity.LegendEntity;

public interface LegendDAO {
	
	void deleteLegendEntityById(int legendId);
	
	List<LegendEntity> findAllLegendEntities();
	
	LegendEntity findLegendEntityById(int legendId);
	
	void saveLegendEntity(LegendEntity legendEntity);

	LegendEntity findLegendEntityByName(String userName);

}
