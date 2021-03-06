package cn.edu.bnu.land.model;

// Generated 2014-3-11 16:56:43 by Hibernate Tools 4.0.0

/**
 * Mbsrecmsg generated by hbm2java
 */
public class Mbsrecmsg implements java.io.Serializable {

	private Integer idxNo;
	private String sender;
	private String recipient;
	private Short msgType;
	private String subject;
	private String urls;
	private String recMmsinfo;
	private Short msgStatus;
	private Integer msgSize;
	private String recTime;
	private Short comPort;
	private String refMsgId;
	private String sendOutTime;
	private String comments;

	public Mbsrecmsg() {
	}

	public Mbsrecmsg(String sender, String recipient, Short msgType,
			String subject, String urls, String recMmsinfo, Short msgStatus,
			Integer msgSize, String recTime, Short comPort, String refMsgId,
			String sendOutTime, String comments) {
		this.sender = sender;
		this.recipient = recipient;
		this.msgType = msgType;
		this.subject = subject;
		this.urls = urls;
		this.recMmsinfo = recMmsinfo;
		this.msgStatus = msgStatus;
		this.msgSize = msgSize;
		this.recTime = recTime;
		this.comPort = comPort;
		this.refMsgId = refMsgId;
		this.sendOutTime = sendOutTime;
		this.comments = comments;
	}

	public Integer getIdxNo() {
		return this.idxNo;
	}

	public void setIdxNo(Integer idxNo) {
		this.idxNo = idxNo;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Short getMsgType() {
		return this.msgType;
	}

	public void setMsgType(Short msgType) {
		this.msgType = msgType;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getRecMmsinfo() {
		return this.recMmsinfo;
	}

	public void setRecMmsinfo(String recMmsinfo) {
		this.recMmsinfo = recMmsinfo;
	}

	public Short getMsgStatus() {
		return this.msgStatus;
	}

	public void setMsgStatus(Short msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Integer getMsgSize() {
		return this.msgSize;
	}

	public void setMsgSize(Integer msgSize) {
		this.msgSize = msgSize;
	}

	public String getRecTime() {
		return this.recTime;
	}

	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}

	public Short getComPort() {
		return this.comPort;
	}

	public void setComPort(Short comPort) {
		this.comPort = comPort;
	}

	public String getRefMsgId() {
		return this.refMsgId;
	}

	public void setRefMsgId(String refMsgId) {
		this.refMsgId = refMsgId;
	}

	public String getSendOutTime() {
		return this.sendOutTime;
	}

	public void setSendOutTime(String sendOutTime) {
		this.sendOutTime = sendOutTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
