package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PicsumImage {
	private Integer id;
	private String author;
	private Integer width;
	private Integer height;
	private String url;
	@JsonProperty("download_url")
	private String downloadUrl;
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Integer getWidth(){
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	public Integer getHeight(){
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public String getUrl(){
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDownloadUrl(){
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}