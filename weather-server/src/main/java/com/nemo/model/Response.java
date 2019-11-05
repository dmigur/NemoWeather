package com.nemo.model;

import com.nemo.entity.Weather;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author D.Gurov
 * Response sent to the client
 */
public class Response {

	private List<Weather> data;
	private Date date;

	public Response() {
	}
	public Response(List<Weather> data, Date date) {
		this.data = data;
		this.date = date;
	}

	public List<Weather> getData() {
		return data;
	}

	public void setData(List<Weather> data) {
		this.data = data;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
