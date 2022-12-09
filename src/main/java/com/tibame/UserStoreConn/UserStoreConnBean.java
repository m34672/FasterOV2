package com.tibame.UserStoreConn;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;

@Data
public class UserStoreConnBean {
//	user_id, store_id, status,
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	private Integer userId;
	private Integer storeId;
	private Integer status;
	private String statusUpdateTime = gson.toJson(new Date()).replaceAll("\"", "");
}
