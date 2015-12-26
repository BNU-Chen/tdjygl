package cn.edu.bnu.land.web;

import java.io.IOException;







import java.util.HashMap;
import java.util.List;
import java.util.Map;













import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.common.SpringContextHolder;
import cn.edu.bnu.land.model.Crawlurl;
import cn.edu.bnu.land.model.CrawlurlHome;
import cn.edu.bnu.land.service.CrawlurlService;



@Controller
public class CrawlurlController {
    private CrawlurlService crawlurlService;


    @Autowired
    public CrawlurlController(CrawlurlService crawlurlService) {
        this.crawlurlService = crawlurlService;
    }
	@RequestMapping(value = "/look_url") 
	@ResponseBody 	
	
	public Map<String,Object> handle(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField) throws IOException 
	{ 
		
		System.out.println("look_url   start:"+start);
		searchField = Encoder.encode(searchField);
		System.out.println("look_url:"+searchField);
		Map<String,Object>  myList= this.crawlurlService.lookurl(start, limit, searchField);
		
		
        return  (myList);
		
	} 
	
	
	@RequestMapping(value = "/look_urlRec") 
	@ResponseBody 	
	
	public Map<String,Object> handle1(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField) throws IOException 
	{ 
		
		System.out.println("查询爬取网站start:"+limit);
		searchField = Encoder.encode(searchField);
		System.out.println("查询爬取网站:"+searchField);
		Map<String,Object>  myList= this.crawlurlService.lookurlRec(start, limit, searchField);
		
		
        return  (myList);
		
	} 
	
	
	@RequestMapping(value = "/add_crawlurl") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> handle2(@RequestBody Crawlurl crawlurl) throws IOException 
	{ 
	   System.out.println("add_crawlurl");
	   System.out.println(crawlurl.getWebname());
	   this.crawlurlService.addCrawlurl(crawlurl);  
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", crawlurl.getWebname()+",保存成功！");   
       return  (model);
	} 
	
	
	@RequestMapping(value = "/update_crawlurl") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> handle3(@RequestBody Crawlurl crawlurl) throws IOException 
	{     
		System.out.println("update_crawlurl");
		System.out.println(crawlurl.getWebname());
		this.crawlurlService.updateCrawlurl(crawlurl);;
	    Map<String,Object> model = new HashMap<String, Object>();   		  
	       model.put("success",true);  
	       model.put("msg", crawlurl.getWebname()+",修改成功！");   
	       return  (model);
	} 
	
	@RequestMapping(value = "/update_urlToRecycle") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle4(@RequestParam("urlIds") String[] urlIds) throws IOException 
	{ 
	    this.crawlurlService.updateurltoRec(urlIds);    
	} 
	
	@RequestMapping(value = "/del_crawlurlRec") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle5(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println("要删除的ids+crawl="+ids);
	    this.crawlurlService.deleteCrawlurl(ids);    
	} 
	
	@RequestMapping(value = "/update_urlFromRec") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle6(@RequestParam("urlRecIds") String[] urlRecIds) throws IOException 
	{ 
	    this.crawlurlService.updateurlfromRec(urlRecIds);;    
	} 
	

	
}
