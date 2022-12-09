package com.tibame.UserStoreConn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/wishlist")
public class UserStoreConnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson _gson = new Gson(); // Gson API 可以把物件轉成json
	UserStoreConnDAO dao = new UserStoreConnDAO();

	// 查詢
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response); // 跨域
		PrintWriter out = response.getWriter();
		// 抓前端url上的的參數(拿到請求的參數)
		// 一定都是String(因為網址是String)
		String userId = request.getParameter("userId");
		String storeId = request.getParameter("storeId");
		// PK要放的是兩個整數 // 將字串轉成整數
		if (userId != null && storeId == null) {
			System.out.println(userId);
			// toJson String
			out.print(_gson.toJson(dao.getWishList(Integer.parseInt(userId))));
			return;
		}

		PK pk = new PK(Integer.parseInt(userId), Integer.parseInt(storeId));

		out.print(_gson.toJson(dao.getById(pk)));
	}

	// 增
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		PrintWriter out = response.getWriter();
//		// Read POST
		BufferedReader read = request.getReader();
//		// 存字串
		String json = read.readLine();
		UserStoreConnBean bean = _gson.fromJson(json, UserStoreConnBean.class);

		if (!bean.getStatus().toString().isEmpty()) {
			dao.update(new UserStoreConnVO(new PK(bean.getUserId(), bean.getStoreId()), bean.getStatus(),
					bean.getStatusUpdateTime()));
		} else {
			dao.insert(new UserStoreConnVO(new PK(bean.getUserId(), bean.getStoreId()), bean.getStatus(),
					bean.getStatusUpdateTime()));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		out.print(_gson.toJson(map));
	}

	// 修
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setHeaders(response);
		PrintWriter out = response.getWriter();
//		// Read POST
		BufferedReader read = request.getReader();
//		// 存字串
		String json = read.readLine();
		UserStoreConnBean bean = _gson.fromJson(json, UserStoreConnBean.class);

		dao.update(new UserStoreConnVO(new PK(bean.getUserId(), bean.getStoreId()), bean.getStatus(),
				bean.getStatusUpdateTime()));

		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		out.print(_gson.toJson(map));
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);

	}

	/*
	 * 跨域
	 */
	private void setHeaders(HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8"); // 重要
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		response.addHeader("Access-Control-Allow-Origin", "*"); // 重要 允許不同連線
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers", "*");
		response.addHeader("Access-Control-Max-Age", "86400");
	}

}
