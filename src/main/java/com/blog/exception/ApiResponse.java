package com.blog.exception;

public class ApiResponse {

	private String message;
	private String timestamp;

	public ApiResponse(String message, String timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", timestamp=" + timestamp + "]";
	}

}
