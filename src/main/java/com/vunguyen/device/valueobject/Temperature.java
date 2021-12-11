package com.vunguyen.device.valueobject;

public class Temperature {

	private String unit;
	private double value;

	public Temperature() {
	}

	public Temperature(String unit, double value) {
		this.unit = unit;
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
