package cn.edu.bnu.land.model;

// Generated 2013-10-5 11:38:47 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class RssChannelItems.
 * @see cn.edu.bnu.land.model.RssChannelItems
 * @author Hibernate Tools
 */
public class RssChannelItemsHome {

	private static final Log log = LogFactory.getLog(RssChannelItemsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

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

	public void persist(RssChannelItems transientInstance) {
		log.debug("persisting RssChannelItems instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(RssChannelItems instance) {
		log.debug("attaching dirty RssChannelItems instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RssChannelItems instance) {
		log.debug("attaching clean RssChannelItems instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(RssChannelItems persistentInstance) {
		log.debug("deleting RssChannelItems instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RssChannelItems merge(RssChannelItems detachedInstance) {
		log.debug("merging RssChannelItems instance");
		try {
			RssChannelItems result = (RssChannelItems) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RssChannelItems findById(java.lang.Integer id) {
		log.debug("getting RssChannelItems instance with id: " + id);
		try {
			RssChannelItems instance = (RssChannelItems) sessionFactory
					.getCurrentSession().get(
							"cn.edu.bnu.land.model.RssChannelItems", id);
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

	public List findByExample(RssChannelItems instance) {
		log.debug("finding RssChannelItems instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.RssChannelItems")
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
