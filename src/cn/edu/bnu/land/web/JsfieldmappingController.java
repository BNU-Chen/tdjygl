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
import cn.edu.bnu.land.model.Jsdatafield;
import cn.edu.bnu.land.model.Jsdatafield2;
import cn.edu.bnu.land.model.Jsfieldmapping;
import cn.edu.bnu.land.service.JsdatafieldService;
import cn.edu.bnu.land.service.JsfieldmappingService;



@Controller
public class JsfieldmappingController {
    private JsfieldmappingService jsfieldmappingService;


    @Autowired
    public JsfieldmappingController(JsfieldmappingService jsfieldmappingService) {
        this.jsfieldmappingService = jsfieldmappingService;
    }
	@RequestMapping(value = "/look_fieldmapping") 
	@ResponseBody 	
	
	public Map<String,Object> handle(@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException 
	{ 
		
		System.out.println("查询字段映射表");

		Map<String,Object>  myList= this.jsfieldmappingService.lookFieldmapping(start, limit);
		
		
        return  (myList);
		
	} 
	
	
	@RequestMapping(value = "/del_fieldmapping") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle4(@RequestParam("ids") String ids) throws IOException 
	{ 
	    System.out.println("ids+jsdadafield="+ids);
	    this.jsfieldmappingService.deletefieldmapping(ids);   
	} 
	
	
	
	@RequestMapping(value = "/add_fieldmapping") //,method=RequestMethod.POST	
	@ResponseBody 	
	public void handle2(@RequestParam("ids1") String ids1,@RequestParam("ids2") String ids2) throws IOException 
	{ 
	    System.out.println("ids1+fieldmapping="+ids1);
	    System.out.println("ids2+fieldmapping="+ids2);
	    this.jsfieldmappingService.adddfieldmapping(ids1, ids2);   
	} 
//	public Map<String,Object> handle2(@RequestBody Jsdatafield jsdatafield,@RequestBody Jsdatafield2 jsdatafield2,
//			@RequestBody Jsfieldmapping jsfieldmapping) throws IOException{
//		   System.out.println("add_jsfieldmapping");
//		   //System.out.println(Jsdatafield2);
//		   this.jsfieldmappingService.adddfieldmapping(jsdatafield, jsdatafield2, jsfieldmapping);
//		   Map<String,Object> model = new HashMap<String, Object>();   		  
//	       model.put("success",true);  
//	       model.put("msg", jsdatafield.getWebdataField()+",保存成功！");   
//	       return  (model);
//	}

}
