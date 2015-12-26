package cn.edu.bnu.land.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.stereotype.Controller;

import cn.edu.bnu.land.common.ConfigReader;
import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.service.InfoArticleService;

@Controller
public class AndroidTimer extends TimerTask {
	
	private InfoArticleService infoArticleService;
	public AndroidTimer() {
		
		
	}
	
	public AndroidTimer(InfoArticleService infoArticleService) {
		super();
		this.infoArticleService = infoArticleService;
	}

	int count=0;
	@Override
	public void run() {
		count++;
		System.out.println("第： "+count+" 次执行timer");		
		String path="http://localhost:8080/"+ConfigReader.getValue("appName_jyhoutai")+"/get_pubArticleList";  
        //将用户名和密码放入HashMap中
		Map<String,String> params=new HashMap<String,String>(); 
        try {
			sendGETRequest(path,params,"UTF-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //InfoArticleService infoArticleService=AndroidListener.infoArticleService;
		for(int i=1;i<7;i++)
		{	
            if(infoArticleService==null) return;
            List<InfoArticle> results=infoArticleService.getArticleListAllForHome(i);				
            System.out.println(results);	
            if(results==null) return;
            
            //创建Document对象，添加Attribute属性
            Element root = new Element("rss");
		    root.setAttribute(new Attribute("version", "2.0"));
		    
		    Document doc = new Document(root);   
		    Element m_channel = new Element("channel");
		    root.addContent(m_channel);
		    Element Ntitle = new Element("title");
		    
		    String m_chanelTitile="";//频道标题
		    String m_fileName="";//xml文件名

		    switch (i)
		    {
			    case 1:
			    {
			    	m_chanelTitile="重庆市农地交易最新动态";
			    	m_fileName="zxdt";
			    	break;
			    }
			    case 2:
			    {
			    	m_chanelTitile="重庆市农地交易通知公告";
			    	m_fileName="tzgg";
			    	break;
			    }
			    case 3:
			    {
			    	m_chanelTitile="重庆市农地转出信息";
			    	m_fileName="ndzc";
			    	break;
			    }
			    case 4:
			    {
			    	m_chanelTitile="重庆市农地转入信息";
			    	m_fileName="ndzr";
			    	break;
			    }
			    case 5:
			    {
			    	m_chanelTitile="重庆市农地最近交易";
			    	m_fileName="zjjy";
			    	break;
			    }
			    case 6:
			    {
			    	m_chanelTitile="重庆市农地交易流程";
			    	m_fileName="jylc";
			    	break;
			    }
		    }
		    Ntitle.addContent(m_chanelTitile);
		    m_channel.addContent(Ntitle);
		           
		    Element Nlink = new Element("link");
		    String myip=ConfigReader.getIpAndPort();
		    Nlink.addContent(myip+"/tdlz_homeWebSite/");
		    m_channel.addContent(Nlink);
		   
		    Element Ndescription = new Element("description");
		    Ndescription.addContent("重庆市农地转出信息订阅");
		    m_channel.addContent(Ndescription);
		    
		    Element Nlanguage = new Element("language");
		    Nlanguage.addContent("zh-cn");
		    m_channel.addContent(Nlanguage);
		   
		    Element Ncopyright = new Element("copyright");
		    Ncopyright.addContent("Copyright 2014 重庆市农地流转信息网. All Rights Reserved.");
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
		   
		    //读入数据库数据 
		    //增加ITEM
		    String strContent;
		    strContent = "请点击查看全文....";
		    for(int j=0;j<results.size();j++)
			{
				System.out.println(results.get(j));
				Element news = new Element("item");//生成元素      　　　　　　　　　
				news.addContent(new Element("title").addContent(results.get(j).getArticleName()));
				news.addContent(new Element("link").addContent(myip+"/tdlz_homeWebSite/ArticleDetailForAndriod.jsp?articleId="+results.get(j).getArticleId().toString()+"&channelId="+i));		
				news.addContent(new Element("pubdate").addContent(FormatRssDate(results.get(j).getArticlePublishtime())));
				news.addContent(new Element("coordinate").addContent("29.979764,107.219629;29.998282,107.298393;29.94472,107.287469;29.948725,107.198932"));
				String temp=results.get(j).getArticleContent();	 
				if(temp !=null && temp.length()>400)
				strContent=temp.substring(0,400)+"... ...    请点击查看全文....";
				else
				strContent=	temp;
				news.addContent(new Element("description").addContent(new CDATA(strContent)));
				m_channel.addContent(news);
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
		        System.out.println("creat RSS XML successful！");
		    }catch (java.io.IOException e) {
		        e.printStackTrace();
		    }
		    
		    //
		}
	}

	public static String FormatRssDate(Date dt) {
        SimpleDateFormat RssFmtDt=new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss z");
        return RssFmtDt.format(dt).toString();
    }
	
	//用于发送url请求
	static boolean sendGETRequest(String path,  
            Map<String, String> params,String encode) throws MalformedURLException, IOException {  
            System.out.println("执行了请求： ");
		    StringBuilder myurl=new StringBuilder(path);  
            myurl.append("?");              
            for(Map.Entry<String, String> entry:params.entrySet())  
            {  
                myurl.append(entry.getKey()).append("=");  
                myurl.append(URLEncoder.encode(entry.getValue(),encode));  
                myurl.append("&");  
            }  
            //删掉最后一个&   
            myurl.deleteCharAt(myurl.length()-1);  
            HttpURLConnection conn=(HttpURLConnection)new URL(myurl.toString()).openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setRequestMethod("GET");  
            if(conn.getResponseCode()==200)  
             { 
                conn.disconnect();
               return true;  
             } 
            else
               conn.disconnect();
               return false;  
             }     

}
