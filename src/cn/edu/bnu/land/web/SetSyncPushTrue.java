package cn.edu.bnu.land.web;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.service.UserService;

@Controller
public class SetSyncPushTrue {
	private UserService userService;
    @Autowired
    public SetSyncPushTrue(UserService userService) {
        this.userService = userService;
    }
	@RequestMapping(value = "/setSyncPushTrue")
	@ResponseBody
	public void setTrue(@RequestParam("ids") String ids){		
		System.out.println("ids value: "+ids); 
		this.userService.updateSyncPushTrue(ids);
	}
	
}
