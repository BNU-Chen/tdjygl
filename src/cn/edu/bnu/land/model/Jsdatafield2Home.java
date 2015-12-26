package cn.edu.bnu.land.model;

import java.util.List;



import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Jsdatafield2Home {

	private static final Log log = LogFactory.getLog(Jsdatafield2Home.class);

	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(Jsdatafield2 transientInstance) {
		log.debug("persisting Jsdatafield2 instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Jsdatafield2 instance) {
		log.debug("attaching dirty Jsdatafield2 instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Jsdatafield2 instance) {
		log.debug("attaching clean Jsdatafield2 instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Jsdatafield2 persistentInstance) {
		log.debug("deleting Jsdatafield2 instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Jsdatafield2 merge(Jsdatafield2 detachedInstance) {
		log.debug("merging Jsdatafield2 instance");
		try {
			Jsdatafield2 result = (Jsdatafield2) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Jsdatafield2 findById(java.lang.Integer id) {
		log.debug("getting Jsdatafield2 instance with id: " + id);
		try {
			Jsdatafield2 instance = (Jsdatafield2) sessionFactory.getCurrentSession()
					.get("dao.Jsdatafield2", id);
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
	
	public void find(Jsdatafield2 jsdataField2) {
		Session session = sessionFactory.getCurrentSession();
		
//		session.createSQLQuery(transinfo.getArea());

	}

	
	
	public List findByExample(Jsdatafield2 instance) {
		log.debug("finding Jsdatafield2 instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Jsdatafield2")
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
	
	public List<Jsdatafield2> selectTb(){		
		System.out.print("Home_before");
		String hql="from Jsdatafield2 as jsdatafield2";
	    List<Jsdatafield2> results=null;
	    try{
	    	org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	    	results=query.list();
	    	for(Jsdatafield2 jsdatafield2:results){
	    		System.out.println(jsdatafield2.getLocaldataField());
	    	}	    
	    }
		catch(Exception e){
			e.printStackTrace();
		}	
	    return results;
	}
}
