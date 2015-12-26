package cn.edu.bnu.land.model;

// Generated 2013-8-19 16:49:52 by Hibernate Tools 4.0.0

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;

import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.bnu.land.common.SpringContextHolder;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Syncpush.
 * @see cn.edu.bnu.land.model.Syncpush
 * @author Hibernate Tools
 */
@Repository
public class SyncpushHome {

	private static final Log log = LogFactory.getLog(SyncpushHome.class);

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

	public void persist(Syncpush transientInstance) {
		log.debug("persisting Syncpush instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Syncpush instance) {
		log.debug("attaching dirty Syncpush instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Syncpush instance) {
		log.debug("attaching clean Syncpush instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Syncpush persistentInstance) {
		log.debug("deleting Syncpush instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Syncpush merge(Syncpush detachedInstance) {
		log.debug("merging Syncpush instance");
		try {
			Syncpush result = (Syncpush) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Syncpush findById(java.lang.Integer id) {
		log.debug("getting Syncpush instance with id: " + id);
		try {
			Syncpush instance = (Syncpush) sessionFactory.getCurrentSession()
					.get("cn.edu.bnu.land.model.Syncpush", id);
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

	public List<Syncpush> findByExample(Syncpush instance) {
		log.debug("finding Syncpush instance by example");
		try {
			List<Syncpush> results = (List<Syncpush>) sessionFactory
					.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.Syncpush")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/*
	 * 先按照目标字段精确检索，若无结果，再进行模糊检索。
	 * 
	 * 参数start分页首记录数，limit分页每页记录数，searchField搜索关键词
	 * */
	public Map<String,Object> selectTb(String start,String limit,String searchField,String dateType,String searchDate){		
			
		String text=searchField;
		String hql="";
		String hql1="from Syncpush s where 1=1 ";
		String totalConut=new String();
		//搜索关键词不为空
		if(!searchField.equals("") && !searchField.isEmpty())
			{
			if(text.equals("true"))text="1";
			if(text.equals("false")) text="0";
			String hql2="";
			if(!searchDate.equals("") && !searchDate.isEmpty())//日期搜索不为空
			{
				 hql2=" and ( s.ticketId='"+text+"'"+
							" or s.isPush="+text+
							" or s.area="+text+")";
				//查询发布日期
				if(dateType.equals("")||dateType.equals("pubDate"))
				
				    hql2+=" and s.publishDate>='"+searchDate+"'";
				 //查询截止日期
				else
					hql2+=" and s.deadline>='"+searchDate+"'";
			}
			else{
			     hql2=" and (s.ticketId='"+text+"'"+
					" or s.isPush="+text+
					" or s.area="+text+")";
			}
			
			hql=hql1+hql2;
		}
		
		else if((searchField.equals("") || searchField.isEmpty())&&!searchDate.equals("") && !searchDate.isEmpty())
		{
			//查询发布日期
			if(dateType.equals("")||dateType.equals("pubDate"))
			
			    hql=hql1+" and s.publishDate>='"+searchDate+"'";
			 //查询截止日期
			else
				hql=hql1+" and  s.deadline>='"+searchDate+"'";
		}
		else
			hql=" from Syncpush ";	
		
		System.out.println(hql);
	    List<Syncpush> results=null;
	    try{
		    org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		    
		    if(!query.list().isEmpty())
		    totalConut=String.valueOf(query.list().size());

		    query.setFirstResult(Integer.parseInt(start));
		    query.setMaxResults(Integer.parseInt(limit));
		    
		    results=(List<Syncpush>)query.list();
		    if (results.isEmpty())
		    {	 
		    	String hql3="";
		        //开始模糊查询
		    	if(!searchField.equals("") && !searchField.isEmpty())
		    	{
					text="'%"+text+"%'";	
                    hql3=" and ( s.ticketId like "+text+
							" or s.isPush like "+text+
							" or s.area like "+text+")";
		    	}
		    	if(!searchDate.equals("") && !searchDate.isEmpty())
		    	{
		    		//查询发布日期
					if(dateType.equals("")||dateType.equals("pubDate"))
					
					    hql3+=" and s.publishDate>='"+searchDate+"'";
					 //查询截止日期
					else
						hql3+=" and s.deadline>='"+searchDate+"'";
		    	}
		    	hql=hql1+hql3;
				System.out.println(hql);				
			    org.hibernate.Query myquery=sessionFactory.getCurrentSession().createQuery(hql);
			    
			    if(!myquery.list().isEmpty())
				    totalConut=String.valueOf(myquery.list().size());
			    
			    myquery.setFirstResult(Integer.parseInt(start));
			    myquery.setMaxResults(Integer.parseInt(limit));
			    
			    results=(List<Syncpush>)myquery.list();		    
		    }
	    }
	    catch(RuntimeException re)
	    {
	    }
	    
	    
	    Map<String,Object> myMapResult= new TreeMap<String,Object>();
	   
	    System.out.println("记录总数： "+totalConut);
	    myMapResult.put("total", new String(totalConut));
	    myMapResult.put("root",results);
	    
	    return myMapResult;
	}
	
	
	/*
	 * 先按照目标字段精确检索，若无结果，再进行模糊检索。
	 * 
	 * 参数start分页首记录数，limit分页每页记录数，searchField搜索关键词
	 * */
	public Map<String,Object> selectMailPushTb(String start,String limit,String searchField,String dateType,String searchDate){		
			
		String text=searchField;
		String hql="";
		String hql1="from Syncpush s where 1=1 ";
		String totalConut=new String();
		//搜索关键词不为空
		if(!searchField.equals("") && !searchField.isEmpty())
			{
			if(text.equals("true"))text="1";
			if(text.equals("false")) text="0";
			String hql2="";
			if(!searchDate.equals("") && !searchDate.isEmpty())//日期搜索不为空
			{
				 hql2=" and (s.ticketId='"+text+"'"+
							" or s.isMailPush="+text+
							" or s.area="+text+")";
				//查询发布日期
				if(dateType.equals("")||dateType.equals("pubDate"))
				
				    hql2+=" and s.publishDate>='"+searchDate+"'";
				 //查询截止日期
				else
					hql2+=" and s.deadline>='"+searchDate+"'";
			}
			else{
			     hql2=" and (s.ticketId='"+text+"'"+
					" or s.isMailPush="+text+
					" or s.area="+text+")";
			}
			
			hql=hql1+hql2;
		}
		
		else if((searchField.equals("") || searchField.isEmpty())&&!searchDate.equals("") && !searchDate.isEmpty())
		{
			//查询发布日期
			if(dateType.equals("")||dateType.equals("pubDate"))
			
			    hql=hql1+" and s.publishDate>='"+searchDate+"'";
			 //查询截止日期
			else
				hql=hql1+" and  s.deadline>='"+searchDate+"'";
		}
		else
			hql=" from Syncpush ";	
		
		System.out.println(hql);
	    List<Syncpush> results=null;
	    try{
		    org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		    
		    if(!query.list().isEmpty())
		    totalConut=String.valueOf(query.list().size());

		    query.setFirstResult(Integer.parseInt(start));
		    query.setMaxResults(Integer.parseInt(limit));
		    
		    results=(List<Syncpush>)query.list();
		    if (results.isEmpty() ||results ==null )
		    {	 
		    	System.out.println("检索全部与模糊检索结果均为空");
		    	String hql3="";
		        //开始模糊查询
		    	if(!searchField.equals("") && !searchField.isEmpty())
		    	{
					text="'%"+text+"%'";	
                    hql3=" and ( s.ticketId like "+text+
							" or s.isMailPush like "+text+
							" or s.area like "+text+")";
		    	}
		    	if(!searchDate.equals("") && !searchDate.isEmpty())
		    	{
		    		//查询发布日期
					if(dateType.equals("")||dateType.equals("pubDate"))
					
					    hql3+=" and s.publishDate>='"+searchDate+"'";
					 //查询截止日期
					else
						hql3+=" and s.deadline>='"+searchDate+"'";
		    	}
		    	hql=hql1+hql3;
				System.out.println(hql);				
			    org.hibernate.Query myquery=sessionFactory.getCurrentSession().createQuery(hql);
			    
			    if(!myquery.list().isEmpty())
				    totalConut=String.valueOf(myquery.list().size());
			    
			    myquery.setFirstResult(Integer.parseInt(start));
			    myquery.setMaxResults(Integer.parseInt(limit));
			    
			    results=(List<Syncpush>)myquery.list();		    
		    }
	    }
	    catch(RuntimeException re)
	    {
	    }
	    
	    
	    Map<String,Object> myMapResult= new TreeMap<String,Object>();
	   
	    System.out.println("记录总数： "+totalConut);
	    myMapResult.put("total", new String(totalConut));
	    myMapResult.put("root",results);
	    
	    return myMapResult;
	}
	
	public Syncpush selectById(int id)
	{
		Session session = sessionFactory.getCurrentSession();
		Syncpush syncpush=(Syncpush)session.get(Syncpush.class,id);      
		return syncpush;
	}
	
	public List<User> getAllData(){
		String hql="from Syncpush u where 1=1 ";
		List<User> results=null;
		org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		results=(List<User>)query.list();
		return results;
	}
	
}
