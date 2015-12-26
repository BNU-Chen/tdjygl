package cn.edu.bnu.land.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.regex.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.aspectj.apache.bcel.util.ClassPath;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.lang.reflect.*;
import java.lang.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.bnu.land.common.Encoder;

public class Tool {
	 public static byte[] getByte(File file) throws Exception

	    {
	        byte[] bytes = null;
	        if(file!=null)
	        {
	            InputStream is = new FileInputStream(file);
	            int length = (int) file.length();
	            if(length>Integer.MAX_VALUE)   //褰撴枃浠剁殑闀垮害瓒呰繃浜唅nt鐨勬渶澶у�
	            {
	                System.out.println("this file is max ");
	                return null;
	            }
	            bytes = new byte[length];
	            int offset = 0;
	            int numRead = 0;
	            while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
	            {
	                offset+=numRead;
	            }
	            //濡傛灉寰楀埌鐨勫瓧鑺傞暱搴﹀拰file瀹為檯鐨勯暱搴︿笉涓�嚧灏卞彲鑳藉嚭閿欎簡
	            if(offset<bytes.length)
	            {
	                System.out.println("file length is error");
	                return null;
	            }
	            is.close();
	        }
	        return bytes;
	    }
	 
	 public static String fileUpload(HttpServletRequest request,String relativeFilePath,String cmpName,String newfileName,boolean b) throws Exception{
		 //relativeFilePath为文件夹相对路径
		  MultipartHttpServletRequest multipartHttpservletRequest=(MultipartHttpServletRequest) request;  
	      MultipartFile multipartFile = multipartHttpservletRequest.getFile(cmpName); 
	     
	      String originalFileName=multipartFile.getOriginalFilename();
	     
	      String extName=originalFileName.substring(originalFileName.lastIndexOf('.'), originalFileName.length());
	      String name=originalFileName.substring(0, originalFileName.lastIndexOf('.'));
	      String path=null;
	      if(b)
	      {
	       path=Tool.createFile(relativeFilePath,newfileName+Tool.RandomString(10)+extName);
	      }
	      else
	      {
	       path=Tool.createFile(relativeFilePath,newfileName+extName);
	      }
	      
	     //File file=new File(path);//姝ゅzcw搴斾粠浼氳瘽涓幏鍙�
	    // System.out.println(file.getAbsolutePath());  
	        try {  
	            FileOutputStream fileOutputStream=new FileOutputStream(path);  
	            fileOutputStream.write(multipartFile.getBytes());  
	            fileOutputStream.flush();  
	            fileOutputStream.close();  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	           // return "error";  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	           // return "error";  
	            
	        }  
	        
	       // Map<String,Object> model = new HashMap<String, Object>();   		  
	       // model.put("success",true);  
	       // model.put("msg", "successfully saved");   
	        return  relativeFilePath+"/"+newfileName+extName;
	 }
	 
	 public static String RandomString(int length)
	 {
	  String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	  Random  random = new Random();
	  StringBuffer buf = new StringBuffer();
	  
	  for(int i = 0 ;i < length ; i ++)
	  {
	   int num = random.nextInt(62);
	   buf.append(str.charAt(num));
	  }
	  
	  return buf.toString();
	 }
	 
	 public static String createFile(String dirPath,String fileName){//输入相对目录名和文件名（包括扩展名）
			
		  String path0=Thread.currentThread().getContextClassLoader().getResource("").getPath();
		  //System.out.println("path0:"+path0); 
	      int n=path0.indexOf("WEB");
	      String path=path0.substring(0, n)+dirPath;
	      //System.out.println("path:"+path); 
	      File dir = new File(path);
			if(!dir.exists()){
			  dir.mkdirs();
			} 
		 File file = new File(path+"/"+fileName);
		 
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 //System.out.println("文件全路径:"+path+"/"+fileName); 
			return path+"/"+fileName;
		}
	 public static String getDate(String format) {
		 SimpleDateFormat formatter = new SimpleDateFormat(format);

		 return formatter.format(new Date());
	 }
	 
	 public static int  getYear()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return y;
	 }   
	 
	 public static int  getMonth()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return m+1;
	 } 
	 
	 public static int  getDay()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return d;
	 } 
	 
	 public static int  getHour()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return h;
	 }   
	
	 public static int  getMinite()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return mi;
	 } 
	 
	 public static int  getsecond()   
	 {   
	 int y,m,d,h,mi,s;   
	 Calendar cal=Calendar.getInstance();   
	 y=cal.get(Calendar.YEAR);   
	 m=cal.get(Calendar.MONTH);   
	 d=cal.get(Calendar.DATE);   
	 h=cal.get(Calendar.HOUR_OF_DAY);   
	 mi=cal.get(Calendar.MINUTE);   
	 s=cal.get(Calendar.SECOND);   
	 return s;
	 
	}
	 
	 public static UserDetails getUserDetail(){
		  return (UserDetails) SecurityContextHolder.getContext()
		     .getAuthentication()
		     .getPrincipal();
		 }
	 public static Date getGMT(Date dateCST) throws java.text.ParseException { 
		   DateFormat df = new SimpleDateFormat("EEE, d-MMM-yyyy HH:mm:ss z"); 
		   df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); // modify Time Zone. 
		   
		 
		   return (df.parse(df.format(dateCST))); 
		} 
	 
	 public static long getDiff(Date dateStart,Date dateEnd)
	 {
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 long diff=0;
		 try
		 {
		    Date d1 = df.parse("2004-03-26 13:31:40");
		    Date d2 = df.parse("2004-01-02 11:30:24");
		    
		     diff = dateStart.getTime() - dateEnd.getTime();
		    long days = diff / (1000 * 60 * 60 * 24);
		    
		 }
		 catch (Exception e)
		 {
		 }
		 return diff;
	 }
}

	


