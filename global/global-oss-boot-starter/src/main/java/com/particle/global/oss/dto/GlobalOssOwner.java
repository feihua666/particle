package com.particle.global.oss.dto;

import com.particle.global.dto.basic.DTO;
import lombok.Data;
import software.amazon.awssdk.services.s3.model.Owner;

/**
 * <p>
 * 拥有者，目前是桶有该属性使用到了，类似 {@link Owner}
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 17:17
 */
@Data
public class GlobalOssOwner extends DTO {

	/**
	 * 唯一标识id
	 */
	private String id;
	/**
	 * 显示名称
	 */
	private String displayName;

	/**
	 * 原始对象
	 */
	private Object origin;

	public static GlobalOssOwner create(
			String id,
			String displayName
	) {
		GlobalOssOwner globalOssOwner = new GlobalOssOwner();
		globalOssOwner.setId(id);
		globalOssOwner.setDisplayName(displayName);

		return globalOssOwner;
	}
}
