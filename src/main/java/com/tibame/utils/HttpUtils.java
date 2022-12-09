package com.tibame.utils;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class HttpUtils {
	static Gson _gson = new Gson(); // Gson API 可以把物件轉成json
// middle ware

	public static void CORS(HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8"); // 重要 回傳json
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要 允許不同連線
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

	public static <T> T writejson(Class<T> type, HttpServletRequest request) {
		// read post
		try (BufferedReader read = request.getReader()) {
			return _gson.fromJson(read.readLine(), type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
