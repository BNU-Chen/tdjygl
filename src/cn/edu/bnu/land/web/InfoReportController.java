package cn.edu.bnu.land.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.edu.bnu.land.model.InfoReport;
import cn.edu.bnu.land.service.InfoReportService;



@Controller
public class InfoReportController{
	private InfoReportService infoReportService;
	@Autowired
	public InfoReportController(InfoReportService infoReportService) {
		this.infoReportService = infoReportService;
	}
	
	@RequestMapping(value = "/get_infoReportList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getInfoReportList(@RequestParam("start") String start,
			@RequestParam("limit") String limit) throws IOException {

		System.out.println("time to get_infoReportList");
		// System.out.println(start);
		// System.out.println(limit);
		// System.out.println();
		Map<String, Object> myInfoReportList = this.infoReportService.getInfoReportList(start, limit);
		System.out.println(myInfoReportList.get("total"));
		return (myInfoReportList);

	}
	

	@RequestMapping(value = "/add_infoReport")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public Map<String, Object> addInfoReport(@RequestBody InfoReport infoReport) throws IOException 
	{ 	
		System.out.println(infoReport.getReportReplycontent());
		Date now = new Date(); 
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		System.out.println("currentTime:"+now);
		infoReport.setReportTime(now);
		this.infoReportService.addReport(infoReport);
		Map<String, Object> infoReportResults = new HashMap<String, Object>();
		infoReportResults.put("success", true);
		infoReportResults.put("msg","successfully saved!");

		return infoReportResults;
		 
	} 
	
	
}