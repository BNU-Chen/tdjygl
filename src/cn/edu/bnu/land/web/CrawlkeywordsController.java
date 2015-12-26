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
import cn.edu.bnu.land.model.Crawlkeywords;
import cn.edu.bnu.land.model.CrawlkeywordsHome;
import cn.edu.bnu.land.service.CrawlkeywordsService;



@Controller
public class CrawlkeywordsController {
    private CrawlkeywordsService crawlkeywordsService;


    @Autowired
    public CrawlkeywordsController(CrawlkeywordsService crawlkeywordsService) {
        this.crawlkeywordsService = crawlkeywordsService;
    }
	@RequestMapping(value = "/look_keywords") 
	@ResponseBody 	
	
	public Map<String,Object> handle(@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			@RequestParam("searchField") String searchField) throws IOException 
	{ 
		
		System.out.println("查询关键词start:"+start);
		searchField = Encoder.encode(searchField);
		System.out.println("查询爬取网站:"+searchField);
		Map<String,Object>  myList= this.crawlkeywordsService.lookkeywords(start, limit, searchField);
		
		
        return  (myList);
		
	} 
	
	
	@RequestMapping(value = "/add_crawlkeywords") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> handle2(@RequestBody Crawlkeywords crawlkeywords) throws IOException 
	{ 
	   System.out.println("add_crawlkeywords");
	   System.out.println(crawlkeywords);
	   this.crawlkeywordsService.addCrawlkeywords(crawlkeywords);  
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", crawlkeywords.getKeywordsname()+",保存成功！");   
       return  (model);
	} 
	
	

	
	@RequestMapping(value = "/del_crawlkeywords") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println("ids+crawlkeywords="+ids);
	    this.crawlkeywordsService.deleteCrawlkeywords(ids);    
	} 
	
}
