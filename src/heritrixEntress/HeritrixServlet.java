package heritrixEntress;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeritrixServlet
 */
@WebServlet("/HeritrixServlet")
public class HeritrixServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//MyThread t1 = new MyThread();
	Process p = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeritrixServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//在servlet初始化时启动Heritrix批处理开始程序
		
		//t1.run();
		//t1.start();
//		String cmd="F:\\eclipsework2\\heritrix\\bin\\start.bat";
//
//		System.out.println(cmd);
		try {
			p = Runtime.getRuntime().exec("cmd /c start F:\\eclipsework2\\heritrix\\bin\\start.bat");
			//p=Runtime.getRuntime().exec("cmd /c start G:\\softwareFree\\heritrix\\bin\\start.bat");
			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("HeritrixServlet Destory");

		
		 Map<String,String> params=new HashMap<String,String>();  
         params.put("shutdown", "doit");  
         //params.put("password", strPassword); 
         //String resultString="";
         sendGETRequest ss = new sendGETRequest();
         try {
			ss.sendGETRequest("http://localhost:8090/login-lyj.jsp",params,"UTF-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("HeritrixServlet !!!!!!!!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
		
	}

}
