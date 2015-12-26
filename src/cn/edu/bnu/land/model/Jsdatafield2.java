package cn.edu.bnu.land.model;

import java.util.Date;

public class Jsdatafield2 implements java.io.Serializable{
	private String localdataField;
	public String getLocaldataField() {
		return localdataField;
	}

	public void setLocaldataField(String localdataField) {
		this.localdataField = localdataField;
	}

	private Integer id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Jsdatafield2() {
	}
	
	public Jsdatafield2(String localdataField) {
		this.localdataField = localdataField;
	
	}

	


}
