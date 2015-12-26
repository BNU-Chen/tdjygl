package cn.edu.bnu.land.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.service.UserService;

@Controller
public class SearchUsersToChoice {
	private UserService userService;
    @Autowired
    public SearchUsersToChoice(UserService userService) {
        this.userService = userService;
    }
    
	@RequestMapping(value = "/searchUserToChoice") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> handle2(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField
			) throws IOException 
	{ 
	String mysearchField=Encoder.encode(searchField);	
	System.out.println("Username From Client : gx"+" Search Users To Choice .searchField:"+mysearchField); 
	Map<String,Object> myList=this.userService.getSelectUsers(start,limit,mysearchField);
    return  (myList);	 
	} 	
}
