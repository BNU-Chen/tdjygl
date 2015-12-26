package heritrixEntress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class sendGETRequest {
	
	
	
    public void  sendGETRequest(String path,Map<String, String> params,String encode) throws MalformedURLException, IOException {  
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
           // HttpURLConnection conn=(HttpURLConnection)new URL(myurl.toString()).openConnection(); 
            HttpURLConnection conn=(HttpURLConnection)new URL("http://localhost:8090/login-lyj.jsp?shutdown=doit").openConnection(); 
            conn.setConnectTimeout(5000);  
            conn.setRequestMethod("GET");  
            System.out.println("conn1"+conn.getResponseMessage());

            
            int code = conn.getResponseCode();
            System.out.println(conn.getResponseMessage());
            String sCurrentLine = ""; 
            String sTotalString = ""; 
//            if (code == 200)
//            {
//                Sy
//            } else
//            {
//                sTotalString =String.valueOf(code);
//
//            }
            
            conn.disconnect();
            
    }

}
