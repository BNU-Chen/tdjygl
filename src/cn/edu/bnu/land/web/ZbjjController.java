package cn.edu.bnu.land.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.common.SpringContextHolder;
import cn.edu.bnu.land.model.User;
import cn.edu.bnu.land.model.UserHome;
import cn.edu.bnu.land.model.Zbcrsh;
import cn.edu.bnu.land.model.Zbgmgl;
import cn.edu.bnu.land.model.Zbjpcjjl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;
import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.service.UserService;
import cn.edu.bnu.land.service.ZbcrService;
import cn.edu.bnu.land.service.ZbjjService;



@Controller
public class ZbjjController {
	@Autowired
	private Zbjpcjjl cjjl;	
	@Autowired
	private Zbjpssjg ssjg;
	@Autowired
	private Zbpmjj zbjp;
    private Encoder codechange;
    
    @Autowired
	private ZbjjService zbjjService;
	 
    @RequestMapping(value = "/getjpxx",method=RequestMethod.POST)
   	@ResponseBody 	
   	public Map<String,Object> handGetZbsh(String zbpcbh) throws IOException 
   	{ 	
   		System.out.println("获取竞拍信息函数");
   		return   zbjjService.getJpxx(zbpcbh) ;   	 
   	}	
   	
   	 @RequestMapping(value = "/updatejpxx",method=RequestMethod.POST)
   		@ResponseBody 	
   		public Map<String,Object> handUpdateJP(@RequestBody Zbpmjj record) throws IOException, ParseException 
   		{ 	
   			System.out.println("设置竞拍信息函数");			
   			System.out.println("开始时间："+record.getKssj());
   			
   			
   			zbjjService.updateGmzt(record.getZbpcbh());
   			return   zbjjService.updateJpxx(record); 
   		}
   	 @RequestMapping(value = "/getcjjl",method=RequestMethod.GET)
   		@ResponseBody 	
   		public Map<String,Object> handGetCjjl(String zbpcbh,String start,String limit) throws IOException 
   		{ 	
   			System.out.println("获取竞拍信息函数");
   			return   zbjjService.getCjjl(zbpcbh,start,limit);   	 
   		}	
   		
   		@RequestMapping(value = "/getssjg",method=RequestMethod.GET)
   		@ResponseBody 	
   		public Map<String,Object> handGetSsjg(String zbpcbh) throws IOException 
   		{ 	
   			System.out.println("获取竞拍信息函数");
   			return   zbjjService.getSsjg(zbpcbh);  	 
   		}
   		
   		@RequestMapping(value = "/scht",method=RequestMethod.POST)
   		@ResponseBody 	
   		public Map<String,Object> generateHt(@RequestBody Zbgmgl ht) throws IOException 
   		{ 	
   			Map<String, Object> model = new TreeMap<String, Object>();
   			System.out.println("开始生成合同");
   			String htwz=zbjjService.updateHt(ht);
   			
   			zbjjService.updateGmWq(ht.getZbpcbh(),ht.getUserid(),htwz);
   			if(zbjjService.judgeWq(ht.getZbpcbh())==0)
   				{zbjjService.updateGgwq(ht.getZbpcbh());}
   			model.put("htwz",htwz);
   			model.put("success",true);			
   			return model;
   		}
		
		
}
