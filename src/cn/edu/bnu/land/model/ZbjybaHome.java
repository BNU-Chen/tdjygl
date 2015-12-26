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
 * Home object for domain model class Zbjyba.
 * @see cn.edu.bnu.land.model.Zbjyba
 * @author Hibernate Tools
 */
@Repository
public class ZbjybaHome {

	private static final Log log = LogFactory.getLog(ZbjybaHome.class);

	private  SessionFactory sessionFactory ;


	public void persist(Zbjyba transientInstance) {
		log.debug("persisting Zbjyba instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Zbjyba instance) {
		log.debug("attaching dirty Zbjyba instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Zbjyba instance) {
		log.debug("attaching clean Zbjyba instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Zbjyba persistentInstance) {
		log.debug("deleting Zbjyba instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Zbjyba merge(Zbjyba detachedInstance) {
		log.debug("merging Zbjyba instance");
		try {
			Zbjyba result = (Zbjyba) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Zbjyba findById(java.lang.Integer id) {
		log.debug("getting Zbjyba instance with id: " + id);
		try {
			Zbjyba instance = (Zbjyba) sessionFactory.getCurrentSession().get(
					"cn.edu.bnu.land.model.Zbjyba", id);
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

	public List findByExample(Zbjyba instance) {
		log.debug("finding Zbjyba instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Zbjyba")
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
