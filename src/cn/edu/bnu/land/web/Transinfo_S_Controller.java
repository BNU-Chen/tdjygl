package cn.edu.bnu.land.web;

import java.io.IOException;
import java.sql.SQLException;
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
import cn.edu.bnu.land.model.Transinfo;
import cn.edu.bnu.land.model.TransinfoHome;
import cn.edu.bnu.land.service.Transinfo_S_Service;



@Controller
public class Transinfo_S_Controller {
    private Transinfo_S_Service transinfo_s_Service;


    @Autowired
    public Transinfo_S_Controller(Transinfo_S_Service transinfo_s_Service) {
        this.transinfo_s_Service = transinfo_s_Service;
    }
	@RequestMapping(value = "/find_transinfo") 
	@ResponseBody 	

	
	public Map<String,Object> handle(@RequestParam("start") String start,@RequestParam("limit") String limit,
			@RequestParam("outSearch") String outSearch) throws IOException, ClassNotFoundException, SQLException 
	{ 
		//System.out.println("outArea="+outArea);
		outSearch = Encoder.encode(outSearch);
		System.out.println("outSearch="+outSearch);
		
		//System.out.println("outSearch2="+outSearch2);
		Map<String,Object> myList= this.transinfo_s_Service.findtransinfo_S(start, limit, outSearch);

        return  (myList);
		
	} 
	
}
