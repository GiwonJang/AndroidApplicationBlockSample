package com.hcsw.androidapplicationblocksample.app.entities;

/**
 * Created by gwjang on 2014. 4. 28..
 */
public class APIResponse<T extends Object> {
	private String result_code;
	private String result_message;
	private T data;

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getResult_message() {
		return result_message;
	}

	public void setResult_message(String result_message) {
		this.result_message = result_message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
