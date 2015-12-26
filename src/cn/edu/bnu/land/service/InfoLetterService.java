package cn.edu.bnu.land.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.InfoLetterHome;


@Service
public class InfoLetterService{
	private InfoLetterHome infoLetterHome;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Autowired
	public void setInfoLetterHome(InfoLetterHome infoLetterHome){
		this.infoLetterHome = infoLetterHome;
		
	}
	
	
	
	
}