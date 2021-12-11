package com.vunguyen.device.dao;

import com.vunguyen.device.valueobject.Device;

/**
 * Data Access layer for Device webservice.
 */
public interface DeviceDao {

	/**
	 * Gets a device entries by device id
	 * 
	 * @param  devId
	 * @return An object of device
	*/
	public Device getDevice(String devId);

	/**
	 * Insert a new device entries
	 * 
	 * @param  dev
	 * @return An object of device
	*/
	public Device addDevice(Device dev);
}
