package cn.edu.bnu.land.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.model.FkQualityreport;
import cn.edu.bnu.land.model.PublicProject;
import cn.edu.bnu.land.model.PublicProjectHome;
import cn.edu.bnu.land.model.Syncpush;
import cn.edu.bnu.land.model.SyncpushHome;
import cn.edu.bnu.land.model.User;
import cn.edu.bnu.land.model.UserHome;

import cn.edu.bnu.land.model.Zbckdl;
import cn.edu.bnu.land.model.Zbcr;
import cn.edu.bnu.land.model.ZbcrHome;
import cn.edu.bnu.land.model.Zbcrsh;
import cn.edu.bnu.land.model.ZbcrshHome;
import cn.edu.bnu.land.model.Zbjcgl;
import cn.edu.bnu.land.model.Zbrkdl;


@Service
public class ZbcrService {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Zbrkdl zbrk;
	private Zbjcgl zbjc;
	
	public Map<String, Object> getZbcrSh(String start,String limit) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbcrsh> results = null;
		String totalConut=null;
		String h = "from Zbcrsh s where s.crzt='"+"待审核"+"'";//1表示要显示的，包括未审核和未通过的
		
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
			 totalConut = String.valueOf(query.list().size());
			query.setFirstResult(Integer.parseInt(start));
			query.setMaxResults(Integer.parseInt(limit));
			results = (List<Zbcrsh>) query.list();
			//results.get(0).setArea("gea");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("root", results);
		model.put("total", totalConut);
		return model;
		
	}	
	
	public Map<String, Object> updateShtg(Zbcrsh record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		try
		{
		//Zbcrsh zb=zbcrshhome.findById(record.getId());
		//zb.setCrzt("通过");
			sessionFactory.getCurrentSession().update(record);
			System.out.println("其他："+record.getCrqt());
			if(record.getCrqt().contains("0"))
			{System.out.println("进入");
				zbrk.setUserid(record.getUserid());
				zbrk.setBccrgm(record.getBccrgm());
				zbrk.setCrzbbh(record.getCrzbbh());
				zbrk.setFkxmbh(record.getFkxmbh());
				String dateStr=Tool.getDate("yyyy-MM-dd");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");			
				zbrk.setJrrq(df.parse(dateStr));
				sessionFactory.getCurrentSession().save(zbrk);
				
				zbjc.setCred(record.getBccrgm());
				zbjc.setCrlx("出让");
				zbjc.setCrrq(df.parse(dateStr));
				zbjc.setJcbh(record.getFkxmbh());
				zbjc.setJcfmc(record.getUserid());//userid未获得
				zbjc.setLrlc(false);
				zbjc.setSfjs(false);
				sessionFactory.getCurrentSession().save(zbjc);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.put("msg", "审核通过");
		
		return model;
		
	}	
	
	public Map<String, Object> updateShbtg(Zbcrsh record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		try
		{
			sessionFactory.getCurrentSession().update(record);
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.put("msg", "操作成功");
		
		return model;
		
	}
}
