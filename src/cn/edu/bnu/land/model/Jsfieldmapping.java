package cn.edu.bnu.land.model;

public class Jsfieldmapping {
	private String fieldmapping;
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getFieldmapping() {
		return fieldmapping;
	}

	public void setFieldmapping(String fieldmapping) {
		this.fieldmapping = fieldmapping;
	}
	
	public Jsfieldmapping() {
	}
	
	public Jsfieldmapping(String fieldmapping) {
		this.fieldmapping = fieldmapping;
	}

}
