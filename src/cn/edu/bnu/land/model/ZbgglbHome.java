package cn.edu.bnu.land.model;

// Generated 2014-5-18 23:58:45 by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class Zbgglb.
 * @see cn.edu.bnu.land.model.Zbgglb
 * @author Hibernate Tools
 */
@Repository
public class ZbgglbHome {

	private static final Log log = LogFactory.getLog(ZbgglbHome.class);

	private  SessionFactory sessionFactory ;


	public void persist(Zbgglb transientInstance) {
		log.debug("persisting Zbgglb instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zbgglb instance) {
		log.debug("attaching dirty Zbgglb instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zbgglb instance) {
		log.debug("attaching clean Zbgglb instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zbgglb persistentInstance) {
		log.debug("deleting Zbgglb instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zbgglb merge(Zbgglb detachedInstance) {
		log.debug("merging Zbgglb instance");
		try {
			Zbgglb result = (Zbgglb) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zbgglb findById(java.lang.Integer id) {
		log.debug("getting Zbgglb instance with id: " + id);
		try {
			Zbgglb instance = (Zbgglb) sessionFactory.getCurrentSession().get(
					"cn.edu.bnu.land.model.Zbgglb", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Zbgglb instance) {
		log.debug("finding Zbgglb instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Zbgglb")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
