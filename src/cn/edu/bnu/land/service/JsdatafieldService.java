package cn.edu.bnu.land.service;


import java.util.List;

import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.edu.bnu.land.model.Jsdatafield;
import cn.edu.bnu.land.model.JsdatafieldHome;



@Service
public class JsdatafieldService {
	private JsdatafieldHome jsdatafieldHome;
	private SessionFactory sessionFactory;
	private Jsdatafield jsdatafield;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Autowired
	public void setJsdatafieldHome(JsdatafieldHome jsdatafieldHome){
		this.jsdatafieldHome = jsdatafieldHome;
	}
	
	public Map<String,Object>  lookdataField(String start,String limit){
		System.out.println("look data field");
		System.out.println("start:"+start);
		System.out.println("limit:"+limit);
		String totalCount =  new String();
		String hql="from Jsdatafield as jsdatafield";
		List<Jsdatafield> results = null;
	    try{
	    	org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
			if(!query.list().isEmpty())
				totalCount=String.valueOf(query.list().size());//获取此次搜索结果的总记录数
			query.setFirstResult(Integer.parseInt(start));//设置所有结果的首记录位置
			query.setMaxResults(Integer.parseInt(limit));//设置所有结果的每页显示的记录数
	    	results=query.list();
//	    	for(Jsfieldmapping jsfieldmapping:results){
//	    		System.out.println(jsfieldmapping.getId()+ jsfieldmapping.getFieldmapping());
//	    	}	    
	    	
	    	
			results = (List<Jsdatafield>)query.list(); 
			for (Jsdatafield i : results) { 
				System.out.println(i.getId()+"  " + i.getWebdataField()); 
			} 
	    }
		catch(Exception e){
			e.printStackTrace();
		}	
				

		
		Map<String,Object> myMapResult = new TreeMap<String,Object>();
		System.out.println("字段映射表查询记录总数："+ totalCount);
		myMapResult.put("total", new String(totalCount));
		myMapResult.put("root", results);
		return myMapResult;	

	}
	
	   public void addwebdata(Jsdatafield jsdatafield){	
	      	System.out.println("add_Jsdatafield service!");
	    	Session session = sessionFactory.getCurrentSession(); 	

	     	String webdataField = jsdatafield.getWebdataField();
	     	
	     	System.out.println("webdatafield:" + webdataField);
	     	jsdatafield.setWebdataField(webdataField);
	     	
	     	session.save(jsdatafield); 	
		}
	   
		/**
		 * 根据id删除crawlkeywords表中记录。多条id之间使用','分隔
		 * 
		**/
		public void deleteJsdatafield(String ids){
			String[] idsArray=ids.split(",");
			for (int i=0;i<idsArray.length;i++){
				String hql="delete from Jsdatafield where id="+idsArray[i];
				System.out.println(hql);
				Session session = sessionFactory.getCurrentSession();
				Query q=session.createQuery(hql);
				q.executeUpdate();
			}
		}
}
