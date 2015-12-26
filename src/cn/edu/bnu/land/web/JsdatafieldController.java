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
import cn.edu.bnu.land.model.Jsdatafield;
import cn.edu.bnu.land.model.Jsdatafield2;
import cn.edu.bnu.land.service.JsdatafieldService;



@Controller
public class JsdatafieldController {
    private JsdatafieldService jsdatafieldService;


    @Autowired
    public JsdatafieldController(JsdatafieldService jsdatafieldService) {
        this.jsdatafieldService = jsdatafieldService;
    }
	@RequestMapping(value = "/look_datafield") 
	@ResponseBody 	
	
	public Map<String,Object> handle(@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException 
	{ 
		
		System.out.println("查询爬取字段列表");

		Map<String,Object>  myList= this.jsdatafieldService.lookdataField(start, limit);
		
		
        return  (myList);
		
	} 
	
	@RequestMapping(value = "/add_jsdadafield") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> handle2(@RequestBody Jsdatafield jsdatafield) throws IOException 
	{ 
	   System.out.println("add_jsdadafield");
	   //System.out.println(Jsdatafield2);
	   this.jsdatafieldService.addwebdata(jsdatafield); 
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", jsdatafield.getWebdataField()+",保存成功！");   
       return  (model);
	} 
	
	
	@RequestMapping(value = "/del_jsdadafield") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println("ids+jsdadafield="+ids);
	    this.jsdatafieldService.deleteJsdatafield(ids);   
	} 
	
}
