package cn.edu.bnu.land.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.ConfigReader;
import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.model.InfoChannel;
import cn.edu.bnu.land.model.RssAreaCode;
import cn.edu.bnu.land.service.AndroidServic;
import cn.edu.bnu.land.service.InfoArticleService;

@Controller
public class AndroidController {
   private AndroidServic rssService;
   private InfoArticleService infoArticleService;
   
   //标识，用于标记是否是第一次请求 启动计时器生成RSS xml
   public static Boolean flagInit=false;
   @Autowired
   public AndroidController(AndroidServic rssService,InfoArticleService infoArticleService) 
   {
       this.rssService = rssService;  
       this.infoArticleService=infoArticleService;
   }
   
   @RequestMapping(value = "/confirm_device_user"  ) //,method=RequestMethod.POST	
   @ResponseBody 	
	public Map<String,Object> confirm(@RequestParam("username") String username,@RequestParam("password") String password)
    {
      Boolean result=rssService.confirmDeviceUser(username, password);
      System.out.println("result:"+result);
      Map<String,Object> myList=new HashMap<String,Object>() ;
      myList.put("result", String.valueOf(result));
      System.out.println("验证结果"+myList);
      return myList;
    }
   
   @RequestMapping(value = "/save_Settings_of_RSSFeed"  ) //,method=RequestMethod.POST	
   @ResponseBody 	
	public void save_Settings_of_RSSFeed(@RequestParam("username") String username,
			@RequestParam("area") String area,
			@RequestParam("category") String category)
    {
	  System.out.println("save_Settings_of_RSSFeed");
      CreatRSSFeedBySettings(username, area, category);
    }
   
   /***
    * Android用户，点击设置页面的“保存”按钮时调用
    * 生成针对该用户的个性化RSSFeed
    * 参数分别代表:用户名，订阅的行政区代码，订阅的类别
   */
   public void CreatRSSFeedBySettings(String username,String area,String category)
   {	
	    System.out.println("999999999999999999"+username+"  "+area+"  "+category);
	   //根据行政区名 获取行政区代码
	    String areacode=this.rssService.getAreaCodebyName(area);
	    
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
	    Nlink.addContent("http://localhost:8080/tdlzHome/");
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
	    	System.out.println(myChannels);
	    	for (int k=0;k<myChannels.length;k++){
		    		List<InfoArticle> resultsk=this.infoArticleService.getArticleListAllForHome(Integer.parseInt(myChannels[k]));	
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
			    				news.addContent(new Element("link").addContent("http://172.16.209.247:8080/tdlzHome/ArticleDetailForAndriod.jsp?articleId="+resultsk.get(m).getArticleId().toString()+"&channelId="+myChannels[k]));		
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
		    				news.addContent(new Element("link").addContent("http://172.16.209.247:8080/tdlzHome/ArticleDetailForAndriod.jsp?articleId="+resultsk.get(m).getArticleId().toString()+"&channelId="+myChannels[k]));		
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
				 List<InfoArticle> results=this.infoArticleService.getArticleListAllForHome(i);	
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
						news.addContent(new Element("link").addContent("http://172.16.209.247:8080/tdlzHome/ArticleDetailForAndriod.jsp?articleId="+results.get(j).getArticleId().toString()+"&channelId="+i));		
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
					news.addContent(new Element("link").addContent("http://172.16.209.247:8080/tdlzHome/ArticleDetailForAndriod.jsp?articleId="+results.get(j).getArticleId().toString()+"&channelId="+i));		
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

	//根据行政区代码获取行政区名
 @RequestMapping(value = "/getAreaNameByCode"  ) //,method=RequestMethod.POST	
   @ResponseBody 	
	public Map<String,Object> getAreaNameByCode(@RequestParam("areaCode") String areaCode)
    {
      String areaName=rssService.getAreaNameByCode(areaCode);
      Map<String,Object> myList=new HashMap<String,Object>() ;
      myList.put("areaName", String.valueOf(areaName));
      System.out.println(myList);
      return myList;
    }
 
//获取省行政区列表，用于填充前台下拉菜单
@RequestMapping(value = "/get_infoArticleProvice")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public List<RssAreaCode> get_infoProvicelList() throws IOException 
	{ 	
		System.out.println("time to get_infoArticleProvice"); 
		List<RssAreaCode> myProvicelList = this.rssService.listProvice();
		return myProvicelList;	 
	} 

//获取市行政区列表，用于填充前台下拉菜单
@RequestMapping(value = "/get_infoArticleCity")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public List<RssAreaCode> get_infoCityList(@RequestParam("codeHead") String codeHead) throws IOException 
	{ 	
		System.out.println("time to get_infoArticleCity"); 
		List<RssAreaCode> myCitylList = this.rssService.listCity(codeHead);
		return myCitylList;	 
	} 

 //获取区县行政区列表，用于填充前台下拉菜单
 @RequestMapping(value = "/get_infoArticleCounty")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public List<RssAreaCode> get_infoCountylList(@RequestParam("codeHead") String codeHead) throws IOException 
	{ 	
		System.out.println("time to get_infoArticleCountry"); 
		List<RssAreaCode> myCountylList = this.rssService.listCounty(codeHead);
		return myCountylList;	 
	} 
 //获取村级行政区列表，用于填充前台下拉菜单
 @RequestMapping(value = "/get_infoArticleVillage")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public List<RssAreaCode> get_infoVillageList(@RequestParam("codeHead") String codeHead) throws IOException 
	{ 	
		System.out.println("time to getZonelList"); 
		List<RssAreaCode> myVillageList = this.rssService.listVillage(codeHead);
		return myVillageList;	 
	} 
 
 @RequestMapping(value = "/creatQRCode")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public void creatQRCode(@RequestParam("url") String url) 
	{ 	
		System.out.println("time to creatQRCode"); 
		String realPath=Thread.currentThread().getContextClassLoader().getResource(".").getPath(); 
		int pos=realPath.indexOf("/lib");
		realPath=realPath.substring(0, pos);
		String pname_houtai=ConfigReader.getValue("appName_jyhoutai");
		String imgPath1=realPath+"/wtpwebapps/"+pname_houtai+"/images/rss_QRCode.png";		
		System.out.println(imgPath1);
        String encoderContent = ConfigReader.getWholeurl_jyhoutai()+"/rss_reader.apk"; 
        System.out.println(imgPath1);
        if(!url.isEmpty() && !url.equals(""))encoderContent=url;
        AndroidTwoDimensionCode handler = new AndroidTwoDimensionCode();  
        handler.encoderQRCode(encoderContent, imgPath1, "png"); 	
        
        //保存到前台项目中的二维码
		String pname_webSite=ConfigReader.getValue("appName_webSite");
        String imgPath2=realPath+"/wtpwebapps/"+pname_webSite+"/images/rss_QRCode.png";		
        handler.encoderQRCode(encoderContent, imgPath2, "png");
	} 
 
 @RequestMapping(value = "/getQRCodeContent")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public String getQRCodeContent() 
	{ 	
		System.out.println("time to getQRCodeContent"); 
		String realPath=Thread.currentThread().getContextClassLoader().getResource(".").getPath(); 
		int pos=realPath.indexOf("/lib");
		realPath=realPath.substring(0, pos);
		
		String pname_houtai=ConfigReader.getValue("appName_jyhoutai");
		realPath=realPath+"/wtpwebapps/"+pname_houtai+"/images";
		String imgPath =realPath+ "/rss_QRCode.png";  
		System.out.println(imgPath);
		AndroidTwoDimensionCode handler = new AndroidTwoDimensionCode();  
		String decoderContent = handler.decoderQRCode(imgPath); 
		Map<String,Object> result = new HashMap<String, Object>();   		  
		result.put("success",true);  
		result.put("content", decoderContent); 
		return decoderContent;
		
	} 
 
 //android客户端新用户注册
@RequestMapping(value = "/save_android_user_register")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public Map<String,Object> save_android_user_register(@RequestParam("username") String username,@RequestParam("password") String password)  
	{ 	
		System.out.println("time to android_user_register"); 
		Map<String,Object> myList=new HashMap<String,Object>() ;
		myList.put("result",rssService.save_android_user_register(username, password));
		return myList;	 
	} 


//此方法目前还没调用 bygx
//页面加载时，通过控件事件调用，使用定时器不断刷新生成RSS的xml文件
@RequestMapping(value = "/timer_creat_xml")//,method=RequestMethod.POST) 
	public void timer_creat_xml()  
	{ 			
		//定时器生成xml
		if(!flagInit){
		AndroidTimer myTask = new AndroidTimer(infoArticleService);
		Timer timer = new Timer();	
		timer.schedule(myTask, 0, 10000);
		flagInit=true;
		}
	  System.out.println("time to timer_creat_xml"); 
	} 
 

}
