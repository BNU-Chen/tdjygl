package cn.edu.bnu.land.service;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.Syncpush;

@Service
public class QueryStatisticInfoService {
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Map<String, Object> getSyncDataList(String keyword,String start, String limit) {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String hql = "from Syncpush ";
		if(!(keyword.equals("")||keyword.isEmpty())){
			hql += " where ticketId like '%"+keyword+"%'"
					+" or ticketId like '%"+keyword+"%'"
					+" or Auction like '%"+keyword+"%'"
					+" or supplier like '%"+keyword+"%'"
					+" or others like '%"+keyword+"%'"
					+" or region like '%"+keyword+"%'";
		}
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
	
	public Map<String, Object> statisticAuction() {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String sql = "SELECT Auction, COUNT(*) FROM syncpush GROUP BY Auction ";
		System.out.println(sql);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> statisticList = query.list();

		List<Map<String, Object>> statisticMapList = new ArrayList<Map<String, Object>>();
		for (Object[] object : statisticList) {
			Map<String, Object> map = new TreeMap<String, Object>();
			map.put("name", (String) object[0]);
			map.put("data", (BigInteger) object[1]);
			statisticMapList.add(map);
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("root", statisticMapList);
		myMapResult.put("success", true);
		return myMapResult;

	}

	
	public Map<String, Object> statisticSupplier() {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String sql = "SELECT supplier, COUNT(*) FROM syncpush GROUP BY supplier ";
		System.out.println(sql);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> statisticList = query.list();

		List<Map<String, Object>> statisticMapList = new ArrayList<Map<String, Object>>();
		for (Object[] object : statisticList) {
			Map<String, Object> map = new TreeMap<String, Object>();
			map.put("name", (String) object[0]);
			map.put("data", (BigInteger) object[1]);
			statisticMapList.add(map);
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("root", statisticMapList);
		myMapResult.put("success", true);
		return myMapResult;

	}
	
	public Map<String, Object> statisticPrice() {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String sql = "SELECT STR_TO_DATE(DATE_FORMAT(publishDate + INTERVAL 1 MONTH, '%Y%m'), '%Y%m') AS staName,ROUND(AVG(price),2) AS staData"
				+ " FROM syncpush"
				+ " GROUP BY MONTH(publishDate),YEAR(publishDate)"
				+ " ORDER BY publishDate ASC";
		System.out.println(sql);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> statisticList = query.list();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Map<String, Object>> statisticMapList = new ArrayList<Map<String, Object>>();
		for (Object[] object : statisticList) {
			Map<String, Object> map = new TreeMap<String, Object>();
			Date date = (Date)object[0];
			String reportDate = df.format(date);
			map.put("name", reportDate); 
			map.put("data", (double) object[1]);
			statisticMapList.add(map);
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("root", statisticMapList);
		myMapResult.put("success", true);
		return myMapResult;
	}
	
	public Map<String, Object> statisticArea() {
		// return this.infoArticleHome.selectInfoArticleList(start, limit);
		// System.out.println("time is getInfoArticleList @InfoArticleService ");
		// System.out.println("start"+start);
		// System.out.println("limit"+limit);
		String sql = "SELECT STR_TO_DATE(DATE_FORMAT(publishDate + INTERVAL 1 MONTH, '%Y%m'), '%Y%m') AS staName,ROUND(AVG(AREA),2) AS staData"
				+ " FROM syncpush"
				+ " GROUP BY MONTH(publishDate),YEAR(publishDate)"
				+ " ORDER BY publishDate ASC";
		System.out.println(sql);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		List<Object[]> statisticList = query.list();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Map<String, Object>> statisticMapList = new ArrayList<Map<String, Object>>();
		for (Object[] object : statisticList) {
			Map<String, Object> map = new TreeMap<String, Object>();
			Date date = (Date)object[0];
			String reportDate = df.format(date);
			map.put("name", reportDate); 
			map.put("data", (double) object[1]);
			statisticMapList.add(map);
		}

		Map<String, Object> myMapResult = new TreeMap<String, Object>();
		myMapResult.put("root", statisticMapList);
		myMapResult.put("success", true);
		return myMapResult;
	}

	
}
