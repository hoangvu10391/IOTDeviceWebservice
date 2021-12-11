package com.vunguyen.device.endpoint;

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
import com.vunguyen.device.service.DeviceService;
import com.vunguyen.device.valueobject.Data;
import com.vunguyen.device.valueobject.Device;
import com.vunguyen.device.valueobject.GetDeviceResponse;
import com.vunguyen.device.valueobject.Temperature;

@RunWith(MockitoJUnitRunner.class)
public class DeviceRestfulEnpointTest {

	@InjectMocks
	private DeviceRestfulEndpoint instance;
	
	@Mock
	private DeviceService deviceService;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
	}
	
	@Test
	public void testGetDeviceById_hasNoDeviceDataReturn() {
		// Prepare sample data
		String deviceId = "D01";
		String errorCode = "404";
		String errorMsg = "Device ID: " + deviceId + " not found";
		
		when(deviceService.getDevice(deviceId, null, null)).thenReturn(null);
		
		// Call method
		GetDeviceResponse getDeviceResponse = instance.getDeviceById(deviceId);
		
		// Verify
		assertEquals(errorCode, getDeviceResponse.getErrorCode());
		assertEquals(errorMsg, getDeviceResponse.getErrMsg());
		assertEquals(null, getDeviceResponse.getDevice());
	}
	
	@Test
	public void testGetDeviceById_hasDeviceDataReturn() {
		// Prepare sample data
		String deviceId = "D01";
		String errorCode = "200";
		String errorMsg = "Success";
		
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
		
		Device dev1 = new Device(deviceId, 41.25, -120.9762, datas1);
		
		when(deviceService.getDevice(deviceId, null, null)).thenReturn(dev1);
		
		// Call method
		GetDeviceResponse getDeviceResponse = instance.getDeviceById(deviceId);
		
		// Verify
		assertEquals(errorCode, getDeviceResponse.getErrorCode());
		assertEquals(errorMsg, getDeviceResponse.getErrMsg());
		assertEquals(deviceId, getDeviceResponse.getDevice().getDeviceId());
		assertEquals(Double.valueOf(41.25), Double.valueOf(getDeviceResponse.getDevice().getLatitude()));
		assertEquals(Double.valueOf(-120.9762), Double.valueOf(getDeviceResponse.getDevice().getLongitude()));
		assertEquals(123, getDeviceResponse.getDevice().getData().get(0).getHumidity());
		assertEquals("C", getDeviceResponse.getDevice().getData().get(0).getTemperature().getUnit());
		assertEquals(Double.valueOf(23.3), Double.valueOf(getDeviceResponse.getDevice().getData().get(0).getTemperature().getValue()));
		assertEquals(timestamp, getDeviceResponse.getDevice().getData().get(0).getTimestamp());
	}
	
	@Test
	public void testGetDeviceByTime_hasWrongDateFormat() {
		// Prepare sample data
		String deviceId = "D01";
		String sDate = "abc";
		String eDate = "abc";
		
		String errorCode = "500";
		String errorMsg = "Fail to parse date time, please enter the correct date time format: yyyy-MM-dd'T'HH:mm:ss";
		
		// Call method
		GetDeviceResponse getDeviceResponse = instance.getDeviceByTime(deviceId, sDate, eDate);
		
		// Verify
		assertEquals(errorCode, getDeviceResponse.getErrorCode());
		assertEquals(errorMsg, getDeviceResponse.getErrMsg());
	}
	
	@Test
	public void testGetDeviceByTime_hasNoDataReturn() {
		// Prepare sample data
		String deviceId = "D01";
		String sDate = "2018-08-15T02:00:39.000";
		String eDate = "2018-08-19T02:00:39.000";
		
		String errorCode = "404";
		String errorMsg = "Device ID: " + deviceId + " not found with startDate: " + sDate + " and endDate: " + eDate;
		
		// Call method
		GetDeviceResponse getDeviceResponse = instance.getDeviceByTime(deviceId, sDate, eDate);
		
		// Verify
		assertEquals(errorCode, getDeviceResponse.getErrorCode());
		assertEquals(errorMsg, getDeviceResponse.getErrMsg());
	}
	
	@Test
	public void testGetDeviceByTime_hasDataReturn() throws ParseException {
		// Prepare sample data
		String deviceId = "D01";
		String sDate = "2018-08-15T02:00:39.000";
		String eDate = "2018-08-19T02:00:39.000";
		
		String errorCode = "200";
		String errorMsg = "Success";
		
		Date startDate = parseStringDateToUtilDate("2018-08-15T02:00:39.000");
		Date endDate = parseStringDateToUtilDate("2018-08-19T02:00:39.000");
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
		
		Device dev1 = new Device(deviceId, 41.25, -120.9762, datas1);
		
		when(deviceService.getDevice(deviceId, startDate, endDate)).thenReturn(dev1);
		
		// Call method
		GetDeviceResponse getDeviceResponse = instance.getDeviceByTime(deviceId, sDate, eDate);
		
		// Verify
		assertEquals(errorCode, getDeviceResponse.getErrorCode());
		assertEquals(errorMsg, getDeviceResponse.getErrMsg());
		assertEquals(deviceId, getDeviceResponse.getDevice().getDeviceId());
		assertEquals(Double.valueOf(41.25), Double.valueOf(getDeviceResponse.getDevice().getLatitude()));
		assertEquals(Double.valueOf(-120.9762), Double.valueOf(getDeviceResponse.getDevice().getLongitude()));
		assertEquals(123, getDeviceResponse.getDevice().getData().get(0).getHumidity());
		assertEquals("C", getDeviceResponse.getDevice().getData().get(0).getTemperature().getUnit());
		assertEquals(Double.valueOf(23.3), Double.valueOf(getDeviceResponse.getDevice().getData().get(0).getTemperature().getValue()));
		assertEquals(timestamp, getDeviceResponse.getDevice().getData().get(0).getTimestamp());
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
		
		when(deviceService.addDevice(dev1)).thenReturn(dev1);
		
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
