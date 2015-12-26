package cn.edu.bnu.land.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.PublishDeal;
import cn.edu.bnu.land.model.PublishDealHome;


@Service
public class PubDealInfoService {
	
	private SessionFactory sessionFactory;
    private PublishDeal publishDeal;
    private PublishDealHome publishDealHome;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setPublishDealHome(PublishDealHome publishDealHome){
		this.publishDealHome=publishDealHome;
	}
	
	public void addPublishDeal(PublishDeal publishDeal){	
     	Session session = sessionFactory.getCurrentSession(); 	
     	Calendar ca = Calendar.getInstance();
     	Date now = ca.getTime();
     	System.out.println(now);
     	publishDeal.setDate(now);
     	session.save(publishDeal); 	
	}
	
	public void updatePublishDeal(PublishDeal publishDeal){	
     	Session session = sessionFactory.getCurrentSession(); 	    	
     	session.update(publishDeal); 	
	}	
	
	/*
	 * 先按照目标字段精确检索，若无结果，再进行模糊检索。
	 * 
	 * 参数start分页首记录数，limit分页每页记录数，searchField搜索关键词,searchDate搜索日期
	 *
	 * */
	public  Map<String,Object> selectPublishDealTb(String start,String limit,String searchField,String searchDate){
		String text=searchField;
		String hql="";
		String hql1="from PublishDeal p where 1=1 ";
		String totalConut=new String();
		//搜索关键词不为空
		if(!searchField.equals("") && !searchField.isEmpty())
			{
			String hql2="";
			
			hql2=" and ( p.title='"+text+"'"+
					" or p.content="+text+
					" or p.url="+text+")";
			if(!searchDate.equals("") && !searchDate.isEmpty())//日期搜索不为空
			{		
				 hql2+=" and p.date>='"+searchDate+"'";
			}
			
			hql=hql1+hql2;
		}
		//搜索关键词为空 搜索日期不为空
		else if((searchField.equals("") || searchField.isEmpty())&&!searchDate.equals("") && !searchDate.isEmpty())
		{
			    hql=hql1+" and p.date>='"+searchDate+"'";			
		}
		else
			hql=" from PublishDeal ";	
		
		System.out.println(hql);
	    List<PublishDeal> results=null;
	    try{
		    org.hibernate.Query query=sessionFactory.getCurrentSession().createQuery(hql);
		    
		    if(!query.list().isEmpty())
		    totalConut=String.valueOf(query.list().size());

		    query.setFirstResult(Integer.parseInt(start));
		    query.setMaxResults(Integer.parseInt(limit));
		    
		    results=(List<PublishDeal>)query.list();
		    if (results.isEmpty())
		    {	 
		    	String hql3="";
		        //开始模糊查询
		    	//搜索关键词不为空
		    	if(!searchField.equals("") && !searchField.isEmpty())
		    	{
					text="'%"+text+"%'";	
                    hql3=" and ( p.title like "+text+
							" or p.content like "+text+
							" or p.url like "+text+")";
		    	}
		    	//搜索日期不为空
		    	if(!searchDate.equals("") && !searchDate.isEmpty())
		    	{
						hql3+=" and p.date>='"+searchDate+"'";
		    	}
		    	hql=hql1+hql3;
				System.out.println(hql);				
			    org.hibernate.Query myquery=sessionFactory.getCurrentSession().createQuery(hql);
			    
			    if(!myquery.list().isEmpty())
				    totalConut=String.valueOf(myquery.list().size());
			    
			    myquery.setFirstResult(Integer.parseInt(start));
			    myquery.setMaxResults(Integer.parseInt(limit));
			    
			    results=(List<PublishDeal>)myquery.list();		    
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
	
	/**
	 * 根据id删除publish_deal表中记录。多条id之间使用','分隔
	 * 
	**/
	public void deletePublishDeal(String ids){
		String[] idsArray=ids.split(",");
		for (int i=0;i<idsArray.length;i++){
			String hql="delete PublishDeal s where s.id="+idsArray[i];
			Session session = sessionFactory.getCurrentSession();
			Query q=session.createQuery(hql);
			q.executeUpdate();
		}
	}
	
	
}
