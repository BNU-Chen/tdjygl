package cn.edu.bnu.land.model;

// Generated 2014-5-25 20:00:21 by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class Zbpmjj.
 * @see cn.edu.bnu.land.model.Zbpmjj
 * @author Hibernate Tools
 */
@Repository
public class ZbpmjjHome {

	private static final Log log = LogFactory.getLog(ZbpmjjHome.class);

	private  SessionFactory sessionFactory ;

	public void persist(Zbpmjj transientInstance) {
		log.debug("persisting Zbpmjj instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zbpmjj instance) {
		log.debug("attaching dirty Zbpmjj instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zbpmjj instance) {
		log.debug("attaching clean Zbpmjj instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zbpmjj persistentInstance) {
		log.debug("deleting Zbpmjj instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zbpmjj merge(Zbpmjj detachedInstance) {
		log.debug("merging Zbpmjj instance");
		try {
			Zbpmjj result = (Zbpmjj) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zbpmjj findById(java.lang.Integer id) {
		log.debug("getting Zbpmjj instance with id: " + id);
		try {
			Zbpmjj instance = (Zbpmjj) sessionFactory.getCurrentSession().get(
					"cn.edu.bnu.land.model.Zbpmjj", id);
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

	public List findByExample(Zbpmjj instance) {
		log.debug("finding Zbpmjj instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Zbpmjj")
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
