package com.tibame.OrderMaster;

import static com.tibame.utils.HttpUtils.CORS;
import static com.tibame.utils.HttpUtils.writejson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/OrderMaster")
public class OrderMasterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	OrderMasterService service = new OrderMasterService();
	Gson _gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 接收url的請求

		CORS(resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CORS(resp);
		// 接收body的請求，轉成java物件
		OrderMasterBean bean = writejson(OrderMasterBean.class, req);
		// 將資料寫入(addOrder方法)
		resp.getWriter().print(_gson.toJson(service.addOrder(bean)));
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CORS(resp);
	}

}
