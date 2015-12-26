package cn.edu.bnu.land.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.Syncpush;
import cn.edu.bnu.land.model.SyncpushHome;
import cn.edu.bnu.land.model.User;
import cn.edu.bnu.land.model.UserHome;

@Service
public class UserService {
	
	private UserHome userHome;
	private SyncpushHome syncpushHome;
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setUserHome(UserHome userHome){
		this.userHome=userHome;
	}
	
	public void addUser(User user){
		this.userHome.save(user);
	}
	
	@Autowired
	public void setSyncpushHome(SyncpushHome syncpushHome){
		this.syncpushHome=syncpushHome;
	}
	
	public  Map<String,Object> selectSyncPushTb(String start,String limit,String searchField,String dateType,String searchDate){
		return this.syncpushHome.selectTb(start,limit,searchField,dateType,searchDate);
	}
	
	public  Map<String,Object> selectMailPushTb(String start,String limit,String searchField,String dateType,String searchDate){
		return this.syncpushHome.selectMailPushTb(start,limit,searchField,dateType,searchDate);
	}
	
	public Syncpush selectSpById(int id){
		return this.syncpushHome.selectById(id);
	}
	
	public List<User> getAllUser(){
	    return this.userHome.getAll();
	}
	
	public Map<String, Object> getSyncData(String start, String limit) {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String hql = "from Syncpush ";
		System.out.println(hql);
		String totalConut = null;
		List<Syncpush> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			totalConut = String.valueOf(query.list().size());
			System.out.println(totalConut);
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
			results = (List<Syncpush>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("total", totalConut);
		myMapResult.put("root", results);
		// System.out.println("myMapResult "+gridId+" "+myMapResult);

		return myMapResult;

	}
	
	public Map<String,Object> getSelectUsers(String start,String limit,String searchField){
	    return this.userHome.getSelectUsers(start,limit,searchField);
	}
	
	
	/*
	 * 根据id号将Syncpush表中的isMPush字段设为1
	 */
	public void updateMailPushTrue(String ids){
		String[] idsArray=ids.split(",");
		for (int i=0;i<idsArray.length;i++){
			String hql="update Syncpush set isMailPush=1 where id="+idsArray[i];
			Session session = sessionFactory.getCurrentSession();
			Query q=session.createQuery(hql);
			q.executeUpdate();
		}
	}

	/*
	 * 根据id号将Syncpush表中的isPush字段设为1
	 */
	public void updateSyncPushTrue(String ids){
		String[] idsArray=ids.split(",");
		for (int i=0;i<idsArray.length;i++){
			String hql="update Syncpush set isPush=1 where id="+idsArray[i];
			Session session = sessionFactory.getCurrentSession();
			Query q=session.createQuery(hql);
			q.executeUpdate();
		}
	}
	
}
