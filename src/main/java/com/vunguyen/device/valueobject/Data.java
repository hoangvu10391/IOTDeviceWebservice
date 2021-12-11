package com.vunguyen.device.valueobject;

import java.util.Date;

public class Data {
	
	private int humidity;
	private Temperature temperature;
	private Date timestamp;

	public Data() {
	}

	public Data(int humidity, Temperature temperature, Date timestamp) {
		this.humidity = humidity;
		this.temperature = temperature;
		this.timestamp = timestamp;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
