package com.apexlegendsat.springmvc.backend.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.hibernate.Criteria;

import org.springframework.stereotype.Repository;

import com.apexlegendsat.springmvc.backend.entity.WeaponEntity;

@Repository
public class WeaponDaoImpl extends AbstractDAO implements WeaponDAO {

	static Logger logger = LogManager.getLogger(WeaponDaoImpl.class.getName());
	
	@Override
	public void deleteWeaponEntityById(int weaponId) {
		
		logger.debug("Attempting Weapon Delete.");
		
		Query deleteQuery = getSession().createQuery("delete from WeaponEntity where id =:weaponId");
		deleteQuery.setLong("weaponId", weaponId);

		deleteQuery.executeUpdate();
	}

	@Override
	public List<WeaponEntity> findAllWeaponEntities() {
		logger.info("Get the entities");
		Criteria criteria = getSession().createCriteria(WeaponEntity.class);
		return (List<WeaponEntity>) criteria.list();
	}

	@Override
	public WeaponEntity findWeaponEntityById(int weaponId) {
		Criteria criteria = getSession().createCriteria(WeaponEntity.class);
		criteria.add(Restrictions.eq("id", weaponId));

		return (WeaponEntity) criteria.uniqueResult();
	}

	@Override
	public WeaponEntity findWeaponEntityByName(String weaponName) {
		Query findQuery = getSession().createQuery("from WeaponEntity where name =:weaponName");
		findQuery.setString("weaponName", weaponName);

		return (WeaponEntity) findQuery.uniqueResult();
	}

	@Override
	public void saveWeaponEntity(WeaponEntity weaponEntity) {
		getSession().persist(weaponEntity);
	}

	@Override
	public void updateWeaponEntity(WeaponEntity weaponEntity) {
		getSession().update(weaponEntity);
	}

}
