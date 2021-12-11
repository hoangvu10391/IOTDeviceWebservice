package com.vunguyen.device.valueobject;

import java.math.BigDecimal;
import java.util.List;

public class Device {

	private String deviceId;
	private double latitude;
	private double longitude;
	private List<Data> data;

	public Device() {
	}

	public Device(String deviceId, double latitude, double longitude, List<Data> data) {
		this.deviceId = deviceId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.data = data;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
}
