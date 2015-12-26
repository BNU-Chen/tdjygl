package cn.edu.bnu.land.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConfigurationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("webapp is exiting...");	
		try {
			HttpURLConnection conn = (HttpURLConnection)new URL("http://localhost:8090/login-lyj.jsp?shutdown=doit").openConnection();
	        conn.setConnectTimeout(5000);  
	        conn.setRequestMethod("GET");
	        
	        int code = conn.getResponseCode();
	        System.out.println("send request to close HERITRIX:"+conn.getResponseMessage());
	        conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("webapp is starting...");

		try {
			String path=ConfigReader.getRootPath();
			path=path.substring(0,path.lastIndexOf("/"));
			path=path.substring(0,path.lastIndexOf("/"));
			System.out.println("heritrix path:"+path);
			Process p = Runtime.getRuntime().exec("cmd /c start "+path+"/heritrix/bin/start.bat");			
			p.waitFor();//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
