package cn.edu.bnu.land.model;

// Generated 2014-5-17 19:34:45 by Hibernate Tools 4.0.0

/**
 * BenefitCanshutai generated by hbm2java
 */
public class BenefitCanshutai implements java.io.Serializable {

	private int xh;
	private Integer ffcs;
	private Integer zgjg;
	private Integer zdjg;
	private Integer zdcjl;
	private Integer xyz;
	private Integer jb;

	public BenefitCanshutai() {
	}

	public BenefitCanshutai(int xh) {
		this.xh = xh;
	}

	public BenefitCanshutai(int xh, Integer ffcs, Integer zgjg, Integer zdjg,
			Integer zdcjl, Integer xyz, Integer jb) {
		this.xh = xh;
		this.ffcs = ffcs;
		this.zgjg = zgjg;
		this.zdjg = zdjg;
		this.zdcjl = zdcjl;
		this.xyz = xyz;
		this.jb = jb;
	}

	public int getXh() {
		return this.xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}

	public Integer getFfcs() {
		return this.ffcs;
	}

	public void setFfcs(Integer ffcs) {
		this.ffcs = ffcs;
	}

	public Integer getZgjg() {
		return this.zgjg;
	}

	public void setZgjg(Integer zgjg) {
		this.zgjg = zgjg;
	}

	public Integer getZdjg() {
		return this.zdjg;
	}

	public void setZdjg(Integer zdjg) {
		this.zdjg = zdjg;
	}

	public Integer getZdcjl() {
		return this.zdcjl;
	}

	public void setZdcjl(Integer zdcjl) {
		this.zdcjl = zdcjl;
	}

	public Integer getXyz() {
		return this.xyz;
	}

	public void setXyz(Integer xyz) {
		this.xyz = xyz;
	}

	public Integer getJb() {
		return this.jb;
	}

	public void setJb(Integer jb) {
		this.jb = jb;
	}

}