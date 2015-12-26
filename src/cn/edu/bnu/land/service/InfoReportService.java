package cn.edu.bnu.land.service;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.model.InfoReport;
import cn.edu.bnu.land.model.InfoReportHome;


@Service
public class InfoReportService{
	private InfoReportHome infoReportHome;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Autowired
	public void setInfoReportHome(InfoReportHome infoReportHome){
		this.infoReportHome = infoReportHome;
		
	}
	public void addReport(InfoReport infoReport) {
		System.out.println("now is in InfoReportService");
		Session session = sessionFactory.getCurrentSession();
		session.save(infoReport);
		
	}
	public Map<String, Object> getInfoReportList(String start, String limit) {
		String hql = "from InfoReport as infoReport";
		System.out.println(hql);
		String totalConut = null;
		List<InfoReport> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession()
					.createQuery(hql);
			totalConut = String.valueOf(query.list().size());
			System.out.println(totalConut);
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
			results = (List<InfoReport>) query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("total", totalConut);
		myMapResult.put("root", results);
		// System.out.println("myMapResult "+gridId+" "+myMapResult);

		return myMapResult;
	}
	
	
	
	
}