package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.model.InfoVoteoption;
import cn.edu.bnu.land.service.InfoVoteService;


@Controller
public class InfoVoteController{
	private InfoVoteService infoVoteService;
	@Autowired
	public InfoVoteController(InfoVoteService infoVoteService) {
		this.infoVoteService = infoVoteService;
	}
	
	
	
	/*
	 * 投票结果显示
	 * 方法：get_voteResult
	 * 返回参数：投票选项类型List<InfoVoteoption> myVoteResult
	 * 20130903 @LF
	 */
	@RequestMapping(value = "/get_voteResult")//,method=RequestMethod.POST) 
	@ResponseBody 	
	public List<InfoVoteoption> handle() throws IOException 
	{ 			
		System.out.println("time to getVoteResult"); 
		List<InfoVoteoption> myVoteResult = this.infoVoteService.getVoteResult();
		return (myVoteResult);

		 
	} 
	
	
}