package cn.edu.bnu.land.web;
//Generated 2013-8-20 17:21:01 by Hibernate Tools 4.0.0
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.SpringContextHolder;
import cn.edu.bnu.land.model.ProjectAll;
import cn.edu.bnu.land.model.ProjectAllHome;
import cn.edu.bnu.land.service.ProdbService;

/**
* Home object for domain model class Prodb.
* @see dao.Prodb
* @author Hibernate Tools
*/

@Controller
public class SchemeToShowLoad {
	private ProdbService prodbService;
	@Autowired
	public SchemeToShowLoad(ProdbService prodbService) 
	{
		this.prodbService  = prodbService;
	}
	@RequestMapping (value = "/SchemeToShowLoad") 
	@ResponseBody 
	public Map<String,Object> handle1(@RequestParam("start") String start,
			@RequestParam ("limit") String  limit)throws IOException
			{
		System.out.println("Land From Client : "); 
		Map<String,Object> myList= this.prodbService.SchemeToShowLoad(start,limit);
		return (myList);
			}
}