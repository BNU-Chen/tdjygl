package cn.edu.bnu.land.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import cn.edu.bnu.land.model.Zbgglb;
import cn.edu.bnu.land.model.ZbgglbHome;
import cn.edu.bnu.land.model.Zbgmgl;
import cn.edu.bnu.land.model.Zbht;
import cn.edu.bnu.land.model.Zbjcgl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;
import cn.edu.bnu.land.model.Zbrkdl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service
public class ZbjyglService {
	
	@Autowired
	private SessionFactory sessionFactory;

//	@Autowired
//	private Zbcr zbcr;
//	@Autowired
//	private PublicProject publicProject;
//	@Autowired
//	private Zbcrsh zbcrsh;
//	@Autowired
//	private ZbcrshHome zbcrshhome;
//	@Autowired
//	private ZbgglbHome zbgghome;
//	@Autowired
//	private ZbcrHome zbcrHome;
//	@Autowired
//	private Zbrkdl zbrk;
//	@Autowired
	private Zbckdl zbck=new Zbckdl();
//	@Autowired
	private Zbjcgl zbjc=new Zbjcgl();
//	@Autowired
	private Zbpmjj zbjp=new Zbpmjj();
//	@Autowired
	private Zbjpssjg ssjg=new Zbjpssjg();
	
	public Map<String, Object> addZbgglb(Zbgglb record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		try
		{
			int year=Tool.getYear();
			int month=Tool.getMonth();	
			String lx=null;
			if(record.getJylx().contains("竞拍"))
			{
			   lx="JP";
			}
			else
			{
				lx="GP";
			}
			String bh=new DecimalFormat("0000").format(year)+new DecimalFormat("00").format(month)+lx;
		    String hql="from Zbjcgl s where s.jcbh like '%"+bh+"%'";
		    //System.out.println(hql);
		    org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
			int num=q.list().size()+1;
			String pcbh=bh+new DecimalFormat("00").format(num);
			String dateStr=Tool.getDate("yyyy-MM-dd");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");			
			zbjc.setCrrq(df.parse(dateStr));
			zbjc.setCred(record.getDwed()*record.getSl());
			zbjc.setCrlx("发布");
			zbjc.setJcbh(bh+new DecimalFormat("00").format(num));
			zbjc.setJcfmc("交易中心");
			zbjc.setLrlc(true);
			zbjc.setSfjs(false);
			sessionFactory.getCurrentSession().save(zbjc);
			
			zbck.setBcfbed(record.getDwed()*record.getSl());
			zbck.setFbrq(df.parse(dateStr));
			zbck.setZbpcbh(bh+new DecimalFormat("00").format(num));
			sessionFactory.getCurrentSession().save(zbck);
			
			record.setZbpcbh(bh+new DecimalFormat("00").format(num));
			record.setPcqt("wd");
			sessionFactory.getCurrentSession().merge(record);
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.put("success",true );
		model.put("zbpcbh",zbck.getZbpcbh());
		//model.put("total", totalConut);
		return model;		
	}	
	
	
	public Map<String, Object> getGglb(String start,String limit) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbgglb> results = null;
		String totalConut=null;
		String h = "from Zbgglb s where s.sfzj=0";//1表示要显示的，包括未审核和未通过的
		
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
	public Map<String, Object> getFbbh()
	{
		Map<String, Object> model = new TreeMap<String, Object>();
		int year=Tool.getYear();
		int month=Tool.getMonth();	
		String bh=new DecimalFormat("0000").format(year)+new DecimalFormat("00").format(month);
	    String hql="from Zbjcgl s where s.jcbh like '%"+bh+"%'";
	    //System.out.println(hql);
	    org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		int num=q.list().size()+1;
		String pcbh=bh+new DecimalFormat("0000").format(num);
		return model;
	
	}
	
	public Map<String, Object> getgmlb(String start,String limit) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbgmgl> results = null;
		String totalConut=null;
		String h = "from Zbgmgl s ";//1表示要显示的，包括未审核和未通过的
		
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
	
	public Map<String, Object> getbzjsh(String zbpcbh,String start,String limit) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbgmgl> results = null;
		String totalConut=null;
		String h = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"'"+"and s.dqhj=2";//1表示要显示的，包括未审核和未通过的
		
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
	
	public Map<String, Object> updateSgtg(Zbgmgl record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		//record.setDqhj(2);
		record.setDqhj(2);
		record.setPczt("尚未提交");
		sessionFactory.getCurrentSession().update(record);
		model.put("success", true);
		//model.put("total", totalConut);
		return model;	
	}
	
	public Map<String, Object> updateSgbtg(Zbgmgl record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		
		record.setPczt("未通过");
		sessionFactory.getCurrentSession().update(record);
		model.put("success", true);
		//model.put("total", totalConut);
		return model;	
	}
	
	private int getSgzsl(Zbgmgl record)
	{
		List<Zbgmgl> gmresults = null;
		String l = "from Zbgmgl s where s.zbpcbh='"+record.getZbpcbh()+"'"+"and s.pczt='通过'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(l);
		int sgzsl=0;
		gmresults=(List<Zbgmgl>)q.list();
		System.out.println(gmresults.size());
		for(int i=0;i<gmresults.size();i++)
		{
			sgzsl+=gmresults.get(i).getSgsl();
		}
		return sgzsl;
		//gmresults = (List<Zbgmgl>) q.list();
	}
	
	private void saveJp(Zbgmgl record)
	{
		List<Zbgglb> ggresult = null;
		String hql = "from Zbgglb s where s.zbpcbh='"+record.getZbpcbh()+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		ggresult=(List<Zbgglb>)q.list();
		zbjp.setZbpcbh(ggresult.get(0).getZbpcbh());
		
		zbjp.setDj(ggresult.get(0).getDj());
		zbjp.setDwed(ggresult.get(0).getDwed());
		zbjp.setJjksrq(ggresult.get(0).getJjksrq());
		
		zbjp.setSgzsl(getSgzsl(record));
		zbjp.setSl(ggresult.get(0).getSl());
		zbjp.setPczt("尚未开始");
		sessionFactory.getCurrentSession().save(zbjp);
	}
	public Map<String, Object> updateBzjtg(Zbgmgl record) throws ParseException {
		Map<String, Object> model = new TreeMap<String, Object>();
		//record.setDqhj(3);
		System.out.println("保证金审核通过");
		record.setPczt("通过");
		sessionFactory.getCurrentSession().update(record);
		String dateStr=Tool.getDate("yyyy-MM-dd hh:mm");
	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date currentdate=df.parse(dateStr);
		//record.setSqjzrq();
		Date sqjzrq=new Date();
		if(record.getSqjzrq()==null)
		{
			sqjzrq=df.parse("2020-01-01");
		}
		else
		{
			sqjzrq=record.getSqjzrq();
		}
		if(currentdate.after(sqjzrq))
		{System.out.println("进入if");
			List<Zbgmgl> results = null;
			
			String totalConut=null;
			String h = "from Zbgmgl s where s.zbpcbh='"+record.getZbpcbh()+"'"+"and s.pczt='待审核'";//1表示要显示的，包括未审核和未通过的
			
			try {
				org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
				
				 totalConut = String.valueOf(query.list().size());
				//query.setFirstResult(Integer.parseInt(start));
				//query.setMaxResults(Integer.parseInt(limit));
				results = (List<Zbgmgl>) query.list();
				
				//results.get(0).setArea("gea");
			} catch (Exception e) {
				e.printStackTrace();
			}			
			if(results.size()==0)
			{System.out.println("全部审核完毕");
				List<Zbgglb> ggresult = null;
				String hql = "from Zbgglb s where s.zbpcbh='"+record.getZbpcbh()+"'";
				org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
				ggresult=(List<Zbgglb>) q.list();
				ggresult.get(0).setDqhj(3);
				ggresult.get(0).setPczt("尚未开始");
				saveJp(record);
				//for(int i=0;i<gmresults.size();i++)
				//{
					//gmresults.get(i).setDqhj(3);
					//gmresults.get(i).setPczt("尚未开始");
				//}
			}
		}
		
		model.put("success", true);
		//model.put("total", totalConut);
		return model;	
	}
	
	public Map<String, Object> addToJg(Zbgmgl record) throws ParseException {
		Map<String, Object> model = new TreeMap<String, Object>();
		ssjg.setZbpcbh(record.getZbpcbh());
		ssjg.setSgsl(record.getSgsl());		
		ssjg.setUserid(record.getUserid());
		ssjg.setSfdb(false);
		sessionFactory.getCurrentSession().save(ssjg);
		model.put("success", true);
		//model.put("total", totalConut);
		return model;	
	}
	
	public Map<String, Object> updateBzjbtg(Zbgmgl record) {
		Map<String, Object> model = new TreeMap<String, Object>();
		
		record.setPczt("未通过");
		sessionFactory.getCurrentSession().update(record);
		model.put("success", true);
		//model.put("total", totalConut);
		return model;	
	}
	
	public Map<String, Object> updateJpResult(String zbpcbh) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbjpssjg> results = null;
		String totalConut=null;
		String h = "from Zbjpssjg s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
		System.out.println(h);
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);
			 totalConut = String.valueOf(query.list().size());		
			results = (List<Zbjpssjg>) query.list();
			for(int i=0;i<results.size();i++)
			{
				if(results.get(i).getSfdb())
				updateSgJpResult(zbpcbh,results.get(i).getUserid(),"中标",results.get(i).getCj());
				else
				{
					updateSgJpResult(zbpcbh,results.get(i).getUserid(),"未中标",results.get(i).getCj());
				}
			}
			
			//results.get(0).setArea("gea");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	
		return model;
		
	}	
	
	public void updateSgJpResult(String zbpcbh,String userid,String sfdb,Float zbdj)
	{
		List<Zbgmgl> result = null;
		String hql = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"'"+" and s.userid='"+userid+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgmgl>)q.list();
		for(int i=0;i<result.size();i++)
		{
			result.get(i).setPczt(sfdb);
			result.get(i).setZbdj(zbdj);
			result.get(i).setYfkze(zbdj*result.get(i).getSgsl());
		}
	}
	
	public Map<String, Object> updateWb(String zbpcbh) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbgglb> results = null;
		String totalConut=null;
		String h = "from Zbgglb s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
		
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);			
			results = (List<Zbgglb>) query.list();
			for(int i=0;i<results.size();i++)
			{
				results.get(i).setPczt("完成备案");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
		
	}	
	
	public Map<String, Object> updateWbToWb(String zbpcbh) {
		Map<String, Object> model = new TreeMap<String, Object>();
		List<Zbgmgl> results = null;
		String totalConut=null;
		String h = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"'";//1表示要显示的，包括未审核和未通过的
		
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(h);			
			results = (List<Zbgmgl>) query.list();
			for(int i=0;i<results.size();i++)
			{
				results.get(i).setDqhj(5);
				results.get(i).setPczt("尚未备案");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return model;
		
	}	
	
	public String updateDp(Zbgmgl record) {	    		
		Zbht ht=new Zbht();		
		ht.setZbpcbh(record.getZbpcbh());
		ht.setZbmj(record.getSgsl()*record.getDwed());
		ht.setSgsl(record.getSgsl());
		ht.setCjzj(record.getYfkze());
		if(ht.getZbmj()!=0)
		{
		ht.setCjdj(ht.getCjzj()/ht.getZbmj());
		}
		else
		{
			ht.setCjdj(ht.getCjzj()/1);
		}
		ht.setDwmc("华科电子有限公司");
		Calendar now=Calendar.getInstance();
	    ht.setCjyear(now.get(Calendar.YEAR));
		ht.setCjmonth(now.get(Calendar.MONTH)+1);
		ht.setCjday(now.get(Calendar.DAY_OF_MONTH));
		String djdx=ht.getCjdj().toString().toUpperCase();
		String mjdx=ht.getZbmj().toString().toUpperCase();
		String zjdx=ht.getCjzj().toString().toLowerCase();
		ht.setMjdx(mjdx);
		ht.setDjdx(djdx);
		ht.setZjdx(zjdx);
		Configuration   configuration = new Configuration();
	    configuration.setEncoding(Locale.CHINA, "utf-8");  
	    configuration.setClassForTemplateLoading(this.getClass(), "/cn/edu/bnu/land/common");
	    Template t = null;
	    String userid=Tool.getUserDetail().getUsername();
	    try {
	        // freemarker2html.ftl为要装载的html模板
	        t = configuration.getTemplate("dp.ftl");
	        t.setEncoding("utf-8");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	     String dirPath="pz/dp/"+ht.getZbpcbh()+"/"; 
	    String m_fileName = userid+".html";	    //	   
	    String m_fileName_download = userid+".doc";	
	    //Tool.createFile(realPath, m_fileName);
	   // File outFile = new File(filePath);
	    Writer out = null;
	    Writer out_download = null;
	    try {
	        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Tool.createFile(dirPath, m_fileName)),"UTF-8"));
	        out_download = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Tool.createFile(dirPath, m_fileName_download)),"UTF-8"));
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }

	    try {
	        t.process(ht, out);
	        out.close();
	        t.process(ht, out_download);
	        out_download.close();
	    } catch (TemplateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	    return dirPath+m_fileName;
	}
	
	public void updateGmWb(String zbpcbh,String userid,String dpwz)
	{
		List<Zbgmgl> result = null;
		String hql = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"' and s.userid='"+userid+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgmgl>)q.list();
		int n=result.size();
		for(int i=0;i<n;i++)
		{
			result.get(i).setDqhj(5);
			result.get(i).setPczt("完成备案");
			result.get(i).setPzwz(dpwz);
		}
	}
}
