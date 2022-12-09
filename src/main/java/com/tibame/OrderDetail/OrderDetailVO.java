package com.tibame.OrderDetail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "OrderDetail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailVO {
	@Id
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "price")
	private Integer price;
	@Column(name = "quantity")
	private Integer quantity;

}
