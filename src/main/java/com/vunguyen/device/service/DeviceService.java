package com.vunguyen.device.service;

import java.util.Date;

import com.vunguyen.device.valueobject.Device;

public interface DeviceService {

	/**
	 * Gets a device entries by device id
	 * 
	 * @param  devId
	 * @param  sDate
	 * @param  eDate
	 * @return An object of device
	*/
	public Device getDevice(String devId, Date sDate, Date eDate);

	/**
	 * Insert a new device entries
	 * 
	 * @param  dev
	 * @return An object of device
	*/
	public Device addDevice(Device dev);
}
