package com.tibame.UserStoreConn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListVO {
//	store_preview_img, store_name, store_introduction, store_type
	private String storeName;
	private String storeIntroduction;
	private String storeType;
	private byte[] storePreviewImg;
	private String storePreviewImgBase64;

}
