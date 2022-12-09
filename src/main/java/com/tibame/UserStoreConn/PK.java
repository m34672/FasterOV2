package com.tibame.UserStoreConn;

/*
 * 雙主鍵
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PK implements Serializable {

	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "store_id")
	private Integer storeId;
}
