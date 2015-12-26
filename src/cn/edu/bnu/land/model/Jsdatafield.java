package cn.edu.bnu.land.model;

import java.util.Date;

public class Jsdatafield implements java.io.Serializable{
	private String webdataField;
	private String localdataField;
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Jsdatafield() {
	}
	
	public Jsdatafield(String webdataField,String localdataField) {
		this.localdataField = localdataField;
		this.webdataField = webdataField;
	}

	public String getWebdataField() {
		return webdataField;
	}

	public void setWebdataField(String webdataField) {
		this.webdataField = webdataField;
	}

	public String getLocaldataField() {
		return localdataField;
	}

	public void setLocaldataField(String localdataField) {
		this.localdataField = localdataField;
	}
}
