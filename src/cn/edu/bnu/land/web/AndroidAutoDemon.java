package cn.edu.bnu.land.web;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.edu.bnu.land.service.AndroidServic;
import cn.edu.bnu.land.service.InfoArticleService;

//未用到 待删除文件 bygx

@Controller
public class AndroidAutoDemon implements ServletContextListener{
	private InfoArticleService infoArticleService;
	AndroidTimer myTask = null;
	Timer timer = new Timer();	 
	
	@Autowired
	   public AndroidAutoDemon(InfoArticleService infoArticleService) 
	   {
	       this.infoArticleService=infoArticleService;
	   }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		myTask.cancel();
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		 myTask=new AndroidTimer(infoArticleService);
		 System.out.println("AndroidListener*********************8test");         
		 timer.schedule(myTask, 0, 10000);
	}
	
	
}
