package cn.edu.bnu.land.web;

import java.io.IOException;
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
import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.service.UserService;
import cn.edu.bnu.land.service.ZbcrService;



@Controller
public class ZbcrController {
	@Autowired
	private ZbcrService zbcrService;
    private Encoder codechange;
    private Tool tool;
	
	@RequestMapping(value = "/getzbcrsh",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handGetZbsh(String start,String limit) throws IOException 
	{ 	
		System.out.println("进入提交权属凭证函数");
		return zbcrService.getZbcrSh(start, limit);      	 
	}
	
	
	@RequestMapping(value = "/updateshtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handupdatTg(@RequestBody Zbcrsh record) throws IOException 
	{ 	
		System.out.println("审核通过");
		System.out.println(record.getId());
		//Map<String, Object> model = new TreeMap<String, Object>();
		return  zbcrService.updateShtg(record);     	 
	}
	
	@RequestMapping(value = "/updateshbtg",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handupdatBtg(@RequestBody Zbcrsh record) throws IOException 
	{ 	
		System.out.println("审核通过");
		System.out.println(record.getQspzwz());
		//Map<String, Object> model = new TreeMap<String, Object>();
		return  zbcrService.updateShbtg(record);     	 
	}
	
}
