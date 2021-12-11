package com.vunguyen.device.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.vunguyen.device.dao.impl.DeviceDaoImpl;
import com.vunguyen.device.valueobject.Data;
import com.vunguyen.device.valueobject.Device;
import com.vunguyen.device.valueobject.Temperature;

@RunWith(MockitoJUnitRunner.class)
public class DeviceDaoImplTest {
	
	@InjectMocks
	private DeviceDaoImpl instance;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetDevice() {
		// Prepare sample data
		String deviceId = "D01";
		
		// Call method
		Device actual = instance.getDevice(deviceId);
		
		// Verify
		assertEquals("D01", actual.getDeviceId());
	}
	
	@Test
	public void testAddDevice() {
		// Prepare sample data
		Temperature temperature = new Temperature("C", 23.3);
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date timestamp = null;
		try {
			timestamp = isoFormat.parse("2018-08-16T02:00:39.000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data data1 = new Data(123, temperature, timestamp);
		List<Data> datas1 = new ArrayList<Data>();
		datas1.add(data1);
		
		Device dev1 = new Device("D02", 41.25, -120.9762, datas1);
		
		// Call method
		Device actual = instance.addDevice(dev1);
		
		// Verify
		assertEquals("D02", actual.getDeviceId());
		assertEquals(Double.valueOf(41.25), Double.valueOf(actual.getLatitude()));
		assertEquals(Double.valueOf(-120.9762), Double.valueOf(actual.getLongitude()));
		assertEquals(123, actual.getData().get(0).getHumidity());
		assertEquals("C", actual.getData().get(0).getTemperature().getUnit());
		assertEquals(Double.valueOf(23.3), Double.valueOf(actual.getData().get(0).getTemperature().getValue()));
		assertEquals(timestamp, actual.getData().get(0).getTimestamp());
	}
}
