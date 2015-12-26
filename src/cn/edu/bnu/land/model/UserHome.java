package cn.edu.bnu.land.model;

// Generated 2013-8-19 16:49:52 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.bnu.land.common.SpringContextHolder;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class User.
 * @see cn.edu.bnu.land.model.User
 * @author Hibernate Tools
 */
@Repository
public class UserHome {

	private static final Log log = LogFactory.getLog(UserHome.class);

	
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
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) sessionFactory.getCurrentSession().get(
					"cn.edu.bnu.land.model.User", id);
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

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) sessionFactory
					.getCurrentSession()
					.createCriteria("cn.edu.bnu.land.model.User")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<User> getAll(){
		String hql="from User u where 1=1 ";
		List<User> results=null;
		org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		results=(List<User>)query.list();
		return results;
	}
	/*
	 * 函数功能：获取所有注册用户和搜索符合条件用户       //地票信息异步推送时选择接收对象时需要
	 * 参数说明:start 表格分页参数 起始记录号
	 * 参数说明：limit 表格分页参数 每页记录数
	 * 参数说明：searchField 搜索关键词
	 * 返回值说明： map键值对，total键，查询到的总记录数;root键，记录内容。键名与store中的reader设置相对应
	 * 函数逻辑：searchField为空，查询全部 ，否则按查询关键词先进行精确检索，无结果，再进行模糊检索
	 * */
	public Map<String,Object> getSelectUsers(String start,String limit,String searchField){
		
		String sql="";
		String sql1=" LIMIT "+start+","+limit;
		
		String sqlselect = "SELECT u.user_name,u.true_name,u.email,u.mobile_num,"
				+ " d.cardId,d.lawman_name,d.company_name,u.user_id "
				+ " FROM u_user_info AS u" + " LEFT JOIN user AS d" 
				+ " ON u.user_id = d.user_id" ;				
		String sqlwhere=" WHERE 1=1";			
		List<User> results=null;
		
		int count=0;
		//查询全部
		if(searchField.equals("")||searchField.isEmpty())
		{
			sql=sqlselect+sqlwhere;
		}
		else{
			//精确检索
			searchField="'"+searchField+"'";
			sqlwhere+=" and ("
					+"user.username="+searchField+
					" or u.true_name="+searchField+
					" or u.email="+searchField+
					" or u.mobile_num="+searchField+
					" or d.cardId="+searchField+
					" or d.lawman_name="+searchField+
					" or d.company_name="+searchField
					+")";
			sql=sqlselect+sqlwhere+sql1;
			System.out.println(sql);
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			count =query.list().size();
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
			List<Object[]> userList = query.list();			
			if(!userList.isEmpty()){
				List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
				for (Object[] object : userList) {
					Map<String, Object> map = new TreeMap<String, Object>();
					map.put("username", (String) object[0]);
					map.put("name", (String) object[1]);
					map.put("email", (String) object[2]);
					map.put("phone", (String) object[3]);
					map.put("cardId", (String) object[4]);
					map.put("lawman_name", (String) object[5]);
					map.put("company_name", (String) object[6]);
					map.put("id", (int) object[7]);
					userMapList.add(map);
				}				
				Map<String,Object> myMapResult= new TreeMap<String,Object>();   
			    System.out.println("记录总数： "+count);
			    myMapResult.put("total", count);
			    myMapResult.put("root",userMapList);			
				return myMapResult;
				
			}
			else{//模糊检索
				String text="'%"+searchField+"%'";
				sqlwhere+=" and ("
						+"user.username like"+text+
						" or u.true_name like"+searchField+
						" or u.email like"+searchField+
						" or u.mobile_num like"+searchField+
						" or d.cardId like"+searchField+
						" or d.lawman_name like"+searchField+
						" or d.company_name like"+searchField
						+")";
				sql=sqlselect+sqlwhere+sql1;
				
			}
		}
		
		System.out.println(sql);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		count =query.list().size();
		query.setFirstResult(Integer.parseInt(start));
		query.setMaxResults(Integer.parseInt(limit));
		List<Object[]> userList = query.list();		
		if(!userList.isEmpty()){
			List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
			for (Object[] object : userList) {
				Map<String, Object> map = new TreeMap<String, Object>();
				map.put("username", (String) object[0]);
				map.put("name", (String) object[1]);
				map.put("email", (String) object[2]);
				map.put("phone", (String) object[3]);
				map.put("cardId", (String) object[4]);
				map.put("lawman_name", (String) object[5]);
				map.put("company_name", (String) object[6]);
				map.put("id", (int) object[7]);
				userMapList.add(map);
			}				
			Map<String,Object> myMapResult= new TreeMap<String,Object>();   
		    System.out.println("记录总数： "+count);
		    myMapResult.put("total", count);
		    myMapResult.put("root",userMapList);			
			return myMapResult;		
	   }
	 return null;
	}
}
