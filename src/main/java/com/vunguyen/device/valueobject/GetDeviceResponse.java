package com.vunguyen.device.valueobject;

public class GetDeviceResponse {

	private String errorCode;
	private String errMsg;
	private Device device;

	public GetDeviceResponse() {
	}

	public GetDeviceResponse(String errorCode, String errMsg, Device device) {
		this.errorCode = errorCode;
		this.errMsg = errMsg;
		this.device = device;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
}
