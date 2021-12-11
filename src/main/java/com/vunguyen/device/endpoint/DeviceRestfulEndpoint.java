package com.vunguyen.device.endpoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vunguyen.device.service.DeviceService;
import com.vunguyen.device.valueobject.Device;
import com.vunguyen.device.valueobject.GetDeviceResponse;

/**
 * Restful controller for device webservice.
 */
@Controller
@RequestMapping("/api")
public class DeviceRestfulEndpoint {

	private static final String GET_DEVICE_REQUEST = "/devices";
	private static final String ADD_DEVICE_REQUEST = "/addDevice";

	@Autowired
	private DeviceService deviceService;

	/**
	 * RESTful controller for Get device by Id
	 * 
	 * @param deviceId String
	 * @return GetDeviceResponse object
	 */
	@RequestMapping(value = GET_DEVICE_REQUEST + "/{deviceId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public GetDeviceResponse getDeviceById(@PathVariable("deviceId") String deviceId) {
		GetDeviceResponse response = new GetDeviceResponse();
		response.setErrorCode("404");
		response.setErrMsg("Device ID: " + deviceId + " not found");
		
		Device device = deviceService.getDevice(deviceId, null, null);
		if(device != null) {
			response.setErrorCode("200");
			response.setErrMsg("Success");
			response.setDevice(device);
		}
		
		return response;
	}

	/**
	 * RESTful controller for Get device by start time and end time
	 * 
	 * @param deviceId
	 * @param sDate
	 * @param eDate
	 * @return GetDeviceResponse object
	 */
	@RequestMapping(value = GET_DEVICE_REQUEST + "/{deviceId}/{startTime}/{endTime}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public GetDeviceResponse getDeviceByTime(@PathVariable("deviceId") String deviceId,
											 @PathVariable("startTime") String sDate,
											 @PathVariable("endTime") String eDate) {
		GetDeviceResponse response = new GetDeviceResponse();
		response.setErrorCode("404");
		response.setErrMsg("Device ID: " + deviceId + " not found with startDate: " + sDate + " and endDate: " + eDate);
		
		try {
			Date startDate = parseStringDateToUtilDate(sDate);
			Date edndDate = parseStringDateToUtilDate(eDate);
			
			Device device = deviceService.getDevice(deviceId, startDate, edndDate);
			
			if(device != null) {
				response.setErrorCode("200");
				response.setErrMsg("Success");
				response.setDevice(device);
			}
			
		} catch (ParseException e) {
			response.setErrorCode("500");
			response.setErrMsg("Fail to parse date time, please enter the correct date time format: yyyy-MM-dd'T'HH:mm:ss");
		}
		
		return response;
	}

	/**
	 * RESTful controller for save device
	 * 
	 * @param dev Device
	 * @return Device object
	 */
	@RequestMapping(value = ADD_DEVICE_REQUEST, method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Device addDevice(@RequestBody Device dev) {
		return deviceService.addDevice(dev);
	}

	/**
	 * Method for parse string date to Util date
	 * Correct formatted yyyy-MM-dd'T'HH:mm:ss
	 * 
	 * @param date
	 * @return Date object
	 * @throws ParseException
	 */
	private Date parseStringDateToUtilDate(String date) throws ParseException {
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return isoFormat.parse(date);
	}
}
