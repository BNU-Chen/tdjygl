package cn.edu.bnu.land.model;

// Generated 2014-3-11 16:56:43 by Hibernate Tools 4.0.0

/**
 * Mbsmsgitems generated by hbm2java
 */
public class Mbsmsgitems implements java.io.Serializable {

	private Integer idxNo;
	private String msgId;
	private Short itemIndex;
	private String contentType;
	private String itemFile;
	private Integer itemFileSize;

	public Mbsmsgitems() {
	}

	public Mbsmsgitems(String msgId, Short itemIndex, String contentType,
			String itemFile, Integer itemFileSize) {
		this.msgId = msgId;
		this.itemIndex = itemIndex;
		this.contentType = contentType;
		this.itemFile = itemFile;
		this.itemFileSize = itemFileSize;
	}

	public Integer getIdxNo() {
		return this.idxNo;
	}

	public void setIdxNo(Integer idxNo) {
		this.idxNo = idxNo;
	}

	public String getMsgId() {
		return this.msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Short getItemIndex() {
		return this.itemIndex;
	}

	public void setItemIndex(Short itemIndex) {
		this.itemIndex = itemIndex;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getItemFile() {
		return this.itemFile;
	}

	public void setItemFile(String itemFile) {
		this.itemFile = itemFile;
	}

	public Integer getItemFileSize() {
		return this.itemFileSize;
	}

	public void setItemFileSize(Integer itemFileSize) {
		this.itemFileSize = itemFileSize;
	}

}
