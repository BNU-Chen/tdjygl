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
import cn.edu.bnu.land.service.Jsdatafield2Service;
import cn.edu.bnu.land.service.JsdatafieldService;



@Controller
public class Jsdatafield2Controller {
    private Jsdatafield2Service jsdatafield2Service;


    @Autowired
    public Jsdatafield2Controller(Jsdatafield2Service jsdatafield2Service) {
        this.jsdatafield2Service = jsdatafield2Service;
    }
	@RequestMapping(value = "/look_datafield2") 
	@ResponseBody 	
	
	public Map<String,Object> handle(@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException 
	{ 
		
		System.out.println("查询本地保存字段列表");

		Map<String,Object>  myList= this.jsdatafield2Service.lookdataField2(start, limit);
		
		
        return  (myList);
		
	} 
	
	@RequestMapping(value = "/add_jsdadafield2") //,method=RequestMethod.POST	
	@ResponseBody 	
	public Map<String,Object> handle2(@RequestBody Jsdatafield2 jsdatafield2) throws IOException 
	{ 
	   System.out.println("add_jsdadafield2");
	   //System.out.println(Jsdatafield2);
	   this.jsdatafield2Service.addlocaldata(jsdatafield2); 
	   Map<String,Object> model = new HashMap<String, Object>();   		  
       model.put("success",true);  
       model.put("msg", jsdatafield2.getLocaldataField()+",保存成功！");   
       return  (model);
	} 
	
	@RequestMapping(value = "/del_jsdadafield2") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println("ids+jsdadafield2="+ids);
	    this.jsdatafield2Service.deleteJsdatafield2(ids);   
	} 

}
