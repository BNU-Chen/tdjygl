package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.common.SpringContextHolder;
import cn.edu.bnu.land.model.Zbcrsh;
import cn.edu.bnu.land.model.Zbgglb;
import cn.edu.bnu.land.model.Zbgmgl;
import cn.edu.bnu.land.model.Zbjpcjjl;
import cn.edu.bnu.land.model.Zbjpssjg;
import cn.edu.bnu.land.model.Zbpmjj;
import cn.edu.bnu.land.common.Tool;
import cn.edu.bnu.land.service.ZbcrClicentService;
import cn.edu.bnu.land.service.ZbcrService;

@Controller
public class ZbcrClientController {
	@Autowired
	private ZbcrClicentService zbcrClicentService;
	
	@Autowired
	private Zbjpcjjl cjjl;	
	@Autowired
	private Zbjpssjg ssjg;
	@Autowired
	private Zbpmjj zbjp;
	
    private Encoder codechange;
    //private Tool tool;
	@RequestMapping(value = "/getfkxmxx",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handGetFkxx(String fkxmbh) throws IOException 
	{ 	
		System.out.println("进入获取指标信息函数");
		System.out.println(fkxmbh);
		if(fkxmbh==null||fkxmbh=="")
		{
			    Map<String, Object> model = new TreeMap<String, Object>();
				model.put("success",false);
				//model.put("data", zbcr);
				model.put("msg", "项目不存在");
				return model;
		}
		else
			
		return zbcrClicentService.updateFkxmInfo(fkxmbh);	      	 
	}         
	
	@RequestMapping(value = "/updateqspz",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handQspz(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("进入提交权属凭证函数");
		String fkxmbh=request.getParameter("fkxmbh");
		System.out.println("fkxmbh:"+fkxmbh);
		
		String userid=Tool.getUserDetail().getUsername();
		System.out.println("userid:"+userid);
		if(fkxmbh==null)
		{
			fkxmbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/sellzm/qszm/"+userid, "scqspz", fkxmbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	@RequestMapping(value = "/addzbcrxx",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handAddZbcrxx(@RequestBody Zbcrsh[] records) throws IOException 
	{ 	
		System.out.println("进入获取指标出让函数");
				   
		return zbcrClicentService.addZbxx(records);		 
	} 
	
	@RequestMapping(value = "/getwdcrzb",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handGetZbsh(String start,String limit) throws IOException 
	{ 	
		System.out.println("进入获取我的出让指标函数");
		return zbcrClicentService.getWdcrzb(start, limit);      	 
	}
	
//	@RequestMapping(value = "/getgglb",method=RequestMethod.GET)
//	@ResponseBody 	
//	public Map<String,Object> handGetgglb(String start,String limit) throws IOException 
//	{ 	
//		System.out.println("进入huoqugglb函数");
//		return zbcrClicentService.getGglb(start, limit);      	 
//	}
	
	@RequestMapping(value = "/addgmsq",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handAddGmsq(@RequestBody Zbgmgl record) throws IOException 
	{ 	
		System.out.println("添加公告列表");
		//System.out.println(record.getFbrq());
		return zbcrClicentService.addWdgmsq(record);     	 
	}
	
	@RequestMapping(value = "/getwdsgzb",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handGetwdsgzb(String start,String limit) throws IOException 
	{ 	
		System.out.println("进入获取我的申请列表函数");
		return zbcrClicentService.getWdsgzb(start, limit);      	 
	}
	
	@RequestMapping(value = "/tjdd",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handTjdd(@RequestBody Zbgmgl record) throws IOException 
	{ 	
		System.out.println("提交订单");
		String userid=Tool.getUserDetail().getUsername();
		//System.out.println(record.getFbrq());
		record.setUserid(userid);//要获取用户名
		return zbcrClicentService.addtjdd(record);   	 
	}
	
	@RequestMapping(value = "/scyyzz",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handScyyzz(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("指标购买审核凭证函数");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("zbpcbh");
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request,"upload/buyzm/yyzz/"+userid,"yyzz", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	@RequestMapping(value = "/scnspz",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handScnspz(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("指标购买审核凭证函数");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("zbpcbh");
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/buyzm/nspz/"+userid,"nspz", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	@RequestMapping(value = "/sczzdm",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handSczzdm(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("指标购买审核凭证函数");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("zbpcbh");
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/buyzm/zzdm/"+userid,"zzdm", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	@RequestMapping(value = "/scsfzm",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handScsfzm(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("指标购买审核凭证函数");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("zbpcbh");
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/buyzm/sfzm/"+userid,"sfzm", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	@RequestMapping(value = "/pzscsh",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handTjdd(@RequestParam("bh") String zbpcbh) throws IOException 
	{ 	
		System.out.println("凭证提交审核回馈");
		//System.out.println(record.getFbrq());
		return zbcrClicentService.updateshzt(zbpcbh);  	 
	}
	
	@RequestMapping(value = "/scbzj",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handScbzj(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("上传保证金");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("zbpcbh");
		System.out.println(userid);
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/buyzm/deposit/"+userid,"sczfpz", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
	
	
	@RequestMapping(value = "/bzjsh",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handTjbzj(@RequestParam("bh") String zbpcbh) throws IOException 
	{ 	
		System.out.println("凭证提交审核回馈");
		//System.out.println(record.getFbrq());
		return zbcrClicentService.updatebzjzt(zbpcbh);  	 
	}
	
//	@RequestMapping(value = "/getjpxx",method=RequestMethod.POST)
//	@ResponseBody 	
//	public Map<String,Object> handGetZbsh(String zbpcbh) throws IOException 
//	{ 	
//		System.out.println("获取竞拍信息函数");
//		return   zbcrClicentService.getJpxx(zbpcbh) ;   	 
//	}	
	
	@RequestMapping(value = "/addjpcj",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> addJpcj(@RequestBody Zbjpcjjl cj ) throws IOException 
	{ 	
		System.out.println("凭证提交审核回馈");
		//System.out.println(record.getFbrq());
		zbcrClicentService.updateSsjg(cj);
		zbcrClicentService.updateSfdb(cj);
		return zbcrClicentService.updateJpcj(cj);  	 
	}
	
//	@RequestMapping(value = "/getcjjl",method=RequestMethod.GET)
//	@ResponseBody 	
//	public Map<String,Object> handGetCjjl(String zbpcbh,String start,String limit) throws IOException 
//	{ 	
//		System.out.println("获取竞拍信息函数");
//		return   zbcrClicentService.getCjjl(zbpcbh,start,limit);   	 
//	}	
	
//	@RequestMapping(value = "/getssjg",method=RequestMethod.GET)
//	@ResponseBody 	
//	public Map<String,Object> handGetSsjg(String zbpcbh) throws IOException 
//	{ 	
//		System.out.println("获取竞拍信息函数");
//		return   zbcrClicentService.getSsjg(zbpcbh);  	 
//	}
	
	@RequestMapping(value = "/dyht",method=RequestMethod.GET)
	@ResponseBody 	
	public Map<String,Object> handDyht(@RequestParam("bh") String zbpcbh) throws IOException 
	{ 	
		System.out.println("打印合同");
		
		return zbcrClicentService.updateGmwb(zbpcbh);  	 
	}
	
	@RequestMapping(value = "/sczfpz",method=RequestMethod.POST)
	@ResponseBody 	
	public Map<String,Object> handSczfpz(HttpServletRequest request) throws IOException 
	{ 	
		System.out.println("上传付款凭证");
		String userid=Tool.getUserDetail().getUsername();
		String zbpcbh=request.getParameter("wbzbpcbh");
		//System.out.println(zbpcbh);
		if(zbpcbh==null)
		{
			zbpcbh="defaut";
		}
		String path=null;
		try {
			path = Tool.fileUpload(request, "upload/buyzm/fkpz/"+userid,"scfkpz", zbpcbh,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zbcrClicentService.updatefkpzWz(zbpcbh);
		
		 Map<String,Object> model = new HashMap<String, Object>();	       	  
	     model.put("success",true);  
	     model.put("msg", path);   
	     return  (model);      	 
	}
}
