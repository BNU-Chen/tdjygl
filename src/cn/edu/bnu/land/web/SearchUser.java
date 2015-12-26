package cn.edu.bnu.land.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.edu.bnu.land.service.UserService;


@Controller
public class SearchUser {
	private UserService userService;
		
    @Autowired
    public SearchUser(UserService userService) 
    {
        this.userService = userService;        
    }
    
	@RequestMapping(value = "/searchUser") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField,
			@RequestParam("dateType") String dateType,
			@RequestParam("searchDate") String searchDate) throws IOException 
	{ 			
	Map<String,Object> myList=this.userService.selectSyncPushTb(start,limit,searchField,dateType,searchDate);
	
	return  (myList);	 
	} 
	
}
