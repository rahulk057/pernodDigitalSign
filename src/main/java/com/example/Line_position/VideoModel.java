package com.example.Line_position;


public class VideoModel {
	String response;
	String line_id ;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public VideoModel(String response, String line_id) {
		super();
		this.response = response;
		this.line_id = line_id;
	}
	
	
}
