package cn.edu.bnu.land.common;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderListener;

import cn.edu.bnu.land.service.InfoArticleService;
import cn.edu.bnu.land.web.AndroidTimer;


//未用到  待删除文件 bygx

@Controller
public class AndroidListener implements ServletContextListener {	
	
	public static InfoArticleService  infoArticleService=null;
	
//	AndroidTimer myTask = new AndroidTimer();
//	Timer timer = new Timer();	 
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		myTask.cancel();
//		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
//		 System.out.println("AndroidListener*********************8test");         
//		 timer.schedule(myTask, 0, 10000);
	}

}
