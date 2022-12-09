package com.tibame.utils;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DateUtills {

	private final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static String getNowDate() {

		return gson.toJson(new Date()).replaceAll("\"", ""); // 現在時間轉成"yyyy-MM-dd HH:mm:ss" 刪掉""
	}
}
