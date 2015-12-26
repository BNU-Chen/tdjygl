package cn.edu.bnu.land.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bnu.land.model.InfoVote;
import cn.edu.bnu.land.model.InfoVoteHome;
import cn.edu.bnu.land.model.InfoVoteoption;
import cn.edu.bnu.land.model.InfoVoteoptionHome;

@Service
public class InfoVoteService{
	private InfoVoteHome infoVoteHome;
	private InfoVoteoptionHome infoVoteoptionHome;
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Autowired
	public void setInfoVoteHome(InfoVoteHome infoVoteHome){
		this.infoVoteHome = infoVoteHome;
		
	}
	@Autowired
	public void setInfoVoteoptionHome(InfoVoteoptionHome infoVoteoptionHome){
		this.infoVoteoptionHome = infoVoteoptionHome;
		
	}
//	public void addInfoArticle(InfoVote infoVote){
//		this.infoVoteHome.save(infoVote);
//	}
//	public Map<String ,Object> getInfoVoteList(String start,String limit) {
//		
//		return this.infoVoteHome.selectInfoVoteList(start,limit);
//	}
	
	
	/*
	 * 投票结果显示
	 * 方法：getVoteResult()
	 * 返回参数：投票选项类型List<InfoVoteoption> result
	 * 20130903 @LF
	 */
	public List<InfoVoteoption> getVoteResult() {
		List<InfoVoteoption> result = null;
		String hql = "from InfoVoteoption as infoVoteoption";
		try{
			org.hibernate.Query query = sessionFactory.getCurrentSession()
					.createQuery(hql);
			result = (List<InfoVoteoption>) query.list();
			for(InfoVoteoption infoVoteoption : result){
				System.out.println(infoVoteoption.getVoptionId());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
}