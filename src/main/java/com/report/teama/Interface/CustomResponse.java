package com.report.teama.Interface;

public class CustomResponse {
    private int statusCode;
    private String message;
    private Object data;
    
    

    public CustomResponse(int statusCode,String message, Object data ) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }



	public int getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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