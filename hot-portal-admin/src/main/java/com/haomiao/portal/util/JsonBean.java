package com.haomiao.portal.util;

public class JsonBean {
	
	private boolean success;
	private String message;
	private Object data;
	
	public JsonBean(){
		
	}
	
	public JsonBean(Object data){
		this.data = data;
	}
	
	public JsonBean(String message,boolean success){
		this.success = success;
		this.message = message;
	}
	
	public JsonBean(String message,boolean success,Object data){
		this.data = data;
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
