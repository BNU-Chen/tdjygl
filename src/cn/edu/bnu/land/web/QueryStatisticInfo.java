package cn.edu.bnu.land.web;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.bnu.land.common.Encoder;
import cn.edu.bnu.land.service.QueryStatisticInfoService;
import cn.edu.bnu.land.service.UserService;

@Controller
public class QueryStatisticInfo {
	private UserService userService;
	private QueryStatisticInfoService queryService;
	
    @Autowired
    public QueryStatisticInfo(QueryStatisticInfoService queryService) {
        this.queryService = queryService;
    }
//    public QueryStatisticInfo(UserService userService) {
//        this.userService = userService;
//    }

	@RequestMapping(value = "/QueryStatisticInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> handle(
			@RequestParam("searchKeyword") String keyword,
			@RequestParam("start") String start,
			@RequestParam("limit") String limit
			) throws IOException {
		
		keyword = Encoder.encode(keyword);
		Map<String, Object> mySyncpushList = this.queryService.getSyncDataList(keyword,start, limit);
		System.out.println(mySyncpushList.get("total"));
		return (mySyncpushList);
	}
	
	@RequestMapping(value = "/StatisticAuction", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> statisticAuction() throws IOException {
		return this.queryService.statisticAuction();
		
	}
	
	@RequestMapping(value = "/StatisticSupplier", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> statisticSupplier() throws IOException {
		return this.queryService.statisticSupplier();
		
	}
	
	@RequestMapping(value = "/StatisticPrice", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> statisticPrice() throws IOException {
		return this.queryService.statisticPrice();
		
	}
	
	@RequestMapping(value = "/StatisticArea", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> statisticArea() throws IOException {
		return this.queryService.statisticArea();
	}
	
}
