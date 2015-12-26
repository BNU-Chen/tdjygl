package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.model.PublishDeal;
import cn.edu.bnu.land.service.PubDealInfoService;
import cn.edu.bnu.land.service.UserService;

@Controller
public class PubDealInfoController {
	private PubDealInfoService pubDealInfoService;
    @Autowired
    public PubDealInfoController(PubDealInfoService pubDealInfoService) {
        this.pubDealInfoService = pubDealInfoService;
    }
	@RequestMapping(value = "/searchPubDealInfo") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> searchPubDealInfo_handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField,	
			@RequestParam("searchDate") String searchDate) throws IOException 
	{ 			
	System.out.println("searchPubDealInfo"); 
	Map<String,Object> myList=this.pubDealInfoService.selectPublishDealTb(start, limit, searchField, searchDate);
    return  (myList);	 
	} 
	
	@RequestMapping(value = "/add_PubDealInfo") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> add_PubDealInfo_handle2(@RequestBody PublishDeal publishDeal) throws IOException 
	{ 
	   System.out.println("add_PubDealInfo");
	   System.out.println(publishDeal.getTitle());
	   this.pubDealInfoService.addPublishDeal(publishDeal);    
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", publishDeal.getTitle()+",保存成功！");   
       return  (model);
	} 
	
	
	@RequestMapping(value = "/update_PubDealInfo") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> update_PubDealInfo_handle3(@RequestBody PublishDeal publishDeal) throws IOException 
	{     
	    this.pubDealInfoService.updatePublishDeal(publishDeal);    
	    Map<String,Object> model = new HashMap<String, Object>();   		  
	       model.put("success",true);  
	       model.put("msg", publishDeal.getTitle()+",修改成功！");   
	       return  (model);
	} 
	
	@RequestMapping(value = "/del_PubDealInfo") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void del_PubDealInfo_handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println(ids);
	    this.pubDealInfoService.deletePublishDeal(ids);    
	} 
	
	
}
