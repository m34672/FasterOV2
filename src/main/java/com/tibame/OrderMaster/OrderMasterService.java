package com.tibame.OrderMaster;

import static com.tibame.utils.DateUtills.getNowDate;

import com.tibame.OrderDetail.OrderDetailDAO;
import com.tibame.OrderDetail.OrderDetailVO;
import com.tibame.OrderMaster.OrderMasterBean.Product;

public class OrderMasterService {

	OrderMasterDAO dao;
	OrderDetailDAO detailDao;

	public OrderMasterService() {
		detailDao = new OrderDetailDAO();
		dao = new OrderMasterDAO();
	}

	public boolean addOrder(OrderMasterBean bean) {
		try {
			// 傳入vo 新增一筆資料 取得自增主鍵
			int ai = dao.insert(new OrderMasterVO(null, bean.getUserId(), bean.getStoreId(), 0, bean.getAmount(),
					getNowDate(), null, bean.getRemark()));
			// 新增多筆detail
			for (Product p : bean.getProduct()) {
				detailDao.insert(new OrderDetailVO(ai, p.getId(), p.getPrice() / p.getQuantity(), p.getQuantity()));
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
