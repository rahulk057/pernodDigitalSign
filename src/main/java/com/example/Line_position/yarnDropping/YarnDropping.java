package com.example.Line_position.yarnDropping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yarndropping")
public class YarnDropping {
      @Id
      @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String line_id;
	private String current_position;
	private String date;
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getCurrent_position() {
		return current_position;
	}
	public void setCurrent_position(String current_position) {
		this.current_position = current_position;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
