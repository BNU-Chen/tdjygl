package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.service.InfoCommentService;



@Controller
public class InfoCommentController{
	private InfoCommentService infoCommentService;
	@Autowired
	public InfoCommentController(InfoCommentService infoVoteService) {
		this.infoCommentService = infoCommentService;
	}
	
//
//	@RequestMapping(value = "/")//,method=RequestMethod.POST) 
//	@ResponseBody 	
//	public void handle() throws IOException 
//	{ 			
//		
//
//		 
//	} 
	
	
}