package com.tibame.OrderMaster;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.OrderDetail.OrderDetailDAO;
import com.tibame.OrderDetail.OrderDetailVO;

public class Test {

	static OrderMasterDAO dao = new OrderMasterDAO();
	static OrderDetailDAO detailDao = new OrderDetailDAO();
	static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static void main(String[] args) {

//		order_id, user_id, store_id, order_status, 
//		order_amount, order_time, update_time, order_remark

//		OrderMasterVO vo = new OrderMasterVO(null, 2, 1, 0, 580, getNowDate(), null, "好吃");
//		dao.insert(vo);
		detailDao.insert(new OrderDetailVO(9, 1, 500, 2));

	}
}
