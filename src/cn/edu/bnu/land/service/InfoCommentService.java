package cn.edu.bnu.land.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.InfoCommentHome;


@Service
public class InfoCommentService{
	private InfoCommentHome infoCommentHome;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Autowired
	public void setInfoCommentHome(InfoCommentHome infoCommentHome){
		this.infoCommentHome = infoCommentHome;
		
	}
	
	
	
	
}