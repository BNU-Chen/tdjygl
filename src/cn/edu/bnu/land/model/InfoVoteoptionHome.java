package cn.edu.bnu.land.model;

// Generated 2013-9-1 17:20:20 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class InfoVoteoption.
 * @see cn.edu.bnu.land.model.InfoVoteoption
 * @author Hibernate Tools
 */
@Repository
public class InfoVoteoptionHome {

	private static final Log log = LogFactory.getLog(InfoVoteoptionHome.class);

	//private final SessionFactory sessionFactory = getSessionFactory();
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(InfoVoteoption transientInstance) {
		log.debug("persisting InfoVoteoption instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InfoVoteoption instance) {
		log.debug("attaching dirty InfoVoteoption instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InfoVoteoption instance) {
		log.debug("attaching clean InfoVoteoption instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InfoVoteoption persistentInstance) {
		log.debug("deleting InfoVoteoption instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InfoVoteoption merge(InfoVoteoption detachedInstance) {
		log.debug("merging InfoVoteoption instance");
		try {
			InfoVoteoption result = (InfoVoteoption) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InfoVoteoption findById(java.lang.Integer id) {
		log.debug("getting InfoVoteoption instance with id: " + id);
		try {
			InfoVoteoption instance = (InfoVoteoption) sessionFactory
					.getCurrentSession().get(
							"cn.edu.bnu.land.model.InfoVoteoption", id);
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

	public List findByExample(InfoVoteoption instance) {
		log.debug("finding InfoVoteoption instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.InfoVoteoption")
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
