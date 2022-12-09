package com.tibame.UserStoreConn;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UserStoreConn")
public class UserStoreConnVO {
//	user_id, store_id, status, status_update_time

	@EmbeddedId
	private PK id;

	@Column(name = "status")
	private Integer status;
	@Column(name = "status_update_time")
	private String statusUpdateTime;

}
