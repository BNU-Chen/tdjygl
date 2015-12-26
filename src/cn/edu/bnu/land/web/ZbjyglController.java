package cn.edu.bnu.land.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
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
import cn.edu.bnu.land.model.Zbgglb;
import cn.edu.bnu.land.model.Zbgmgl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;
import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.service.UserService;
import cn.edu.bnu.land.service.ZbcrService;
import cn.edu.bnu.land.service.ZbjyglService;



@Controller
public class ZbjyglController {
	@Autowired
	private ZbjyglService zbjyglService;
    private Encoder codechange;
    private Tool tool;
    @Autowired
	private Zbpmjj zbjp;
    @Autowired
	private Zbjpssjg ssjg;
    
	@RequestMapping(value = "/addfbzb",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handAddZbgglb(@RequestBody Zbgglb record) throws IOException 
	{ 	
		System.out.println("添加公告列表");
		System.out.println(record.getFbrq());
		return zbjyglService.addZbgglb(record);     	 
	}
	
	@RequestMapping(value = "/getgglb",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handGetZbsh(String start,String limit) throws IOException 
	{ 	
		System.out.println("进入huoqugglb函数");
		return zbjyglService.getGglb(start, limit);      	 
	}	
	
	@RequestMapping(value = "/getgmlb",method=RequestMethod.GET)//申购审核
	@ResponseBody 	
	public Map<String,Object> handGetsgsh(String start,String limit) throws IOException 
	{ 	
		System.out.println("获取购买列表e函数");
		return zbjyglService.getgmlb(start, limit);      	 
	}
	
	@RequestMapping(value = "/getbzjsh",method=RequestMethod.GET)//申购审核
	@ResponseBody 	
	public Map<String,Object> handGetbzjsh(String zbpcbh,String start,String limit) throws IOException 
	{ 	
		System.out.println("进入shengou shenhe函数");
		return zbjyglService.getbzjsh(zbpcbh,start, limit);      	 
	}
	
	@RequestMapping(value = "/sgshtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handsgshtg(@RequestBody Zbgmgl record) throws IOException 
	{ 	
		System.out.println("申购审核通过");
		//System.out.println(record.getFbrq());
		return zbjyglService.updateSgtg(record);     	 
	}
	
	@RequestMapping(value = "/sgshbtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handsgshbtg(@RequestBody Zbgmgl record) throws IOException 
	{ 	
		System.out.println("申购审核bu通过");
		//System.out.println(record.getFbrq());
		return zbjyglService.updateSgbtg(record);   	 
	}
	
	@RequestMapping(value = "/bzjshtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handbzjshtg(@RequestBody Zbgmgl record) throws IOException, ParseException 
	{ 	
		System.out.println(" 保证金审核通过");
		//System.out.println(record.getFbrq());
		zbjyglService.addToJg(record);
		return zbjyglService.updateBzjtg(record);     	 
	}
	
	@RequestMapping(value = "/bzjshbtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handbzjshbtg(@RequestBody Zbgmgl record) throws IOException 
	{ 	
		System.out.println("申购审核bu通过");
		//System.out.println(record.getFbrq());
		return zbjyglService.updateBzjbtg(record);   	 
	}
	
	@RequestMapping(value = "/updatejpresult",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handleJpResult(@RequestBody Zbgglb record) throws IOException, ParseException 
	{ 	
		System.out.println("更新购买列表竞价结果");
		//System.out.println(zbpcbh);			
		return zbjyglService.updateWb(record.getZbpcbh());   	 
	}
			
	@RequestMapping(value = "/updateToWb",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> wqToWb(@RequestParam("zbpcbh") String zbpcbh) throws IOException, ParseException 
	{ 	
		System.out.println("同意合同并进入网备阶段");
		//System.out.println(zbpcbh);			
		return zbjyglService.updateWb(zbpcbh);   	 
	}
	
	@RequestMapping(value = "/scdp",method=RequestMethod.POST)
		@ResponseBody 	
		public Map<String,Object> generateHt(@RequestBody Zbgmgl dp) throws IOException 
		{ 	
			Map<String, Object> model = new TreeMap<String, Object>();
			System.out.println("开始生成地票");
			String dpwz=zbjyglService.updateDp(dp);
			
			zbjyglService.updateGmWb(dp.getZbpcbh(),dp.getUserid(),dpwz);
			model.put("dpwz",dpwz);
			model.put("success",true);			
			return model;
		}
}
