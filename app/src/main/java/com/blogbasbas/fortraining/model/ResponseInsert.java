package com.blogbasbas.fortraining.model;

import com.google.gson.annotations.SerializedName;

public class ResponseInsert{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private String code;

	@SerializedName("status")
	private boolean status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseInsert{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}