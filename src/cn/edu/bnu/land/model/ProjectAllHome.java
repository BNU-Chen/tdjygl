package cn.edu.bnu.land.model;

// Generated 2013-9-3 14:38:55 by Hibernate Tools 4.0.0

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Home object for domain model class ProjectAll.
 * @see cn.edu.bnu.land.model.ProjectAll
 * @author Hibernate Tools
 */
@Repository
public class ProjectAllHome {
	private static final Log log = LogFactory.getLog(ProjectAllHome.class);
	//private final SessionFactory sessionFactory = getSessionFactory();
	private SessionFactory sessionFactory;
	@Autowired   //添加注解和sessionFactory对象
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
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

	//条件查询
	/*先按目标字段精确查询，若无结果，在进行模糊查询
	 * 日期字段均为模糊检索
	 * 参数start分页首记录数，limit分页每页记录数，searchField搜索关键词 
	 */
	public Map<String,Object> selectTb(String start,String limit,String searchField)
	{
		String text=searchField;
		String hql="";
		String hql1="from ProjectAll as projectAll where  ";
		String totalConut=new String();
		//如果输入条件为空，则全部数据显示；
		if(searchField.equals("") ||searchField.isEmpty())
		{
			hql="from ProjectAll as projectAll";
		}
		else
		{ 
			if(text.equals("true"))text="1";
			if(text.equals("false")) text="0";
			String hql2="project_all.ProjectName="+text+
					"or project_all.DesignUnit="+text; // searchField;//prodb已经获取到了Prodb类
			hql=hql1+hql2;
		} 
		System.out.println(hql);
		List<ProjectAll> results=null;
		try{
			org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if(!query.list().isEmpty())
				totalConut=String.valueOf(query.list().size());//获取此次搜索结果的总记录数
			query.setFirstResult(Integer.parseInt(start));//设置所有结果的首记录位置
			query.setMaxResults(Integer.parseInt(limit));//设置所有结果的每页显示的记录数
			results=(List<ProjectAll>)query.list();
			if(results.isEmpty())
			{
				//开始模糊查询
				if(!searchField.equals("") && !searchField.isEmpty())
				{
					text="'%"+text+"%'";
					String hql3="project_all.ProjectName like "+text+
							"or project_all.DesignUnit like "+text;
					hql=hql1+hql3;
				}
				else 
					hql=" from ProjectAll ";
				System.out.println(hql);
				org.hibernate.Query myquery=sessionFactory.getCurrentSession().createQuery(hql);

				if(!myquery.list().isEmpty())
					totalConut=String.valueOf(myquery.list().size());
				myquery.setFirstResult(Integer.parseInt(start));
				myquery.setMaxResults(Integer.parseInt(limit));
				results=(List<ProjectAll>)query.list();
			}
		}
		catch(RuntimeException re)
		{
		}

		Map<String,Object> myMapResult=new TreeMap<String,Object>();

		System.out.println("记录总数："+totalConut);
		myMapResult.put("total", new String(totalConut));
		myMapResult.put("root",results);

		return myMapResult;
	}

	//要公示的所有项目
	public Map<String, Object> SchemeToShowLoad(String start,String limit){
		String hql=null;
		String totalConut=new String();
		hql="from ProjectAll as projectAll where projectAll.isShow='0'";		
		System.out.println(hql); 
		List<ProjectAll> results=null;
		try{
			org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if(!query.list().isEmpty())
				totalConut=String.valueOf(query.list().size());//获取此次搜索结果的总记录数
			query.setFirstResult(Integer.parseInt(start));//设置所有结果的首记录位置
			query.setMaxResults(Integer.parseInt(limit));//设置所有结果的每页显示的记录数
			results=(List<ProjectAll>)query.list();
			for(ProjectAll projectAll:results){
				System.out.println(projectAll.getProjectName());
			}	    
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> myMapResult=new TreeMap<String,Object>();
		System.out.println("记录总数："+totalConut);
		myMapResult.put("total", new String(totalConut));
		myMapResult.put("root",results);

		return myMapResult;
		}
	
	
	//要公示的项目里面搜索符合条件的项目
//	public Map<String,Object> SchemeToShowButton(String start,String limit,String searchField)
//	{
//		String text=searchField;
//		String hql="";
//		String hql1="from ProjectAll as projectAll where  projectAll.isShow='0' and ";
//		String totalConut=new String();
//		//如果输入条件为空，则全部数据显示；
//		if(searchField.equals("") ||searchField.isEmpty())
//		{
//			hql="from ProjectAll as projectAll";
//		}
//		else
//		{ 
//			if(text.equals("true"))text="1";
//			if(text.equals("false")) text="0";
//			String hql2="project_all.ProjectName="+text+
//					"or project_all.DesignUnit="+text; // searchField;//prodb已经获取到了Prodb类
//			
//			hql=hql1+hql2;
//		} 
//		System.out.println(hql);
//		List<ProjectAll> results=null;
//		try{
//			org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
//			if(!query.list().isEmpty())
//				totalConut=String.valueOf(query.list().size());//获取此次搜索结果的总记录数
//			query.setFirstResult(Integer.parseInt(start));//设置所有结果的首记录位置
//			query.setMaxResults(Integer.parseInt(limit));//设置所有结果的每页显示的记录数
//			results=(List<ProjectAll>)query.list();
//			if(results.isEmpty())
//			{
//				//开始模糊查询
//				if(!searchField.equals("") && !searchField.isEmpty())
//				{
//					text="'%"+text+"%'";
//					String hql3="project_all.ProjectName like "+text+
//							"or project_all.DesignUnit like "+text;
//					hql=hql1+hql3;
//				}
//				else 
//					hql=" from ProjectAll ";
//				System.out.println(hql);
//				org.hibernate.Query myquery=sessionFactory.getCurrentSession().createQuery(hql);
//
//				if(!myquery.list().isEmpty())
//					totalConut=String.valueOf(myquery.list().size());
//				myquery.setFirstResult(Integer.parseInt(start));
//				myquery.setMaxResults(Integer.parseInt(limit));
//				results=(List<ProjectAll>)query.list();
//			}
//		}
//		catch(RuntimeException re)
//		{
//		}
//
//		Map<String,Object> myMapResult=new TreeMap<String,Object>();
//
//		System.out.println("记录总数："+totalConut);
//		myMapResult.put("total", new String(totalConut));
//		myMapResult.put("root",results);
//
//		return myMapResult;
//	}
//
//	
//	
	
	//测试
	public Map<String, Object> SchemeToShowButton(String start,String limit,String searchField){
		String hql=null;
		String totalConut=new String();
		hql="from ProjectAll as projectAll where projectAll.projectName='云台镇复垦项目'";		
		System.out.println(hql); 
		System.out.println(searchField);

		List<ProjectAll> results=null;
		try{
			org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if(!query.list().isEmpty())
				totalConut=String.valueOf(query.list().size());//获取此次搜索结果的总记录数
			query.setFirstResult(Integer.parseInt(start));//设置所有结果的首记录位置
			query.setMaxResults(Integer.parseInt(limit));//设置所有结果的每页显示的记录数
			results=(List<ProjectAll>)query.list();
			for(ProjectAll projectAll:results){
				System.out.println(projectAll.getProjectName());
			}	    
		}		
		catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> myMapResult=new TreeMap<String,Object>();
		System.out.println("记录总数："+totalConut);
		myMapResult.put("total", new String(totalConut));
		myMapResult.put("root",results);

		return myMapResult;
		}

	//	//查看存档数据
	//	public List<ProjectAll> selectProRecord(){
	//		//		String hql=null;
	//		//		hql="from ProjectAll as projectAll where prodb_all.Isfinish='true'";		 
	//		List<ProjectAll> results=null;
	//		//		try{
	//		//			org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
	//		//			results=query.list();
	//		//			for(ProjectAll projectAll:results){
	//		//				System.out.println(projectAll.getprojectName());
	//		//			}	    
	//		//		}
	//		//		catch(Exception e){
	//		//			e.printStackTrace();
	//		//		}	
	//		return results;
	//	}
	//

	public void persist(ProjectAll transientInstance) {
		log.debug("persisting ProjectAll instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProjectAll instance) {
		log.debug("attaching dirty ProjectAll instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProjectAll instance) {
		log.debug("attaching clean ProjectAll instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProjectAll persistentInstance) {
		log.debug("deleting ProjectAll instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProjectAll merge(ProjectAll detachedInstance) {
		log.debug("merging ProjectAll instance");
		try {
			ProjectAll result = (ProjectAll) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProjectAll findById(java.lang.String id) {
		log.debug("getting ProjectAll instance with id: " + id);
		try {
			ProjectAll instance = (ProjectAll) sessionFactory
					.getCurrentSession().get(
							"cn.edu.bnu.land.model.ProjectAll", id);
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

	public List findByExample(ProjectAll instance) {
		log.debug("finding ProjectAll instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.ProjectAll")
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

