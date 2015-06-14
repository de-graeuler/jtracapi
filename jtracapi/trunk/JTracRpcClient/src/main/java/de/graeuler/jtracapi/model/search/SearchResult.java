package de.graeuler.jtracapi.model.search;

import java.util.Date;

public class SearchResult {

	private String href;
	private String title;
	private Date date;
	private String author;
	private String excerpt;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
}
