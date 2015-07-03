package de.graeuler.jtracapi.model.wiki;

import java.util.Date;

public class WikiPageInfo {

	private String author;
	private String name;
	private String comment;
	private Date lastModified;
	private Integer version;

	public void setAuthor(String string) {
		this.author = string;
	}
	
	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
