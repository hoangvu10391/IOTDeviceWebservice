package com.vunguyen.device.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.vunguyen.device.dao.DeviceDao;
import com.vunguyen.device.service.impl.DeviceServiceImpl;
import com.vunguyen.device.valueobject.Data;
import com.vunguyen.device.valueobject.Device;
import com.vunguyen.device.valueobject.Temperature;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceImpTest {

	@InjectMocks
	private DeviceServiceImpl instance;
	
	@Mock
	private DeviceDao deviceDao;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetDevice_hasNoStartDateAndEndDate() {
		// Prepare sample data
		String devId = "D01";
		
		Temperature temperature = new Temperature("C", 23.3);
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date timestamp = null;
		try {
			timestamp = isoFormat.parse("2018-08-16T02:00:39.000Z");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data data1 = new Data(123, temperature, timestamp);
		List<Data> datas1 = new ArrayList<Data>();
		datas1.add(data1);
		
		Device dev1 = new Device(devId, 41.25, -120.9762, datas1);
		
		when(deviceDao.getDevice(devId)).thenReturn(dev1);
		
		// Call method
		Device actual = instance.getDevice(devId, null, null);
		
		// Verify
		assertEquals(devId, actual.getDeviceId());
		assertEquals(Double.valueOf(41.25), Double.valueOf(actual.getLatitude()));
		assertEquals(Double.valueOf(-120.9762), Double.valueOf(actual.getLongitude()));
		assertEquals(123, actual.getData().get(0).getHumidity());
		assertEquals("C", actual.getData().get(0).getTemperature().getUnit());
		assertEquals(Double.valueOf(23.3), Double.valueOf(actual.getData().get(0).getTemperature().getValue()));
		assertEquals(timestamp, actual.getData().get(0).getTimestamp());
	}
	
	@Test
	public void testGetDevice_hasStartDateAndEndDate() throws ParseException {
		// Prepare sample data
		String devId = "D01";
		Date startDate = parseStringDateToUtilDate("2018-08-15T02:00:39.000");
		Date endDate = parseStringDateToUtilDate("2018-08-20T02:00:39.000");
		
		Temperature temperature = new Temperature("C", 23.3);
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date timestamp = null;
		try {
			timestamp = isoFormat.parse("2018-08-16T02:00:39.000Z");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data data1 = new Data(123, temperature, timestamp);
		List<Data> datas1 = new ArrayList<Data>();
		datas1.add(data1);
		
		Device dev1 = new Device(devId, 41.25, -120.9762, datas1);
		
		when(deviceDao.getDevice(devId)).thenReturn(dev1);
		
		// Call method
		Device actual = instance.getDevice(devId, startDate, endDate);
		
		// Verify
		assertEquals(devId, actual.getDeviceId());
		assertEquals(Double.valueOf(41.25), Double.valueOf(actual.getLatitude()));
		assertEquals(Double.valueOf(-120.9762), Double.valueOf(actual.getLongitude()));
		assertEquals(123, actual.getData().get(0).getHumidity());
		assertEquals("C", actual.getData().get(0).getTemperature().getUnit());
		assertEquals(Double.valueOf(23.3), Double.valueOf(actual.getData().get(0).getTemperature().getValue()));
		assertEquals(timestamp, actual.getData().get(0).getTimestamp());
	}
	
	@Test
	public void testGetDevice_hasNoDeviceData() throws ParseException {
		// Prepare sample data
		String devId = "D01";
		
		when(deviceDao.getDevice(devId)).thenReturn(null);
		
		// Call method
		Device actual = instance.getDevice(devId, null, null);
		
		// Verify
		assertEquals(null, actual);
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
		
		when(deviceDao.addDevice(dev1)).thenReturn(dev1);
		
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
	
	private Date parseStringDateToUtilDate(String date) throws ParseException {
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return isoFormat.parse(date);
	}
}
