package com.vunguyen.device.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.stereotype.Repository;

import com.vunguyen.device.dao.DeviceDao;
import com.vunguyen.device.valueobject.Data;
import com.vunguyen.device.valueobject.Device;
import com.vunguyen.device.valueobject.Temperature;

/**
 * Data Access Interface for Device webservice.
 */
@Repository
public class DeviceDaoImpl implements DeviceDao {

	/**
	 * Declare a map to store all device objects.
	 */
	private static final Map<String, Device> deviceMap = new HashMap<String, Device>();

	
	/**
	 * Use static block to initial static deviceMap.
	 */
	static {
		try {
			initDevices();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initial devices static map with one record existed.
	 * @throws ParseException 
	 */
	private static void initDevices() throws ParseException {
		Temperature temperature = new Temperature("C", 23.3);
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date timestamp = isoFormat.parse("2018-08-16T02:00:39.000");
		Data data1 = new Data(123, temperature, timestamp);
		List<Data> datas1 = new ArrayList<Data>();
		datas1.add(data1);
		
		Device dev1 = new Device("D01", 41.25, -120.9762, datas1);
		deviceMap.put(dev1.getDeviceId(), dev1);
	}

	/**
	 * Gets a device entries by device id
	 * 
	 * @param  devId
	 * @return An object of device
	*/
	@Override
	public Device getDevice(String devId) {
		return deviceMap.get(devId);
	}

	/**
	 * Insert a new device entries
	 * 
	 * @param  dev
	 * @return An object of device
	*/
	@Override
	public Device addDevice(Device dev) {
		deviceMap.put(dev.getDeviceId(), dev);
		return dev;
	}
}
