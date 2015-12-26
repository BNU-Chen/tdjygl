package cn.edu.bnu.land.service;

import java.io.BufferedWriter;
import java.io.File;
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
import java.util.TimeZone;
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
import cn.edu.bnu.land.model.Zbjpcjjl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;
import cn.edu.bnu.land.model.Zbrkdl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service
public class ZbjjService {
	
	@Autowired
	private SessionFactory sessionFactory;
	//@Autowired
	//private PublicProject fkxm;
	@Autowired
	private Zbjpcjjl cjjl;	
	@Autowired
	private Zbjpssjg ssjg;
	@Autowired
	private Zbpmjj zbjp;
	@Autowired
	private Zbcrsh zbcrsh;
	@Autowired
	private ZbcrshHome zbcrshhome;
	@Autowired
	private ZbgglbHome zbgghome;
	@Autowired
	private ZbcrHome zbcrHome;
	@Autowired
	private Zbrkdl zbrk;
	@Autowired
	private Zbckdl zbck;
	@Autowired
	private Zbjcgl zbjc;
	
	
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
			Zbpmjj record=results.get(0);
			//results.get(0).setArea("gea");
			
			Calendar cal=Calendar.getInstance();
			cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			if(record.getJjksrq()!=null)
			{
				cal.setTime(record.getJjksrq());
			}
			else
			{
				
				record.setJjksrq(cal.getTime());//如果竞价日期为空
			}
			int year=cal.get(Calendar.YEAR);
			int month=cal.get(Calendar.MONTH);
			int day=cal.get(Calendar.DAY_OF_MONTH);
			System.out.println(String.valueOf(year)+String.valueOf(month)+String.valueOf(day));
			if(record.getKssj()!=null)
			{
				cal.setTime(record.getKssj());	
			}
			else
			{
				cal.setTime(cal.getTime());//如果竞价开始时间为空
			}
				
			//int minute=cal.get(Calendar.MINUTE);
			//int hour=cal.get(Calendar.HOUR_OF_DAY);
			//int second=cal.get(Calendar.SECOND);
			//cal.set(Calendar.HOUR_OF_DAY,hour-8);	
			//cal.set(Calendar.YEAR,year);
			//cal.set(Calendar.MONTH,month);
			System.out.println("修改前日期："+day);
			System.out.println("修改前日期："+cal.get(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.DAY_OF_MONTH,day);	
			day=cal.get(Calendar.DAY_OF_MONTH);
			System.out.println("修改后日期："+day);
			//cal.set(Calendar.MINUTE,minute);
			//cal.set(Calendar.SECOND,second);
			record.setKssj(cal.getTime());
			System.out.println("存储日期："+record.getKssj());
			//cal.setTimeInMillis(4);
		    
			Calendar now = Calendar.getInstance();
			
			System.out.println("开始时间："+record.getKssj().getTime());
			System.out.println("当前时间："+now.getTimeInMillis());
			long dif=cal.getTimeInMillis()-now.getTimeInMillis();
			model.put("dif", dif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("data", results.get(0));
		model.put("success",true);
		//model.put("dif", dif);
		return model;
		
	}	
	
	public Map<String, Object> updateJpxx(Zbpmjj record) throws ParseException {
		Map<String, Object> model = new TreeMap<String, Object>();
		//record.setDqhj(3);
		record.setPczt("尚未开始");
		sessionFactory.getCurrentSession().update(record);
		record.setKssj(Tool.getGMT(record.getKssj()));		
		Calendar cal=Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		cal.setTime(record.getJjksrq());
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH);
		int day=cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(String.valueOf(year)+String.valueOf(month)+String.valueOf(day));
		cal.setTime(record.getKssj());		
		int minute=cal.get(Calendar.MINUTE);
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int second=cal.get(Calendar.SECOND);
		cal.set(Calendar.HOUR_OF_DAY,hour-8);	
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH,month);
		System.out.println("修改前日期："+day);
		System.out.println("修改前日期："+cal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.DAY_OF_MONTH,day);	
		day=cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("修改后日期："+day);
		//cal.set(Calendar.MINUTE,minute);
		//cal.set(Calendar.SECOND,second);
		record.setKssj(cal.getTime());
		System.out.println("存储日期："+record.getKssj());
		//cal.setTimeInMillis(4);
	    
		Calendar now = Calendar.getInstance();
		
		System.out.println("开始时间："+record.getKssj().getTime());
		System.out.println("当前时间："+now.getTimeInMillis());
		long dif=cal.getTimeInMillis()-now.getTimeInMillis();
		System.out.println("dif:"+dif);
		
		model.put("success", true);
		model.put("dif", dif);
		//model.put("total", totalConut);
		return model;	
	}
	
	public void updateGmzt(String zbpcbh)
	{
		List<Zbgmgl> result = null;
		String hql = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgmgl>)q.list();
		int n=result.size();
		for(int i=0;i<n;i++)
		{
			result.get(i).setDqhj(3);
			result.get(i).setPczt("尚未开始");
		}
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
	
	public String updateHt(Zbgmgl record) {	    		
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
	        t = configuration.getTemplate("ht.ftl");
	        t.setEncoding("utf-8");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	     String dirPath="pz/ht/"+ht.getZbpcbh()+"/"; 
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
	
	public void updateGmWq(String zbpcbh,String userid,String htwz)
	{
		List<Zbgmgl> result = null;
		String hql = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"' and s.userid='"+userid+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgmgl>)q.list();
		int n=result.size();
		for(int i=0;i<n;i++)
		{
			result.get(i).setDqhj(4);
			result.get(i).setPczt("未签");
			result.get(i).setHtwz(htwz);
		}
	}
	public int judgeWq(String zbpcbh)
	{
		List<Zbgmgl> result = null;
		String hql = "from Zbgmgl s where s.zbpcbh='"+zbpcbh+"' and s.pczt='中标'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgmgl>)q.list();
		int n=result.size();
		return n;
	}
	
	public void updateGgwq(String zbpcbh)
	{
		List<Zbgglb> result = null;
		String hql = "from Zbgglb s where s.zbpcbh='"+zbpcbh+"'";
		org.hibernate.Query q = sessionFactory.getCurrentSession().createQuery(hql);
		result=(List<Zbgglb>)q.list();
		int n=result.size();
		for(int i=0;i<n;i++)
		{
			result.get(i).setDqhj(5);
			
		}
	}
	
	
}
