package cn.edu.bnu.land.model;

// Generated 2013-10-5 11:38:45 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * RssItemsDetails generated by hbm2java
 */
public class RssItemsDetails implements java.io.Serializable {

	private int itemsId;
	private RssChannelItems rssChannelItems;
	private String title;
	private String link;
	private Date pubdate;
	private String description;

	public RssItemsDetails() {
	}

	public RssItemsDetails(RssChannelItems rssChannelItems) {
		this.rssChannelItems = rssChannelItems;
	}

	public RssItemsDetails(RssChannelItems rssChannelItems, String title,
			String link, Date pubdate, String description) {
		this.rssChannelItems = rssChannelItems;
		this.title = title;
		this.link = link;
		this.pubdate = pubdate;
		this.description = description;
	}

	public int getItemsId() {
		return this.itemsId;
	}

	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}

	public RssChannelItems getRssChannelItems() {
		return this.rssChannelItems;
	}

	public void setRssChannelItems(RssChannelItems rssChannelItems) {
		this.rssChannelItems = rssChannelItems;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubdate() {
		return this.pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
