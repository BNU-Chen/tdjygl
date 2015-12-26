package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;











import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.SpringContextHolder;
import cn.edu.bnu.land.model.User;
import cn.edu.bnu.land.model.UserHome;
import cn.edu.bnu.land.service.UserService;


@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
	@RequestMapping(value = "/add_user") //,method=RequestMethod.POST
	@ResponseBody 	
	public Map<String,Object> handle(@RequestBody User user) throws IOException 
	{ 	
		System.out.println("Username From Client : "+user.getUsername()); 
		this.userService.addUser(user);		
		Map<String,Object> model = new HashMap<String, Object>();   		  
        model.put("success",true);  
        model.put("msg", user.getUsername()+",successfully saved");   
        return  (model);
		 
	} 
	@RequestMapping(value = "/isAdmin") //,method=RequestMethod.POST
	@ResponseBody 	
	public String isAdmin() throws IOException 
	{ 	
		UserDetails userDetails = null;
		boolean isAdmin=false;
		// 取得当前用户登陆的信息
		Object obj =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof UserDetails) {
			userDetails =(UserDetails)obj;
			isAdmin=userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
			//GrantedAuthority ga=null;
			System.out.println(".............................*****************************"+userDetails.getAuthorities()+isAdmin);
		} 
		
		
		           
        return  isAdmin?"ok":"no";
		 
	} 
}
