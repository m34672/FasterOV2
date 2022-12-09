package com.tibame.UserStoreConn;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		UserStoreConnDAO dao = new UserStoreConnDAO();
//		WishListDAO DAO = new WishListDAO();
//		dao.getAll();
//		System.out.println(gson.toJson(new Date()));

//		dao.getById(new PK(1, 1));
//		dao.insert(new UserStoreConnVO(new PK(2, 1), 1, gson.toJson(new Date()).replaceAll("\"", "")));
//		dao.update(new UserStoreConnVO(new PK(2, 1), 2, gson.toJson(new Date()).replaceAll("\"", "")));

//		dao.getWishList(new PK(1, 1));

		List<WishListVO> list = dao.getWishList(2);
		System.out.println(list.size());
	}

}
