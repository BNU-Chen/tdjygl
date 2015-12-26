package cn.edu.bnu.land.model;

// Generated 2013-8-16 10:11:46 by Hibernate Tools 4.0.0

import java.util.List;


import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.bnu.land.common.SpringContextHolder;
import static org.hibernate.criterion.Example.create;
/**
 * Home object for domain model class Transinfo.
 * @see dao.Transinfo
 * @author Hibernate Tools
 */
@Repository
public class CrawlkeywordsHome {

	private static final Log log = LogFactory.getLog(CrawlkeywordsHome.class);

	
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

	public void persist(Crawlurl transientInstance) {
		log.debug("persisting Landquota instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Crawlurl instance) {
		log.debug("attaching dirty Landquota instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Crawlurl instance) {
		log.debug("attaching clean Landquota instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Crawlurl persistentInstance) {
		log.debug("deleting Landquota instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Crawlurl merge(Crawlurl detachedInstance) {
		log.debug("merging Landquota instance");
		try {
			Crawlurl result = (Crawlurl) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Crawlurl findById(java.lang.Integer id) {
		log.debug("getting Transinfo instance with id: " + id);
		try {
			Crawlurl instance = (Crawlurl) sessionFactory.getCurrentSession()
					.get("cn.edu.bnu.land.model.Landquota", id);
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
	
	public void find(CrawlkeywordsHome landquota) {
		Session session = sessionFactory.getCurrentSession();
		
//		session.createSQLQuery(transinfo.getArea());

	}

	
	
	public List findByExample(CrawlkeywordsHome instance) {
		log.debug("finding Landquota instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Landquota")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			System.out.println(results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	
	}
	
	public List<Crawlkeywords> selectkeywords(){		
		System.out.print("关键词Home_before");
		String hql="from Crawlkeywords as Crawlkeywords";
	    List<Crawlkeywords> results=null;
	    try{
	    	org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	    	results=query.list();
	    	for(Crawlkeywords crawlkeywords:results){
	    		System.out.println(crawlkeywords.getId()+ crawlkeywords.getKeywordsname());
	    	}	    
	    }
		catch(Exception e){
			e.printStackTrace();
		}	
	    return results;
	}

	public void save(Crawlkeywords crawlkeywords) {
		Session session = sessionFactory.getCurrentSession();
		session.save(crawlkeywords);
	}
	
}
