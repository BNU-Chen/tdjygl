package cn.edu.bnu.land.model;

// Generated 2014-5-30 15:01:26 by Hibernate Tools 4.0.0

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class Zbjpssjg.
 * @see cn.edu.bnu.land.model.Zbjpssjg
 * @author Hibernate Tools
 */
@Repository
public class ZbjpssjgHome {

	private static final Log log = LogFactory.getLog(ZbjpssjgHome.class);

	private  SessionFactory sessionFactory ;

	public void persist(Zbjpssjg transientInstance) {
		log.debug("persisting Zbjpssjg instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zbjpssjg instance) {
		log.debug("attaching dirty Zbjpssjg instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zbjpssjg instance) {
		log.debug("attaching clean Zbjpssjg instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zbjpssjg persistentInstance) {
		log.debug("deleting Zbjpssjg instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zbjpssjg merge(Zbjpssjg detachedInstance) {
		log.debug("merging Zbjpssjg instance");
		try {
			Zbjpssjg result = (Zbjpssjg) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zbjpssjg findById(java.lang.Integer id) {
		log.debug("getting Zbjpssjg instance with id: " + id);
		try {
			Zbjpssjg instance = (Zbjpssjg) sessionFactory.getCurrentSession()
					.get("cn.edu.bnu.land.model.Zbjpssjg", id);
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

	public List findByExample(Zbjpssjg instance) {
		log.debug("finding Zbjpssjg instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Zbjpssjg")
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
