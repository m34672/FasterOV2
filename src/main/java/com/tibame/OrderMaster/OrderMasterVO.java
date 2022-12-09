package com.tibame.OrderMaster;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "OrderMaster")
@NoArgsConstructor
@AllArgsConstructor
public class OrderMasterVO {
//	order_id, user_id, store_id, order_status, order_amount, order_time, update_time, order_remark
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;
	@Column(name = "user_id")
	private Integer useId;
	@Column(name = "store_id")
	private Integer storeId;
	@Column(name = "order_status")
	private Integer orderStatus;
	@Column(name = "order_amount")
	private Integer orderAmount;
	@Column(name = "order_time")
	private String orderTime;
	@Column(name = "update_time", nullable = true)
	private String updateTime;
	@Column(name = "order_remark")
	private String orderRemark;
}
