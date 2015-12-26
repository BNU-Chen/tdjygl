package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.service.UserService;

@Controller
public class SearchMailPush {
	private UserService userService;
    @Autowired
    public SearchMailPush(UserService userService) {
        this.userService = userService;
    }
	@RequestMapping(value = "/searchMailPush") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField,
			@RequestParam("dateType") String dateType,
			@RequestParam("searchDate") String searchDate) throws IOException 
	{ 			
	System.out.println("Username From Client : gx  searchMailPush"); 
	Map<String,Object> myList=this.userService.selectMailPushTb(start,limit,searchField,dateType,searchDate);
    return  (myList);	 
	} 
}
