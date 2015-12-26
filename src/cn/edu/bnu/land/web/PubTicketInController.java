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
public class PubTicketInController {
	
	private PublishService publishService;
    @Autowired
    public PubTicketInController(PublishService publishService) {
        this.publishService = publishService;
    }
	@RequestMapping(value = "/searchPubTicketIn") //,method=RequestMethod.POST	
	@ResponseBody 	
	public   Map<String,Object> searchPubTicketIn_handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField,	
			@RequestParam("searchDate") String searchDate) throws IOException 
	{ 			
	System.out.println("searchPubDealInfo"); 
	searchField=Encoder.encode(searchField);
	Map<String,Object> myList=this.publishService.selectPubTicketInTb(start, limit, searchField, searchDate,4);
    return  (myList);	 
	} 
	
	@RequestMapping(value = "/add_PubTicketIn") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> add_PubTicketIn_handle2(@RequestBody InfoArticle infoArticle) throws IOException 
	{ 
	   System.out.println("add_PubTicketIn");
	   System.out.println(infoArticle.getArticleName());
	   this.publishService.addPubTicketIn(infoArticle,4);    
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", infoArticle.getArticleName()+",保存成功！");   
       return  (model);
	} 
	
	
	@RequestMapping(value = "/update_PubTicketIn") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> update_PubTicketIn_handle3(@RequestBody InfoArticle infoArticle) throws IOException 
	{     
	    this.publishService.updatePubTicketIn(infoArticle,4);
	    Map<String,Object> model = new HashMap<String, Object>();   		  
	       model.put("success",true);  
	       model.put("msg", infoArticle.getArticleName()+",修改成功！");   
	       return  (model);
	} 
	
	@RequestMapping(value = "/del_PubTicketIn") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void del_PubTicketIn_handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println(ids);
	    this.publishService.deletePubTicketIn(ids);    
	} 
	
}
