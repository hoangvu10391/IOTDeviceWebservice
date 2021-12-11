package com.vunguyen.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vunguyen.device.dao.DeviceDao;
import com.vunguyen.device.service.DeviceService;
import com.vunguyen.device.valueobject.Data;
import com.vunguyen.device.valueobject.Device;

/**
 * Service layer for Device webservice.
 */
@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceDao deviceDao;
	
	/**
	 * Gets a device entries by device id
	 * 
	 * @param  devId
	 * @param  sDate
	 * @param  eDate
	 * @return An object of device
	*/
	@Override
	public Device getDevice(String devId, Date sDate, Date eDate) {
		return populateDeviceData(deviceDao.getDevice(devId), sDate, eDate);
	}

	/**
	 * Insert a new device entries
	 * 
	 * @param  dev
	 * @return An object of device
	*/
	@Override
	public Device addDevice(Device dev) {
		return deviceDao.addDevice(dev);
	}

	/**
	 * Retrieve device data from valueobject
	 * 
	 * @param  deviceData
	 * @param  start
	 * @param  end
	 * @return An object of device
	*/
	private Device populateDeviceData(Device deviceData, Date start, Date end) {
		Device device = null;
		List<Data> data = null;
		
		if(deviceData != null) {
			device = new Device();
			data = new ArrayList<Data>();
			
			// Get basic device data
			device.setDeviceId(deviceData.getDeviceId());
			device.setLatitude(deviceData.getLatitude());
			device.setLongitude(deviceData.getLongitude());
			device.setData(data);
			
			// Proceed populate data
			List<Data> datas = deviceData.getData();
			for (Data d : datas) {
				if(start == null && end == null) {
					device.getData().add(d);
				} else {
					if(d.getTimestamp().after(start) && d.getTimestamp().before(end)) {
						device.getData().add(d);
					}
				}
			}
		}
		
		return device;
	}
}
