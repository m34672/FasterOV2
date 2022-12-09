package com.tibame.OrderMaster;

import java.util.List;

import lombok.Data;

@Data
public class OrderMasterBean {

	private Integer userId;

	private Integer storeId;

	private String remark;

	private Integer amount;

	private List<Product> product;

	@Data
	public class Product {

		private Integer id;
		private String name;
		private Integer price;
		private Integer quantity;
	}
}
