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
public class JsfieldmappingHome {

	private static final Log log = LogFactory.getLog(JsfieldmappingHome.class);

	
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void persist(Jsfieldmapping transientInstance) {
		log.debug("persisting Jsfieldmapping instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Jsfieldmapping instance) {
		log.debug("attaching dirty Jsfieldmapping instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Jsfieldmapping instance) {
		log.debug("attaching clean Jsfieldmapping instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Jsfieldmapping persistentInstance) {
		log.debug("deleting Jsfieldmapping instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Jsfieldmapping merge(Jsfieldmapping detachedInstance) {
		log.debug("merging Jsfieldmapping instance");
		try {
			Jsfieldmapping result = (Jsfieldmapping) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Jsfieldmapping findById(java.lang.Integer id) {
		log.debug("getting Jsfieldmapping instance with id: " + id);
		try {
			Jsfieldmapping instance = (Jsfieldmapping) sessionFactory.getCurrentSession()
					.get("dao.Jsfieldmapping", id);
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
	
	public void find(Jsfieldmapping jsfieldmapping) {
		Session session = sessionFactory.getCurrentSession();
		
//		session.createSQLQuery(transinfo.getArea());

	}

	
	
	public List findByExample(Jsfieldmapping instance) {
		log.debug("finding Jsfieldmapping instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Jsfieldmapping")
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
	
	public List<Jsfieldmapping> selectTb(){		
		System.out.print("Home_before");
		String hql="from Jsfieldmapping as jsfieldmapping";
	    List<Jsfieldmapping> results=null;
	    try{
	    	org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	    	results=query.list();
	    	for(Jsfieldmapping jsfieldmapping:results){
	    		System.out.println(jsfieldmapping.getFieldmapping());
	    	}	    
	    }
		catch(Exception e){
			e.printStackTrace();
		}	
	    return results;
	}
}
