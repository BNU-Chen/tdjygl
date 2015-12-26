package cn.edu.bnu.land.service;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.common.ConfigReader;
import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.model.InfoArticleHome;
import cn.edu.bnu.land.model.InfoChannel;
import cn.edu.bnu.land.model.RssAreaCode;
import cn.edu.bnu.land.model.RssAreaCodeHome;
import cn.edu.bnu.land.model.RssUserDevice;
import cn.edu.bnu.land.model.RssUserDeviceHome;
import cn.edu.bnu.land.model.Syncpush;

@Service
public class AndroidServic {
   private RssAreaCode rssAreaCode;
   private RssUserDevice rssUserDevice;
   private SessionFactory sessionFactory;
   private RssUserDeviceHome rssUserDeviceHome;
   
   //为了获取Article数据，因此在Service中调用Service
   private InfoArticleService infoArticleService;
   
   @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
                                                                                                                                                                                                                                                                                                                                        
   @Autowired
	public void setRssUserDeviceHome(RssUserDeviceHome rssUserDevice) {
		this.rssUserDeviceHome = rssUserDeviceHome;

	}
   
//   public Boolean confirmDeviceUser(String username,String password){
//	   Boolean result=false;
//	   String hql = "from RssUserDevice as r where r.username='"+username+"' and r.password='"+password+"'";
//	   System.out.println(hql);
//	   try {
//			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
//			if( query.list()!=null && query.list().size()!=0)
//				{				  		
//				  result=true;
//				}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	   return result;
//   }
//   
   
   public Boolean confirmDeviceUser(String username,String password){
	   Boolean result=false;
	   String hql = "from uUserInfo as r where r.userName='"+username+"' and r.userPwd='"+password+"'";
	   System.out.println(hql);
	   try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			if( query.list()!=null && query.list().size()!=0)
				{				  		
				  result=true;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   return result;
   }
   
   /***
    * Android用户，点击设置页面的“保存”按钮时调用
    * 生成针对该用户的个性化RSSFeed
    * 参数分别代表:用户名，订阅的行政区代码，订阅的类别
   */
   public void CreatRSSFeedBySettings(String username,String area,String category)
   {
	   //此处添加 根据行政区名查询行政区代码
	   String areacode=area;
	  	   
	   //一个生成RSS XML文件的程序
	    Element root = new Element("rss");
	    root.setAttribute(new Attribute("version", "2.0"));
	    Document doc = new Document(root);   //将根元素植入
	    Element m_channel = new Element("channel");
	    root.addContent(m_channel);
	    Element Ntitle = new Element("title");
	    
	    String m_chanelTitile="您订阅的农地流转信息";//频道标题
	    String m_fileName=username;//以用户名作为xml文件名
	    
	    Ntitle.addContent(m_chanelTitile);
	    m_channel.addContent(Ntitle);		           
	    Element Nlink = new Element("link");
	    Nlink.addContent(ConfigReader.getWholeurl_webSite());
	    m_channel.addContent(Nlink);
	   
	    Element Ndescription = new Element("description");
	    Ndescription.addContent("重庆市农地转出信息订阅");
	    m_channel.addContent(Ndescription);
	    
	    Element Nlanguage = new Element("language");
	    Nlanguage.addContent("zh-cn");
	    m_channel.addContent(Nlanguage);
	   
	    Element Ncopyright = new Element("copyright");
	    Ncopyright.addContent("Copyright 2013 重庆市农地流转信息网. All Rights Reserved.");
	    m_channel.addContent(Ncopyright);
	   
	    Element NpubDate = new Element("pubDate");
	    java.util.Date now=new java.util.Date();
	    NpubDate.addContent(FormatRssDate(now));
	    m_channel.addContent(NpubDate);
	    
	    Element NlastBuildDate = new Element("lastBuildDate");
	    NlastBuildDate.addContent(FormatRssDate(now));
	    m_channel.addContent(NlastBuildDate);
	    
	    Element Ngenerator = new Element("generator");
	    Ngenerator.addContent("JNULRSS 1.0(beta)");
	    m_channel.addContent(Ngenerator);
	    
	    //订阅类别不为空
	    if( !category.equals("") && !category.isEmpty() )    	
	    {
	    	String[] myChannels=category.split(",");
	    	for (int k=0;k<myChannels.length;k++){
		    		List<InfoArticle> resultsk=infoArticleService.getArticleListAllForHome(Integer.parseInt(myChannels[k]));	
		    	    for(int m=0;m<resultsk.size();m++)
		    	    {   String strContentm="";
			    		//订阅行政区不为空,再按行政区代码筛选。
			    		if(!areacode .equals("") && !areacode.isEmpty())
			    		{
			    			//！！！！待完成 这里应改成从数据库读取的每条记录的areacode
			    			String areacodem="";
			    			//选出处于相同及下级行政区的记录			   
			    			if(areacodem.length() >= areacode.length() && areacodem.startsWith(areacode) )
			    			{
			    				Element news = new Element("item");//生成元素      　　　　　　　　　
			    				news.addContent(new Element("title").addContent(resultsk.get(m).getArticleName()));
			    				news.addContent(new Element("link").addContent(ConfigReader.getWholeurl_webSite()+"/ArticleDetailForAndriod.jsp?articleId="+resultsk.get(m).getArticleId().toString()+"&channelId="+myChannels[k]));		
			    				news.addContent(new Element("pubdate").addContent(FormatRssDate(resultsk.get(m).getArticlePublishtime())));
			    				String temp=resultsk.get(m).getArticleContent();	 
			    				if(temp !=null && temp.length()>400)
			    					strContentm=temp.substring(0,400)+"... ...    请点击查看全文....";
			    				else
			    					strContentm=	temp;
			    				news.addContent(new Element("description").addContent(new CDATA(strContentm)));
			    				m_channel.addContent(news);
			    				strContentm="";
			    			}	
			    		}
			    		//订阅行政区为空,加上全部数据
			    		else
			    		{
			    			Element news = new Element("item");//生成元素      　　　　　　　　　
		    				news.addContent(new Element("title").addContent(resultsk.get(m).getArticleName()));
		    				news.addContent(new Element("link").addContent(ConfigReader.getWholeurl_webSite()+"/ArticleDetailForAndriod.jsp?articleId="+resultsk.get(m).getArticleId().toString()+"&channelId="+myChannels[k]));		
		    				news.addContent(new Element("pubdate").addContent(FormatRssDate(resultsk.get(m).getArticlePublishtime())));
		    				String temp=resultsk.get(m).getArticleContent();	 
		    				if(temp !=null && temp.length()>400)
		    					strContentm=temp.substring(0,400)+"... ...    请点击查看全文....";
		    				else
		    					strContentm=	temp;
		    				news.addContent(new Element("description").addContent(new CDATA(strContentm)));
		    				m_channel.addContent(news);
		    				strContentm="";
			    		}
		    	    }
	    	    }
	    }
	    //订阅类别为空，则选择全部类别
	    else{
		    for(int i=1;i<7;i++)
			{
				 List<InfoArticle> results=infoArticleService.getArticleListAllForHome(i);	
				 String strContent="";
				 		 
			 for(int j=0;j<results.size();j++)
				{
				//订阅行政区不为空,再按行政区代码筛选。
	    		if(!areacode .equals("") && !areacode.isEmpty())
	    		{
	    			//！！！！待完成 这里应改成从数据库读取的每条记录的areacode
	    			String areacoden="";
	    			//选出处于相同及下级行政区的记录			   
	    			if(areacoden.length() >= areacode.length() && areacoden.startsWith(areacode) )
	    			{
	    				
	    				Element news = new Element("item");//生成元素      　　　　　　　　　
						news.addContent(new Element("title").addContent(results.get(j).getArticleName()));
						news.addContent(new Element("link").addContent(ConfigReader.getWholeurl_webSite()+"/ArticleDetailForAndriod.jsp?articleId="+results.get(j).getArticleId().toString()+"&channelId="+i));		
						news.addContent(new Element("pubdate").addContent(FormatRssDate(results.get(j).getArticlePublishtime())));
						String temp=results.get(j).getArticleContent();	 
						if(temp !=null && temp.length()>400)
						strContent=temp.substring(0,400)+"... ...    请点击查看全文....";
						else
						strContent=	temp;
						news.addContent(new Element("description").addContent(new CDATA(strContent)));
						m_channel.addContent(news);
						strContent="";
	    			}
	    		}
	    		//行政区订阅为空 则加入全部
	    		else{
	    			Element news = new Element("item");//生成元素      　　　　　　　　　
					news.addContent(new Element("title").addContent(results.get(j).getArticleName()));
					news.addContent(new Element("link").addContent(ConfigReader.getWholeurl_webSite()+"/ArticleDetailForAndriod.jsp?articleId="+results.get(j).getArticleId().toString()+"&channelId="+i));		
					news.addContent(new Element("pubdate").addContent(FormatRssDate(results.get(j).getArticlePublishtime())));
					String temp=results.get(j).getArticleContent();	 
					if(temp !=null && temp.length()>400)
					strContent=temp.substring(0,400)+"... ...    请点击查看全文....";
					else
					strContent=	temp;
					news.addContent(new Element("description").addContent(new CDATA(strContent)));
					m_channel.addContent(news);
					strContent="";
	    		}
			  }						    
			}
	    }
	    //输出
	    try{
	        XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
	        String realPath=this.getClass().getClassLoader().getResource("/").getPath();
	        int pos=realPath.indexOf("/WEB-INF");
	        System.out.println(pos);
	        realPath=realPath.substring(0, pos);
	        realPath=realPath+"/"+m_fileName+".xml";   
	        XMLOut.output(doc, new FileOutputStream(realPath));    
	        System.out.println("creat ["+m_fileName+"] RSS XML successful！");
	    }catch (java.io.IOException e) {
	        e.printStackTrace();
	    }
		 
   }
   
	
	public static String FormatRssDate(Date dt) {
       SimpleDateFormat RssFmtDt=new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss z");
       return RssFmtDt.format(dt).toString();
   }
	
	//根据行政区区名 获取行政区代码
	public  String getAreaCodebyName(String areaName){
		String areaCode="";		
		String hql = "from RssAreaCode as r where r.areaName='"+areaName+"'";
		   System.out.println(hql);
		   try {
				org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
				if( query.list()!=null && query.list().size()!=0)
					{				  		
					List<RssAreaCode> results=query.list();
					areaCode=results.get(0).getAreaName();
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		   return areaCode;
	}
	
	//根据行政区代码获取行政区名
	public  String getAreaNameByCode (String areaCode){
		String areaName="";	
		if (areaCode.isEmpty() || areaCode.equals(""))
			return areaName;	
		String hql = "from RssAreaCode as r where r.areaCode='"+areaCode+"'";
		//
		   System.out.println(hql);
		   try {
				org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
				if( query.list()!=null && query.list().size()!=0)
					{				  		
					List<RssAreaCode> results=query.list();
					areaName=results.get(0).getAreaCode();
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		   return areaName;
	}
    //获取省级行政区列表
	public List<RssAreaCode> listProvice() {
		String hql="from RssAreaCode as rssAreaCode where "
				+ "areaCode like '%0000000'";
		List<RssAreaCode> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}
	//获取市级行政区列表
	public List<RssAreaCode> listCity(String codeHead) {
		String code=codeHead.substring(0,2);
		String hql="from RssAreaCode as rssAreaCode where areaCode like '"
		+code+"%00000' and areaCode !='"+code+"0000000'";

		List<RssAreaCode> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}
	//获取区县级行政区列表
	public List<RssAreaCode> listCounty(String codeHead) {
		String code=codeHead.substring(0,4);
		String hql="from RssAreaCode as rssAreaCode where "
				+ "areaCode like '"+code+"%000' and areaCode !='"+code+"00000'";

		List<RssAreaCode> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}	
	//获得村级行政区列表
	public List<RssAreaCode> listVillage(String codeHead) {
		String code=codeHead.substring(0,6);
		String hql="from RssAreaCode as rssAreaCode where "
				+ "areaCode like '"+code+"%' and areaCode !='"+code+"000'";
		List<RssAreaCode> results = null;
		try {
			org.hibernate.Query query = sessionFactory.getCurrentSession().createQuery(hql);
			results = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return results;
	}
	
//   public String save_android_user_register(String username,String password)
//   {
//	   //0表示注册失败，1注册成功，2用户名已存在
//	   String result="0";
//	   
//	   //判断用户名是否已存在
//	   String hql = "from RssUserDevice as r where r.username='"+username+"'";
//	   System.out.println(hql);
//	   Session session = sessionFactory.getCurrentSession(); 
//	   try {
//			org.hibernate.Query query = session.createQuery(hql);
//			if( query.list()!=null && query.list().size()!=0)
//				{
//				  result="2";				  
//				  return result;
//				}
//			session.clear();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	   
//	   try{
//		   
//	   //保存新用户
//	   RssUserDevice rssUserDevice = new RssUserDevice();
//	   rssUserDevice.setUsername(username);
//	   rssUserDevice.setPassword(password);
//	   session.saveOrUpdate(rssUserDevice); 	  
//	   result="1";
//	   } catch (Exception e) {
//		e.printStackTrace();
//	   }
//  
//	   return result;
//   }
   
	 public String save_android_user_register(String username,String password)
	   {
		   //0表示注册失败，1注册成功，2用户名已存在
		   String result="0";
		   
		   //判断用户名是否已存在
		   String hql = "from uUserInfo as r where r.userName='"+username+"'";
		   System.out.println(hql);
		   Session session = sessionFactory.getCurrentSession(); 
		   try {
				org.hibernate.Query query = session.createQuery(hql);
				if( query.list()!=null && query.list().size()!=0)
					{
					  result="2";				  
					  return result;
					}
				session.clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		   
		   try{
			   
		   //保存新用户
		   RssUserDevice rssUserDevice = new RssUserDevice();
		   rssUserDevice.setUsername(username);
		   rssUserDevice.setPassword(password);
		   session.saveOrUpdate(rssUserDevice); 	  
		   result="1";
		   } catch (Exception e) {
			e.printStackTrace();
		   }
	  
		   return result;
	   }   
}