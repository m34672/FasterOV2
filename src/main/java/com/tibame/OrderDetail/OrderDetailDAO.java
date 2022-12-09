package com.tibame.OrderDetail;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tibame.utils.HibernateUtil;

public class OrderDetailDAO {

	public int insert(OrderDetailVO vo) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			int res = (int) session.save(vo); // insert api //返回自增主鍵

			tx.commit();
			return res;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new Error();
//			return 0;
		}
	}
}
