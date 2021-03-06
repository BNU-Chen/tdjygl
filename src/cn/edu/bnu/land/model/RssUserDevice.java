package cn.edu.bnu.land.model;

// Generated 2014-1-20 10:50:32 by Hibernate Tools 4.0.0

/**
 * RssUserDevice generated by hbm2java
 */
public class RssUserDevice implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private String orderId;
	private String orderCode;

	public RssUserDevice() {
	}

	public RssUserDevice(String username) {
		this.username = username;
	}

	public RssUserDevice(String username, String password, String orderId,
			String orderCode) {
		this.username = username;
		this.password = password;
		this.orderId = orderId;
		this.orderCode = orderCode;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
