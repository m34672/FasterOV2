package com.tibame.UserStoreConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class WishListDAO {

//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TGA103G3");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	public List<WishListVO> getWishList(Integer id) {

		final String sql = "select s.store_preview_img,s.store_name,s.store_introduction,b.store_type from Store s INNER JOIN StoreType b \n"
				+ "where s.Store_id in (select store_id from FASTERO.UserStoreConn where user_id = ? and status = 1);";

		List<WishListVO> list = new ArrayList<>();
		WishListVO vo;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/FASTERO", "root", "password");
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					vo = new WishListVO();

//					store_preview_img, store_name, store_introduction, store_type

//					vo.setStorePreviewImg(rs.getBytes("store_preview_img"));
					vo.setStoreName(rs.getString("store_name"));
					vo.setStoreIntroduction(rs.getString("store_introduction"));
					vo.setStoreType(rs.getString("store_type"));
					vo.setStorePreviewImgBase64(Base64.getEncoder().encodeToString(rs.getBytes("store_preview_img")));

					list.add(vo);
				}
			}

			return list;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
}
