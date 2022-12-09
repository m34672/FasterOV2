package com.tibame.OrderMaster;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tibame.utils.HibernateUtil;

public class OrderMasterDAO {

	public int insert(OrderMasterVO vo) {

		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			int res = (int) session.save(vo); // insert api + 返回自增主鍵

			tx.commit();
			return res;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return 0;
		}
	}

}