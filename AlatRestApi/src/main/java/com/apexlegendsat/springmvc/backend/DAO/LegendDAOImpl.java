package com.apexlegendsat.springmvc.backend.DAO;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.apexlegendsat.springmvc.backend.entity.LegendEntity;

@Repository
public class LegendDAOImpl extends AbstractDAO implements LegendDAO {

	static Logger logger = LogManager.getLogger(LegendDAOImpl.class.getName());
	
	@Override
	public void deleteLegendEntityById(int legendId) {
		Query deleteQuery = getSession().createQuery("delete from LegendEntity where id =:legendId");
		deleteQuery.setInteger("legendId", legendId);
		
		deleteQuery.executeUpdate();
	}

	@Override
	public List<LegendEntity> findAllLegendEntities() {
		logger.debug("Grabbing all the legends!");
		
		Criteria criteria = getSession().createCriteria(LegendEntity.class);
		return (List<LegendEntity>) criteria.list();
	}

	@Override
	public LegendEntity findLegendEntityById(int legendId) {
		Criteria criteria = getSession().createCriteria(LegendEntity.class);
		criteria.add(Restrictions.eq("id", legendId));
		
		return (LegendEntity) criteria.uniqueResult();
	}
	
	@Override
	public LegendEntity findLegendEntityByName(String name) {
		
		logger.debug("Legend : " + name);
		String hqlString = "from LegendEntity where name =:name";
		Query findQuery = getSession().createQuery(hqlString);
		findQuery.setString("name", name);
		
		return (LegendEntity) findQuery.uniqueResult();
	}

	@Override
	public void saveLegendEntity(LegendEntity legendEntity) {
		getSession().persist(legendEntity);
	}

}
