package cn.edu.bnu.land.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.ProjectAll;
import cn.edu.bnu.land.model.ProjectAllHome;

import java.util.List;
import java.util.Map;
@Service
public class ProdbService {
	private ProjectAllHome projectAllHome;
	@Autowired
	public void setProdbHome(ProjectAllHome projectAllHome){
		this.projectAllHome=projectAllHome;
	}

	public Map<String,Object> SelectPro(String start,String limit,String searchField){
		System.out.print("ServieceBefore"); 
		return this.projectAllHome.selectTb(start,limit,searchField);
	}
	
	public Map<String,Object> SchemeToShowLoad(String start,String limit){
		System.out.print("ServieceBefore"); 
		return this.projectAllHome.SchemeToShowLoad(start,limit);
	}
	
	public Map<String,Object> SchemeToShowButton(String start,String limit,String searchField){
		System.out.print("ServieceBefore"); 
		return this.projectAllHome.SchemeToShowButton(start,limit,searchField);
	}
	//	//公示项目
	//	public List<ProjectAll> ProjectShow(){
	//		System.out.print("ServieceBefore"); 
	//		return this.projectAllHome.ProjectShow();
	//	}
	//
	//	//存档项目
	//	public List<ProjectAll> Project_record(){
	//		System.out.print("ServieceBefore"); 
	//		return this.projectAllHome.selectProRecord();
	//	}
}
