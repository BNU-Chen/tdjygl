package cn.edu.bnu.land.service;

import java.sql.SQLException;
import java.util.List;




import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





import cn.edu.bnu.land.model.Crawlkeywords;
import cn.edu.bnu.land.model.Transinfo;
import cn.edu.bnu.land.model.TransinfoHome;

@Service
public class Transinfo_S_Service {
	private TransinfoHome transinfoHome;
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Autowired
	public void setTransinfoHome(TransinfoHome transinfoHome){
		this.transinfoHome=transinfoHome;
	}
	
	public  Map<String,Object>  findtransinfo_S(String start,String limit,String outSearch) throws ClassNotFoundException, SQLException{
		System.out.println("findtransinfo_S");
		System.out.println("start:"+start);
		System.out.println("limit:"+limit);
		String totalCount =  new String();
		List<Crawlkeywords> results = null;
		Session session =sessionFactory.getCurrentSession(); 
		DetachedCriteria dc = DetachedCriteria.forClass(Transinfo.class); 
		

		
		if (outSearch != ""){
			dc.add(Restrictions.ilike("title", outSearch,MatchMode.ANYWHERE));
			dc.add(Restrictions.ilike("region", outSearch,MatchMode.ANYWHERE));
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
	


}
