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
public class JsdatafieldHome {

	private static final Log log = LogFactory.getLog(JsdatafieldHome.class);

	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(Jsdatafield transientInstance) {
		log.debug("persisting Jsdatafield instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Jsdatafield instance) {
		log.debug("attaching dirty Jsdatafield instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Jsdatafield instance) {
		log.debug("attaching clean Jsdatafield instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Jsdatafield persistentInstance) {
		log.debug("deleting Jsdatafield instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Jsdatafield merge(Jsdatafield detachedInstance) {
		log.debug("merging Jsdatafield instance");
		try {
			Jsdatafield result = (Jsdatafield) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Jsdatafield findById(java.lang.Integer id) {
		log.debug("getting Jsdatafield instance with id: " + id);
		try {
			Jsdatafield instance = (Jsdatafield) sessionFactory.getCurrentSession()
					.get("dao.Jsdatafield", id);
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
	
	public void find(Jsdatafield jsdataField) {
		Session session = sessionFactory.getCurrentSession();
		
//		session.createSQLQuery(transinfo.getArea());

	}

	
	
	public List findByExample(Jsdatafield instance) {
		log.debug("finding Jsdatafield instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Jsdatafield")
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
	
	public List<Jsdatafield> selectTb(){		
		System.out.print("Home_before");
		String hql="from Jsdatafield as jsdatafield";
	    List<Jsdatafield> results=null;
	    try{
	    	org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	    	results=query.list();
	    	for(Jsdatafield jsdatafield:results){
	    		System.out.println(jsdatafield.getLocaldataField());
	    	}	    
	    }
		catch(Exception e){
			e.printStackTrace();
		}	
	    return results;
	}
}
