package cn.edu.bnu.land.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.Crawlkeywords;
import cn.edu.bnu.land.model.CrawlkeywordsHome;
import cn.edu.bnu.land.model.Crawlurl;



@Service
public class CrawlkeywordsService {
	private CrawlkeywordsHome crawlkeywordsHome;
	private SessionFactory sessionFactory;
	private Crawlkeywords crawlkeywords;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Autowired
	public void setCrawlkeywordsHome(CrawlkeywordsHome crawlkeywordsHome){
		this.crawlkeywordsHome=crawlkeywordsHome;
	}
	
	public Map<String,Object>  lookkeywords(String start,String limit,String searchField){
		System.out.println("lookkeywords");
		System.out.println("start:"+start);
		System.out.println("limit:"+limit);
		String totalCount =  new String();
		List<Crawlkeywords> results = null;
		Session session =sessionFactory.getCurrentSession(); 
		DetachedCriteria dc = DetachedCriteria.forClass(Crawlkeywords.class); 
		

		
		if (searchField != ""){
			dc.add(Restrictions.ilike("keywordsname", searchField,MatchMode.ANYWHERE));
		}

		Criteria c = dc.getExecutableCriteria(session); 
		
		
		if (!c.list().isEmpty())
			totalCount= String.valueOf(c.list().size());
		c.setFirstResult(Integer.parseInt(start));
		c.setMaxResults(Integer.parseInt(limit));
		
		results = (List<Crawlkeywords>)c.list(); 
//		for (Transinfo i : results) { 
//			System.out.println(i.getNumber()+"  " + i.getText()); 
//		} 
		
		Map<String,Object> myMapResult = new TreeMap<String,Object>();
		System.out.println("查询记录总数："+ totalCount);
		myMapResult.put("total", new String(totalCount));
		myMapResult.put("root", results);
		return myMapResult;	

	}
	
    public void addCrawlkeywords(Crawlkeywords crawlkeywords){	
      	System.out.println("add_crawlkeywords service!");
    	Session session = sessionFactory.getCurrentSession(); 	

     	String keywordsname = crawlkeywords.getKeywordsname();
     	String adddate = crawlkeywords.getAdddate();
     	System.out.println(keywordsname + adddate);
     	crawlkeywords.setKeywordsname(keywordsname);;
     	crawlkeywords.setAdddate(adddate);
     	session.save(crawlkeywords); 	
	}
	
	public void updateCrawlurl(Crawlkeywords crawlkeywords){	
     	Session session = sessionFactory.getCurrentSession();   	 

     	session.update(crawlkeywords); 	
	}	
	
	/**
	 * 根据id删除crawlkeywords表中记录。多条id之间使用','分隔
	 * 
	**/
	public void deleteCrawlkeywords(String ids){
		String[] idsArray=ids.split(",");
		for (int i=0;i<idsArray.length;i++){
			String hql="delete from Crawlkeywords where id="+idsArray[i];
			System.out.println(hql);
			Session session = sessionFactory.getCurrentSession();
			Query q=session.createQuery(hql);
			q.executeUpdate();
		}
	}
	
	

}
