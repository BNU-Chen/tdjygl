package cn.edu.bnu.land.model;

// Generated 2014-5-18 23:58:44 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * Zbjyba generated by hbm2java
 */
public class Zbjyba implements java.io.Serializable {

	private Integer id;
	private String userid;
	private String zbpcbh;
	private String zbbh;
	private Float dwed;
	private Integer gmsl;
	private Float cjdj;
	private Float cjzj;
	private String htbh;
	private String wqbh;
	private String babh;
	private Date barq;
	private String bz;
	private String qt;

	public Zbjyba() {
	}

	public Zbjyba(String userid, String zbpcbh, String zbbh, Float dwed,
			Integer gmsl, Float cjdj, Float cjzj, String htbh, String wqbh,
			String babh, Date barq, String bz, String qt) {
		this.userid = userid;
		this.zbpcbh = zbpcbh;
		this.zbbh = zbbh;
		this.dwed = dwed;
		this.gmsl = gmsl;
		this.cjdj = cjdj;
		this.cjzj = cjzj;
		this.htbh = htbh;
		this.wqbh = wqbh;
		this.babh = babh;
		this.barq = barq;
		this.bz = bz;
		this.qt = qt;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getZbpcbh() {
		return this.zbpcbh;
	}

	public void setZbpcbh(String zbpcbh) {
		this.zbpcbh = zbpcbh;
	}

	public String getZbbh() {
		return this.zbbh;
	}

	public void setZbbh(String zbbh) {
		this.zbbh = zbbh;
	}

	public Float getDwed() {
		return this.dwed;
	}

	public void setDwed(Float dwed) {
		this.dwed = dwed;
	}

	public Integer getGmsl() {
		return this.gmsl;
	}

	public void setGmsl(Integer gmsl) {
		this.gmsl = gmsl;
	}

	public Float getCjdj() {
		return this.cjdj;
	}

	public void setCjdj(Float cjdj) {
		this.cjdj = cjdj;
	}

	public Float getCjzj() {
		return this.cjzj;
	}

	public void setCjzj(Float cjzj) {
		this.cjzj = cjzj;
	}

	public String getHtbh() {
		return this.htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getWqbh() {
		return this.wqbh;
	}

	public void setWqbh(String wqbh) {
		this.wqbh = wqbh;
	}

	public String getBabh() {
		return this.babh;
	}

	public void setBabh(String babh) {
		this.babh = babh;
	}

	public Date getBarq() {
		return this.barq;
	}

	public void setBarq(Date barq) {
		this.barq = barq;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getQt() {
		return this.qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

}
