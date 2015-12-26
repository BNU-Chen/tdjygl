package cn.edu.bnu.land.model;

// Generated 2014-5-3 8:35:40 by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class Zbcr.
 * @see cn.edu.bnu.land.model.Zbcr
 * @author Hibernate Tools
 */
@Repository
public class ZbcrHome {

	private static final Log log = LogFactory.getLog(ZbcrHome.class);

	private SessionFactory sessionFactory ;

	public void persist(Zbcr transientInstance) {
		log.debug("persisting Zbcr instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zbcr instance) {
		log.debug("attaching dirty Zbcr instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zbcr instance) {
		log.debug("attaching clean Zbcr instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zbcr persistentInstance) {
		log.debug("deleting Zbcr instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zbcr merge(Zbcr detachedInstance) {
		log.debug("merging Zbcr instance");
		try {
			Zbcr result = (Zbcr) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zbcr findById(java.lang.String id) {
		log.debug("getting Zbcr instance with id: " + id);
		try {
			Zbcr instance = (Zbcr) sessionFactory.getCurrentSession().get(
					"cn.edu.bnu.land.model.Zbcr", id);
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

	public List findByExample(Zbcr instance) {
		log.debug("finding Zbcr instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Zbcr")
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
