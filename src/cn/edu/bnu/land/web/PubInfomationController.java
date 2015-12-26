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

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.model.InfoArticle;
import cn.edu.bnu.land.service.PublishService;

@Controller
public class PubInfomationController {
	private PublishService publishService;
    @Autowired
    public PubInfomationController(PublishService publishService) {
        this.publishService = publishService;
    }
	@RequestMapping(value = "/search_PubInfomation") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> search_PubInfomation_handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField,	
			@RequestParam("searchDate") String searchDate) throws IOException 
	{ 			
	System.out.println("searchPubInfomation"); 
	searchField=Encoder.encode(searchField);
	Map<String,Object> myList=this.publishService.selectPubTicketInTb(start, limit, searchField, searchDate,2);
    return  (myList);	 
	} 
	
	@RequestMapping(value = "/add_PubInfomation") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> add_PubInfomation_handle2(@RequestBody InfoArticle infoArticle) throws IOException 
	{ 
	   System.out.println("add_PubInfomation");
	   System.out.println(infoArticle.getArticleName());
	   this.publishService.addPubTicketIn(infoArticle,2);    
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", infoArticle.getArticleName()+",保存成功！");   
       return  (model);
	} 
	
	
	@RequestMapping(value = "/update_PubInfomation") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> update_PubInfomation_handle3(@RequestBody InfoArticle infoArticle) throws IOException 
	{     
	    this.publishService.updatePubTicketIn(infoArticle,2);
	    Map<String,Object> model = new HashMap<String, Object>();   		  
	       model.put("success",true);  
	       model.put("msg", infoArticle.getArticleName()+",修改成功！");   
	       return  (model);
	} 
	
	@RequestMapping(value = "/del_PubInfomation") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void del_PubInfomation_handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println(ids);
	    this.publishService.deletePubTicketIn(ids);    
	} 
}
