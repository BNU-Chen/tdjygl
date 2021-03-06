package cn.edu.bnu.land.model;

// Generated 2013-9-6 11:32:46 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * InfoComment generated by hbm2java
 */
public class InfoComment implements java.io.Serializable {

	private Integer commentId;
	private Integer articleId;
	private Date commentTime;
	private String commentContent;
	private String commentAuthor;

	public InfoComment() {
	}

	public InfoComment(Integer articleId, Date commentTime,
			String commentContent, String commentAuthor) {
		this.articleId = articleId;
		this.commentTime = commentTime;
		this.commentContent = commentContent;
		this.commentAuthor = commentAuthor;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Date getCommentTime() {
		return this.commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentContent() {
		return this.commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentAuthor() {
		return this.commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

}
