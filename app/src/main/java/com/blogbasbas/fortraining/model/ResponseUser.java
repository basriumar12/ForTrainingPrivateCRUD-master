package com.blogbasbas.fortraining.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseUser{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("username")
	private List<UsernameItem> username;

	@SerializedName("status")
	private boolean status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setUsername(List<UsernameItem> username){
		this.username = username;
	}

	public List<UsernameItem> getUsername(){
		return username;
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
			"ResponseUser{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",username = '" + username + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}