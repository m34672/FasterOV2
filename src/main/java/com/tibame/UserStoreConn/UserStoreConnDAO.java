package com.tibame.UserStoreConn;

import static com.tibame.UserStoreConn.UserStoreConnSQL.GET_ALL;
import static com.tibame.UserStoreConn.UserStoreConnSQL.GET_BY_ID;
import static com.tibame.UserStoreConn.UserStoreConnSQL.GET_WISH_LIST;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tibame.utils.HibernateUtil;

public class UserStoreConnDAO {

	public List<UserStoreConnVO> getAll() {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			/**
			 * HQL
			 */
			@SuppressWarnings("unchecked")
			List<UserStoreConnVO> list = session.createQuery(GET_ALL).list();

			list.forEach(System.out::println);

			tx.commit();
			return list;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public boolean insert(UserStoreConnVO vo) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			session.save(vo);

			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(UserStoreConnVO vo) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();

			session.update(vo);

			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}

// 查
	public List<UserStoreConnVO> getById(PK pk) { // 傳入參數
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tx = session.beginTransaction();
			/**
			 * API
			 */
			@SuppressWarnings("unchecked")
			List<UserStoreConnVO> list = session.createQuery(GET_BY_ID).setParameter(0, pk).list();
			tx.commit();
			return list;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return null;
	}

	public List<WishListVO> getWishList(Integer id) {

		List<WishListVO> list = new ArrayList<>();
		WishListVO vo;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FASTERO", "root", "password");
				PreparedStatement ps = con.prepareStatement(GET_WISH_LIST);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					vo = new WishListVO();

//					vo.setStorePreviewImg(rs.getBytes("store_preview_img"));
					vo.setStoreName(rs.getString("store_name"));
					vo.setStoreIntroduction(rs.getString("store_introduction"));
					vo.setStoreType(rs.getString("store_type"));
					vo.setStorePreviewImgBase64("data:image/jpeg;base64,"
							+ Base64.getEncoder().encodeToString(rs.getBytes("store_preview_img")));

					list.add(vo);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(list.size());
		return list;
	}
}
