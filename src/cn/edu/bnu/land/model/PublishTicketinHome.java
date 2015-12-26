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
 * Home object for domain model class PublishTicketin.
 * @see cn.edu.bnu.land.model.PublishTicketin
 * @author Hibernate Tools
 */
public class PublishTicketinHome {

	private static final Log log = LogFactory.getLog(PublishTicketinHome.class);

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

	public void persist(PublishTicketin transientInstance) {
		log.debug("persisting PublishTicketin instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PublishTicketin instance) {
		log.debug("attaching dirty PublishTicketin instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PublishTicketin instance) {
		log.debug("attaching clean PublishTicketin instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PublishTicketin persistentInstance) {
		log.debug("deleting PublishTicketin instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PublishTicketin merge(PublishTicketin detachedInstance) {
		log.debug("merging PublishTicketin instance");
		try {
			PublishTicketin result = (PublishTicketin) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PublishTicketin findById(java.lang.Integer id) {
		log.debug("getting PublishTicketin instance with id: " + id);
		try {
			PublishTicketin instance = (PublishTicketin) sessionFactory
					.getCurrentSession().get(
							"cn.edu.bnu.land.model.PublishTicketin", id);
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

	public List findByExample(PublishTicketin instance) {
		log.debug("finding PublishTicketin instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.PublishTicketin")
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
