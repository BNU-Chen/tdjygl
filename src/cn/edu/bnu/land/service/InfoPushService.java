package cn.edu.bnu.land.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.common.ConfigReader;
import cn.edu.bnu.land.model.AttachmentMail;
import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.model.InfoArticleHome;
import cn.edu.bnu.land.model.MailSenderInfo;
import cn.edu.bnu.land.model.Mbssendmsg;
import cn.edu.bnu.land.model.MbssendmsgHome;
import cn.edu.bnu.land.model.MbssendmsgId;
import cn.edu.bnu.land.model.User;

@Service
public class InfoPushService {
	
	private InfoArticleHome infoArticleHome;
	private SessionFactory sessionFactory;
	private UserService userService;
    private MbssendmsgHome mbssendmsgHome;
    
    int count=0;
    
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setInfoArticleHome(InfoArticleHome infoArticleHome,MbssendmsgHome mbssendmsgHome) {
		this.infoArticleHome = infoArticleHome;
		this.mbssendmsgHome=mbssendmsgHome;

	}
	
	/*
	    * 功能描述：将用户选中的地票信息推送给全部注册并订阅的用户，或者推送给指定地址的用户
	    * 参数说明：ids,地票id字符串，id间使用','分隔。
	    * 参数说明：adresses,收件地址字符串，地址间使用','分隔
	    * 参数说明:chanelId,用于标识文章的id 
	    * 函数主要逻辑：根据ids查询地票，构建邮件内容；判断adresses是否空值，空则查询已注册并选择订阅的用户的邮件地址。
	    * 函数返回值：所有操作成功，则返回true，否则返回false
	    * */
	    public Boolean update_sendMail (String articleids,String adresses,String chanelId) throws MessagingException, IOException {
	    	
	    	Boolean flag=true;
	    	Session session = sessionFactory.getCurrentSession();
	        
	    	MailSenderInfo mailInfo = new MailSenderInfo();
	        mailInfo.setMailServerHost("smtp.163.com");
	        //发送邮箱参数设置
	        // qq，163,sina接收邮件测试通过
	        mailInfo.setMailServerPort("25");
	        mailInfo.setValidate(true);
	        mailInfo.setUserName("tudiliuzhuan2013");
	        mailInfo.setPassword("tudiliuzhuan");
	        mailInfo.setFromAddress("tudiliuzhuan2013@163.com");
	    
	        //根据地票id查询地票表，构建邮件内容
	        String content="";
	        String[] articleidsArr=articleids.split(",");
	       
	        
	        for (int i=0;i<articleidsArr.length;i++)
	        {
	        	String hql = "from InfoArticle as infoArticle where channelId="
	    				+ chanelId+" and articleId="+articleidsArr[i];
	        	System.out.println(hql);
	        	
	        	List<InfoArticle> results = null;
	    		try {
	    			org.hibernate.Query query = sessionFactory.getCurrentSession()
	    					.createQuery(hql);	 			
	    			results = query.list();	    			
	    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		
	    		content=results.get(0).getArticleContent()+"\n"+
	        			"感谢您的订阅！";
	    		System.out.println(content);
        
	    		//如果用户没有指定地址，那么邮件的接收地址为所有已注册的用户
	        	if(adresses.equals("")||adresses.isEmpty()){
			        List<User> myUserList=userService.getAllUser();
			        for (User user:myUserList)
			        {   
			        	//判断用户是否订阅了邮件信息推送
			        	if(user.getMailRequest()==true){
			        		
					        mailInfo.setToAddress(user.getEmail());
					        mailInfo.setSubject("重庆市农地交易信息"); 
					        mailInfo.setContent(content);	        
					        AttachmentMail am = new AttachmentMail();
					        //初始化mail信息
					        if (am.sendAttachmentMail(mailInfo)==false) flag=false;
			        	}
			        }
	        	}
	        	else{//否则，发送给指定用户
	      	        String[] adressArr=adresses.split(",");
	      	        for(int j=0;j<adressArr.length;j++){
				        mailInfo.setToAddress(adressArr[j]);
				        mailInfo.setSubject("重庆市农地交易信息"); 
				        mailInfo.setContent(content);	        
				        AttachmentMail am = new AttachmentMail();
				        //初始化mail信息
				        if (am.sendAttachmentMail(mailInfo)==false) flag=false;
	      	        }
	        	}
	        	if(flag){
	        	results.get(0).setIsMailpush("是");
	        	System.out.println(results.get(0).getIsMailpush());
	        	session.saveOrUpdate(results.get(0));}
	        }
	        return flag;
	       
	    }
	    
	    public Boolean createSendSMS (String articleids,String userPhones,String chanelId) throws MessagingException, IOException {
	        Boolean flag=true;  
	        
	        //根据地票id查询地票表，构建邮件内容
	        String content="";
	        String[] articleidsArr=articleids.split(",");
	        Session session = this.sessionFactory.getCurrentSession();
	        for (int i=0;i<articleidsArr.length;i++)
	        {
	        	String hql = "from InfoArticle as infoArticle where channelId="
	    				+ chanelId+" and articleId="+articleidsArr[i];
	        	System.out.println(hql);
	        	
	        	List<InfoArticle> results = null;
	    		try {
	    			org.hibernate.Query query = sessionFactory.getCurrentSession()
	    					.createQuery(hql);	 			
	    			results = query.list();	    			
	    			
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    		content=results.get(0).getArticleContent();
	    		int length=content.length();
	    		System.out.println(content);
	    		
	    		//读取配置文件，获取sim卡卡号
	    		String SenderString=ConfigReader.getValue("sender");
	    		
	    		//短信配置值
	    		short  msgType=1;//1短信 512彩信
		     	short  msgLevel=0;//0普通发送 1优先发送
		     	short screenHeight=200; //接收手机屏幕高度
		     	short screenWidth=140;//接收手机屏幕宽度
		     	short MsgStatus=0;//0请求发送  1发送处理中 -1发送失败 2发送成功 3不符合规范 -2未收到送达短信回执 3信息已送达
		     	short comPort=(short)Integer.parseInt(ConfigReader.getValue("sms_comPort"));//端口号
		     	String dateTimeString;//发送时间
		     	String MsgIDString;//短信id
		     	SimpleDateFormat dftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     	SimpleDateFormat dfid=new SimpleDateFormat("yyyyMMddHHmmss");
	    		
		     	//如果没有指定手机号码，那么发短信给所有已注册的用户手机号码
	        	if(userPhones.equals("")||userPhones.isEmpty()){
			        List<User> myUserList=userService.getAllUser();
			        for (User user:myUserList)
			        {  	
		        	   if(length>280)
	                   {
		        		   double times=(float)length/(float)280;
                    	   int num=(int)Math.ceil(times);//向上取整
	                	   System.out.println(length);
	                	   String subContentString;
	                	   //这几条短信的MsgID相同
	            		   Date mydate1=new Date();
				     	   dateTimeString=String.valueOf(dftime.format(mydate1));
				     	   
				     	  
	                	   for(int k=0;k<num-1;k++)
	                	   {   
	                		   subContentString=content.substring(k*280, (k+1)*280-1);
	                		   if(subContentString != null)
	                		   {
	                			   MbssendmsgId mbssendmsgIdk=new MbssendmsgId();
	                			   count=count+1;
	    				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
	    				     	   MsgIDString=MsgIDString+String.valueOf(count);
	                			   mbssendmsgIdk.setMsgId(MsgIDString);
	    				     	   Mbssendmsg mm = new Mbssendmsg();
	    				     	   mm.setSubject(subContentString);
	    				     	   mm.setMsgSize(subContentString.length());
	   				     		   mm.setSender(SenderString);
	   				     		   mm.setRecipient(user.getPhone());
	   					     	   mm.setMsgLevel(msgLevel);//即时发送
	   					     	   mm.setMsgType(msgType);//1：短信；512：彩信
	   					     	   mm.setRequestTime(dateTimeString);
	   					     	   mm.setScreenHeight(screenHeight);
	   					     	   mm.setScreenWidth(screenWidth);
	   					     	   mm.setComPort(comPort);
	   					     	   mm.setId(mbssendmsgIdk);
	   					     	   mm.setMsgStatus(MsgStatus);
	   					     	   mm.setNeedFeedback("0");
	   					     	   mm.setNeedReadFeedback("0");
	   					     	   System.out.println("存储的短信id: "+mbssendmsgIdk.getMsgId());	
	   					     	   session.save(mm);
	    				     	   System.out.println(subContentString);
	                		   }   
	                	   }
	                	   MbssendmsgId mbssendmsgIdl=new MbssendmsgId();
	                	   count=count+1;
				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
				     	   MsgIDString=MsgIDString+String.valueOf(count);
	                	   mbssendmsgIdl.setMsgId(MsgIDString);
	                	   subContentString=content.substring((num-1)*280, length-1);
	                	   Mbssendmsg mmm = new Mbssendmsg();
				     	   mmm.setSubject(subContentString);
				     	   mmm.setMsgSize(subContentString.length());
			     		   mmm.setSender(SenderString);
			     		   mmm.setRecipient(user.getPhone());
				     	   mmm.setMsgLevel(msgLevel);//即时发送
				     	   mmm.setMsgType(msgType);//1：短信；512：彩信
				     	   mmm.setRequestTime(dateTimeString);
				     	   mmm.setScreenHeight(screenHeight);
				     	   mmm.setScreenWidth(screenWidth);
				     	   mmm.setComPort(comPort);
				     	   mmm.setId(mbssendmsgIdl);
				     	   mmm.setMsgStatus(MsgStatus);
				     	   mmm.setNeedFeedback("0");
				     	   mmm.setNeedReadFeedback("0");
				     	   System.out.println("存储的短信id: "+mbssendmsgIdl.getMsgId());	
				     	   session.save(mmm);
				     	   System.out.println(subContentString);
	                	   
	                   }
			     	  //长度<280，则直接发送
			     	   else
			     	   {
			     		   Mbssendmsg mm = new Mbssendmsg();
			     		   mm.setSender(SenderString);
			     		   mm.setRecipient(user.getPhone());
				     	   Date mydate1=new Date();
				     	   dateTimeString=String.valueOf(dftime.format(mydate1));
				     	   count=count+1;
				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
				     	   MsgIDString=MsgIDString+String.valueOf(count);
				     	   MbssendmsgId mbssendmsgId1=new MbssendmsgId();
				     	   mbssendmsgId1.setMsgId(MsgIDString);
				     	   mm.setMsgLevel(msgLevel);//即时发送
				     	   mm.setMsgType(msgType);//1：短信；512：彩信
				     	   mm.setRequestTime(dateTimeString);
				     	   mm.setScreenHeight(screenHeight);
				     	   mm.setScreenWidth(screenWidth);
				     	   mm.setComPort(comPort);
				     	   mm.setId(mbssendmsgId1);
				     	   mm.setMsgStatus(MsgStatus);
				     	   mm.setNeedFeedback("0");
				     	   mm.setNeedReadFeedback("0");
				     	   mm.setSubject(content);
				     	   System.out.println("存储的短信id: "+mbssendmsgId1.getMsgId());	
				     	   session.save(mm);
			     	   }
			     	  
	      	        	
			        }
	        	}
	        	else{//否则，发送给指定用户
	      	        String[] userPhonesArr=userPhones.split(",");
	      	        for(int j=0;j<userPhonesArr.length;j++){
	      	        	System.out.println("用户手机号码："+userPhonesArr[j]);
                       //如果长度大于280，则需要分为几条短息
                       if(length>280)
                       {
                    	   double times=(float)length/(float)280;
                    	   int num=(int)Math.ceil(times);//向上取整
                    	   System.out.println(length);
                    	   String subContentString;
                    	   //这几条短信的MsgID相同
                		   Date mydate1=new Date();
				     	   dateTimeString=String.valueOf(dftime.format(mydate1));
				     	   
                    	   for(int k=0;k<num-1;k++)
                    	   {   
                    		   subContentString=content.substring(k*280, (k+1)*280-1);
                    		   if(subContentString != null)
                    		   {
                    			   MbssendmsgId mbssendmsgIdLongM=new MbssendmsgId();
                    			   count=count+1;
        				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
        				     	   MsgIDString=MsgIDString+String.valueOf(count);
                    			   mbssendmsgIdLongM.setMsgId(MsgIDString);
        				     	   Mbssendmsg mm = new Mbssendmsg();
        				     	   mm.setSubject(subContentString);
        				     	   mm.setMsgSize(subContentString.length());
       				     		   mm.setSender(SenderString);
       				     		   mm.setRecipient(userPhonesArr[j]);
       					     	   mm.setMsgLevel(msgLevel);//即时发送
       					     	   mm.setMsgType(msgType);//1：短信；512：彩信
       					     	   mm.setRequestTime(dateTimeString);
       					     	   mm.setScreenHeight(screenHeight);
       					     	   mm.setScreenWidth(screenWidth);
       					     	   mm.setComPort(comPort);
       					     	   mm.setId(mbssendmsgIdLongM);
       					     	   mm.setMsgStatus(MsgStatus);
       					     	   mm.setNeedFeedback("0");
       					     	   mm.setNeedReadFeedback("0");
       					     	   System.out.println("存储的短信id: "+mbssendmsgIdLongM.getMsgId());	
       					     	   session.save(mm);
        				     	   System.out.println(subContentString);
                    		   }   
                    	   }
                    	   MbssendmsgId mbssendmsgIdLongM2=new MbssendmsgId();
                    	   count=count+1;
				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
				     	   MsgIDString=MsgIDString+String.valueOf(count);
                    	   mbssendmsgIdLongM2.setMsgId(MsgIDString);
                    	   subContentString=content.substring((num-1)*280, length-1);
                    	   Mbssendmsg mmm = new Mbssendmsg();
				     	   mmm.setSubject(subContentString);
				     	   mmm.setMsgSize(subContentString.length());
			     		   mmm.setSender(SenderString);
			     		   mmm.setRecipient(userPhonesArr[j]);
				     	   mmm.setMsgLevel(msgLevel);//即时发送
				     	   mmm.setMsgType(msgType);//1：短信；512：彩信
				     	   mmm.setRequestTime(dateTimeString);
				     	   mmm.setScreenHeight(screenHeight);
				     	   mmm.setScreenWidth(screenWidth);
				     	   mmm.setComPort(comPort);
				     	   mmm.setId(mbssendmsgIdLongM2);
				     	   mmm.setMsgStatus(MsgStatus);
				     	   mmm.setNeedFeedback("0");
				     	   mmm.setNeedReadFeedback("0");
				     	   System.out.println("存储的短信id: "+mbssendmsgIdLongM2.getMsgId());	
				     	   session.save(mmm);
				     	   System.out.println(subContentString);
                    	   
                       }
			     	  //长度<280，则直接发送
			     	   else
			     	   {
			     		   Mbssendmsg mm = new Mbssendmsg();
			     		   mm.setSender(SenderString);
			     		   mm.setRecipient(userPhonesArr[j]);
				     	   Date mydate1=new Date();
				     	   dateTimeString=String.valueOf(dftime.format(mydate1));
				     	   count=count+1;
				     	   MsgIDString=String.valueOf(dfid.format(new Date()));
				     	   MsgIDString=MsgIDString+String.valueOf(count);
				     	   MbssendmsgId mbssendmsgId1=new MbssendmsgId();
				     	   mbssendmsgId1.setMsgId(MsgIDString);
				     	   mm.setMsgLevel(msgLevel);//即时发送
				     	   mm.setMsgType(msgType);//1：短信；512：彩信
				     	   mm.setRequestTime(dateTimeString);
				     	   mm.setScreenHeight(screenHeight);
				     	   mm.setScreenWidth(screenWidth);
				     	   mm.setComPort(comPort);
				     	   mm.setId(mbssendmsgId1);
				     	   mm.setMsgStatus(MsgStatus);
				     	   mm.setNeedFeedback("0");
				     	   mm.setNeedReadFeedback("0");
				     	   mm.setSubject(content);
				     	   mm.setMsgSize(content.length());
				     	   System.out.println("存储的短信id: "+mbssendmsgId1.getMsgId());	
				     	   session.save(mm);
			     	   }
			     	  
	      	        }
	        	}
	        	if(flag){
	        	results.get(0).setIsSmspush("是");
	        	session.saveOrUpdate(results.get(0));
	        	}
	        }
	        return flag;
	       
	    }
	    
	    

}
