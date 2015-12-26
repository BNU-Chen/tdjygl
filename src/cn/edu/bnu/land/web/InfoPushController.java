package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.service.InfoPushService;
import cn.edu.bnu.land.service.UserService;
@Controller
public class InfoPushController {
	InfoPushService infoPushService;
    @Autowired
    public InfoPushController(InfoPushService sendMailService) {
        this.infoPushService = sendMailService;
    }
	@RequestMapping(value = "/setMailPushTrue")
	@ResponseBody
	public Map<String, Object> setTrue(@RequestParam("ids") String ids,@RequestParam("userAdresses") String userAdresses,@RequestParam("chanelid") String chanelid) throws MessagingException, IOException{						
			Map<String, Object> myMapResult = new TreeMap<String, Object>();
			myMapResult.put("success", String.valueOf(infoPushService.update_sendMail(ids, userAdresses, chanelid)));
            return 	myMapResult;		
	}
	
	@RequestMapping(value = "/setSMSPushTrue")
	@ResponseBody
	public Map<String, Object> setSMSTrue(@RequestParam("ids") String ids,@RequestParam("userPhones") String userPhones,@RequestParam("chanelid") String chanelid) throws MessagingException, IOException{		
 			Map<String, Object> myMapResult = new TreeMap<String, Object>();
			myMapResult.put("success", String.valueOf(infoPushService.createSendSMS(ids, userPhones, chanelid)));
            return 	myMapResult;
	}
}
