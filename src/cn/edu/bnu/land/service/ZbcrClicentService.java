package cn.edu.bnu.land.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.model.PublicProject;
import cn.edu.bnu.land.model.PublicProjectHome;
import cn.edu.bnu.land.model.Zbcr;
import cn.edu.bnu.land.model.ZbcrHome;
import cn.edu.bnu.land.model.Zbcrsh;
import cn.edu.bnu.land.model.Zbgglb;
import cn.edu.bnu.land.model.Zbgmgl;
import cn.edu.bnu.land.model.Zbjpcjjl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;

@Service
public class ZbcrClicentService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//@Autowired
	private Zbjpcjjl cjjl;	
	//@Autowired
	private Zbjpssjg ssjg;
	//@Autowired
	private Zbpmjj zbjp;
	//@Autowired
	//private PublicProject fkxm;
	
	//@Autowired
	private Zbcr zbcr;
	//@Autowired
	private PublicProject pulicproject;
	//@Autowired
	private ZbcrHome zbcrHome;
	
	public  Map<String, Object> updateFkxmInfo(String fkxmbh) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbcr> result = null;
		String h = "from Zbcr s where s.fkxmbh='"+fkxmbh+"'";
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		result = (List<Zbcr>) query.list();
		
		//zbcr=zbcrHome.findById(fkxmbh);
		if(result.size()==0)
		{System.out.println("为检索到，开始从复垦项目找");
			//int projectId=Integer.parseInt(fkxmbh);
			String hql = "from PublicProject s where s.projectId='"+fkxmbh+"'";
			System.out.println(hql);
			try {
				List<PublicProject> results = null;
				org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
				results = (List<PublicProject>) q.list();
				if(results.size()==0)
				{
					//Map<String, Object> model = new TreeMap<String, Object>();
					model.put("success",false);
					model.put("msg", "该项目不存在");
				}
				else
				{System.out.println(results.get(0).getRqualityFlag());
					if(!results.get(0).getRqualityFlag().contains("1"))
					{
						//Map<String, Object> model = new TreeMap<String, Object>();
						model.put("success",true);
						model.put("msg", "该项目尚未通过审核");
					}
					else
					{System.out.println("通     过");
						zbcr.setFkxmbh(results.get(0).getProjectId());
						zbcr.setFkxmmc(results.get(0).getProjectname());
						String area=results.get(0).getRarea();
						String fkgm=area;
						if(area.contains("公顷"))
						{
						int pos=area.indexOf("公顷");
						 fkgm=area.substring(0, pos);
						}
						
					    zbcr.setKcrgm(Float.parseFloat(fkgm));
					    zbcr.setCrzt("可出让");
					    zbcr.setFkxmwz(results.get(0).getProjectname());
                        zbcr.setFkxmgm(Float.parseFloat(fkgm));	
                       // Map<String, Object> model = new TreeMap<String, Object>();
						model.put("success",true);
						model.put("data", zbcr);
						model.put("msg", "yes");
                        sessionFactory.getCurrentSession().save(zbcr);
                       
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else
		{
			zbcr=result.get(0);
			if(zbcr.getCrzt()=="终结")
			{
				model.put("success",false);
				model.put("msg", "该复垦项目指标已经用完");
			}
			else
			{
				model.put("success",true);
				model.put("data", zbcr);
			}
			
			
		}
		return model;
		
	}	
	
public Map<String,Object> addZbxx(Zbcrsh[] records){
	String userid=Tool.getUserDetail().getUsername();
		int year=Tool.getYear();
		int month=Tool.getMonth();	
		String bh=new DecimalFormat("0000").format(year)+new DecimalFormat("00").format(month);
	    String hql="from Zbcrsh s where s.crzbbh like '%"+bh+"%'";
	    //System.out.println(hql);
	    org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		int num=q.list().size()+1;
		String pcbh=bh+new DecimalFormat("0000").format(num);
		int	n=records.length;
		for(int i=0;i<n;i++)
		{
			records[i].setUserid(userid);//设置用户名，
			records[i].setCrzbbh(pcbh);
			records[i].setQspzwz("upload/sellzm/qszm/"+userid+"/"+records[i].getFkxmbh());
			records[i].setCrzt("待审核");
			records[i].setCrqt(String.valueOf(n));//设为1表示需要显示，未审核和为通过都需要显示
			sessionFactory.getCurrentSession().save(records[i]);
			List<Zbcr> result = null;
			String h = "from Zbcr s where s.fkxmbh='"+records[i].getFkxmbh()+"'";
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
			result = (List<Zbcr>) query.list();
			if(result.get(0).getCrzt()!="终结")
			{
			    Float kcrgm=result.get(0).getKcrgm()-records[i].getBccrgm();
			    result.get(0).setKcrgm(kcrgm);
			    if(kcrgm<=0)
			    {
				    result.get(0).setCrzt("终结");
			    }
			}
			else
			{
				records[i].setCrzt("指标已经用完");
			}
		}
		
		Map<String,Object> mapResult=new TreeMap<String,Object>();
		mapResult.put("success", true);
		mapResult.put("msg", "提交成功");
		return mapResult;
	}

public Map<String, Object> getWdcrzb(String start,String limit) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbcrsh> results = null;
	String totalConut=null;
	String h = "from Zbcrsh s where s.userid='"+userid+"'";//1表示要显示的，包括未审核和未通过的
	
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

public Map<String, Object> getGglb(String start,String limit) {
	Map<String, Object> model = new TreeMap<String, Object>();
	List<Zbgglb> results = null;
	String totalConut=null;
	String h = "from Zbgglb s where s.sfzj=0 and s.dqhj=1";//1表示要显示的，包括未审核和未通过的
	
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		query.setFirstResult(Integer.parseInt(start));
		query.setMaxResults(Integer.parseInt(limit));
		results = (List<Zbgglb>) query.list();
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("root", results);
	model.put("total", totalConut);
	return model;
	
}	

public Map<String, Object> addWdgmsq(Zbgmgl record) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	record.setId(null);
	record.setUserid(userid);
	record.setSgsl(1);
	record.setPczt("尚未提交");
	sessionFactory.getCurrentSession().save(record);
	model.put("success",true );
	//model.put("total", totalConut);
	return model;		
}	

public Map<String, Object> addtjdd(Zbgmgl record) {
	Map<String, Object> model = new TreeMap<String, Object>();
	record.setId(null);
	record.setPczt("尚未提交");
	List<Zbgmgl> result = null;
	String h = "from Zbgmgl s where s.zbpcbh='"+record.getZbpcbh()+"' and s.userid='"+record.getUserid()+"'";
	//System.out.println(h);
	org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
	result = (List<Zbgmgl>) query.list();
	if(result.size()==0)
	{//System.out.println("数据库中没有");
		sessionFactory.getCurrentSession().merge(record);
	}
	else
	{//System.out.println("数据库中有");
		sessionFactory.getCurrentSession().delete(result.get(0));
		sessionFactory.getCurrentSession().merge(record);
	}
	
	model.put("success",true );
	//model.put("total", totalConut);
	return model;		
}	

public Map<String, Object> getWdsgzb(String start,String limit) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		query.setFirstResult(Integer.parseInt(start));
		query.setMaxResults(Integer.parseInt(limit));
		results = (List<Zbgmgl>) query.list();
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("root", results);
	model.put("total", totalConut);
	return model;
	
}	

public Map<String, Object> updateshzt(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'"+" and s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		results = (List<Zbgmgl>) query.list();
		results.get(0).setPczt("待审核");
		results.get(0).setYyzzwz("upload/buyzm/yyzz/"+userid);
		results.get(0).setNspzwz("upload/buyzm/nspz/"+userid);
		results.get(0).setZzdmwz("upload/buyzm/zzdm/"+userid);
		results.get(0).setSfzmwz("upload/buyzm/sfzm/"+userid);
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("success",true);
	//model.put("total", totalConut);
	return model;
	
}	

public Map<String, Object> updatebzjzt(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'"+" and s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		results = (List<Zbgmgl>) query.list();
		results.get(0).setPczt("待审核");
		results.get(0).setBzjwz("upload/buyzm/deposit/"+userid);		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("success",true);
	//model.put("total", totalConut);
	return model;
	
}

public Map<String, Object> getJpxx(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	List<Zbpmjj> results = null;
	String totalConut=null;
	String h = "from Zbpmjj s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		//query.setFirstResult(Integer.parseInt(start));
		//query.setMaxResults(Integer.parseInt(limit));
		results = (List<Zbpmjj>) query.list();
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	Calendar cal=Calendar.getInstance();	
	cal.setTime(results.get(0).getJjksrq());
	int year=cal.get(Calendar.YEAR);
	int month=cal.get(Calendar.MONTH);
	int day=cal.get(Calendar.DAY_OF_MONTH);
	System.out.println(String.valueOf(year)+String.valueOf(month)+String.valueOf(day));		
	
	Calendar now = Calendar.getInstance();
	Calendar kssj=Calendar.getInstance();
	kssj.setTime(results.get(0).getKssj());
	kssj.set(Calendar.YEAR,year);
	kssj.set(Calendar.MONTH,month);
	kssj.set(Calendar.DAY_OF_MONTH,day);	
	System.out.println("开始时间"+kssj.getTime());
	long dif=kssj.getTimeInMillis()-now.getTimeInMillis();
	model.put("dif",dif);
	model.put("data", results.get(0));
	model.put("success",true);
	return model;
	
}	

public Map<String, Object> updateJpcj(Zbjpcjjl record) {
	Map<String, Object> model = new TreeMap<String, Object>();
	Calendar now = Calendar.getInstance();
	String userid=Tool.getUserDetail().getUsername();
	record.setUserid(userid);
	record.setCjsj(now.getTime());
	sessionFactory.getCurrentSession().save(record);
	return model;
}

public Map<String, Object> getCjjl(String zbpcbh,String start,String limit) {
	Map<String, Object> model = new TreeMap<String, Object>();
	List<Zbjpcjjl> results = null;
	String totalConut=null;
	String h = "from Zbjpcjjl s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		query.setFirstResult(Integer.parseInt(start));
		query.setMaxResults(Integer.parseInt(limit));
		results = (List<Zbjpcjjl>) query.list();
		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	model.put("root", results);
	model.put("total",totalConut);
	return model;
	
}	

public Map<String, Object> updateSsjg(Zbjpcjjl record) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbjpssjg> results = null;
	String totalConut=null;
	String h = "from Zbjpssjg s where s.zbpcbh='"+record.getZbpcbh()+"'"+" and s.userid='"+userid+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		
		results = (List<Zbjpssjg>) query.list();
		results.get(0).setCj(record.getCj());;
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	model.put("root", results);
	model.put("total",totalConut);
	return model;
	
}	

public int getSl(Zbjpcjjl record) {	
	List<Zbpmjj> results = null;
	String totalConut=null;
	String h = "from Zbpmjj s where s.zbpcbh='"+record.getZbpcbh()+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	int sl=1;
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());		
		results = (List<Zbpmjj>) query.list();
		sl=results.get(0).getSl();				
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}	
	return sl;
	
}	

public Map<String, Object> updateSfdb(Zbjpcjjl record) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbjpssjg> results = null;
	String totalConut=null;
	String h = "from Zbjpssjg s where s.zbpcbh='"+record.getZbpcbh()+"'"+" order by s.cj desc";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());
		
		results = (List<Zbjpssjg>) query.list();
		int sl=getSl(record);
		System.out.println("批次数量："+sl);
		int n=0;
		System.out.println("申购人数："+results.size());
		for(int i=0;i<results.size();i++)
		{
			int sgsl=results.get(i).getSgsl();
			
			if(n>sl||results.get(i).getCj()==0)
				break;
			else
			{
				n+=sgsl;
				results.get(i).setSfdb(true);
			}
		}
		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	model.put("root", results);
	model.put("total",totalConut);
	return model;
	
}	

public Map<String, Object> getSsjg(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	List<Zbjpssjg> results = null;
	String totalConut=null;
	String h = "from Zbjpssjg s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		 totalConut = String.valueOf(query.list().size());		
		results = (List<Zbjpssjg>) query.list();
		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	model.put("root", results);
	model.put("total",totalConut);
	return model;
	
}	

public Map<String, Object> updateGmwb(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'"+" and s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		results = (List<Zbgmgl>) query.list();
		for(int i=0;i<results.size();i++)
		{
			results.get(i).setDqhj(5);
			results.get(i).setPczt("尚未备案");
		}
		results.get(0).setPczt("待审核");
		results.get(0).setBzjwz("upload/buyzm/deposit/"+userid);		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("success",true);
	//model.put("total", totalConut);
	return model;
	
}

public Map<String, Object> updatesgfkpz(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'"+" and s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		results = (List<Zbgmgl>) query.list();
		for(int i=0;i<results.size();i++)
		{
			results.get(i).setPczt("尚未备案");	
			results.get(i).setPcqt("upload/buyzm/fkpz/"+userid);
		}		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("success",true);
	//model.put("total", totalConut);
	return model;
	
}	

public Map<String, Object> updatefkpzWz(String zbpcbh) {
	Map<String, Object> model = new TreeMap<String, Object>();
	String userid=Tool.getUserDetail().getUsername();
	List<Zbgmgl> results = null;
	String totalConut=null;
	String h = "from Zbgmgl s where s.userid='"+userid+"'"+" and s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
	System.out.println(h);
	try {
		org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
		results = (List<Zbgmgl>) query.list();
		for(int i=0;i<results.size();i++)
		{
		results.get(i).setPczt("尚未备案");
		results.get(i).setPcqt("upload/buyzm/fkpz/"+userid);
		}
		
		//results.get(0).setArea("gea");
	} catch (Exception e) {
		e.printStackTrace();
	}
	model.put("success",true);
	//model.put("total", totalConut);
	return model;
	
}	


}
