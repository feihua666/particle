package com.particle.global.oss.dto;

import com.particle.global.dto.basic.DTO;
import lombok.Data;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.time.LocalDateTime;

/**
 * <p>
 * 类似 s3 bucket {@link Bucket}，即为一个桶，桶下存储的为具体的文件对象 s3Object
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 17:10
 */
@Data
public class GlobalOssBucket extends DTO {

	/**
	 * 桶的名称
	 */
	private String name;

	/**
	 * 桶的创建时间
	 */
	private LocalDateTime createAt;

	/**
	 * 原始对象
	 */
	private Object origin;


	public static GlobalOssBucket create(
			String name,
			LocalDateTime createAt,
			Object origin
	) {
		GlobalOssBucket globalOssBucket = new GlobalOssBucket();
		globalOssBucket.setName(name);
		globalOssBucket.setCreateAt(createAt);
		globalOssBucket.setOrigin(origin);

		return globalOssBucket;
	}
}
