package com.hcsw.androidapplicationblocksample.app.entities;

/**
 * Created by gwjang on 2014. 4. 28..
 */
public class UserCheckData {

	private String result_code;
	private String result_message;
	private DataClass data;

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

	public DataClass getData() {
		return data;
	}

	public void setData(DataClass data) {
		this.data = data;
	}

	public class DataClass {
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}
