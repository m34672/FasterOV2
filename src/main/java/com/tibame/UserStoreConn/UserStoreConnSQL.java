package com.tibame.UserStoreConn;

/*
 * HQL
 */
public class UserStoreConnSQL {

	public static final String GET_ALL = "from UserStoreConnVO";

	public static final String GET_BY_ID = "FROM UserStoreConnVO where id = ?0";

	public static final String GET_WISH_LIST = "SELECT store_preview_img,store_name,store_introduction,store_type FROM Store \n"
			+ "where store_id in (select store_id from UserStoreConn where user_id = ? and status = 1)";
}
