package cn.edu.bnu.land.model;

// Generated 2014-5-6 16:10:04 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * BenefitAlarmshou generated by hbm2java
 */
public class BenefitAlarmshou implements java.io.Serializable {

	private Integer xh;
	private String yclx;
	private String ycxq;
	private String jyqy;
	private String ycbh;
	private String htmc;
	private String xmbh;
	private String xmmc;
	private Date cxsj;

	public BenefitAlarmshou() {
	}

	public BenefitAlarmshou(String yclx, String ycxq, String jyqy, String ycbh,
			String htmc, String xmbh, String xmmc, Date cxsj) {
		this.yclx = yclx;
		this.ycxq = ycxq;
		this.jyqy = jyqy;
		this.ycbh = ycbh;
		this.htmc = htmc;
		this.xmbh = xmbh;
		this.xmmc = xmmc;
		this.cxsj = cxsj;
	}

	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	public String getYclx() {
		return this.yclx;
	}

	public void setYclx(String yclx) {
		this.yclx = yclx;
	}

	public String getYcxq() {
		return this.ycxq;
	}

	public void setYcxq(String ycxq) {
		this.ycxq = ycxq;
	}

	public String getJyqy() {
		return this.jyqy;
	}

	public void setJyqy(String jyqy) {
		this.jyqy = jyqy;
	}

	public String getYcbh() {
		return this.ycbh;
	}

	public void setYcbh(String ycbh) {
		this.ycbh = ycbh;
	}

	public String getHtmc() {
		return this.htmc;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public String getXmbh() {
		return this.xmbh;
	}

	public void setXmbh(String xmbh) {
		this.xmbh = xmbh;
	}

	public String getXmmc() {
		return this.xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public Date getCxsj() {
		return this.cxsj;
	}

	public void setCxsj(Date cxsj) {
		this.cxsj = cxsj;
	}

}
