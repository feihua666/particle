package com.particle.global.oss.dto;

import com.particle.global.dto.basic.DTO;
import software.amazon.awssdk.services.s3.model.S3Object ;
import lombok.Data;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * <p>
 * 存储对象 类似 s3Object {@link S3Object}
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 17:24
 */
@Data
public class GlobalOssObject extends DTO  implements Closeable {


	/**
	 * 储存对象的key值，该值代表对象的唯一标识或者存储位置
	 */
	private String key;

	/**
	 * 存储对象所在的桶名称
	 */
	private String bucketName;

	/**
	 * 存储对象内容流，读取数据完成后请使用 close()方法关闭流
	 * 注意：该变量使用 transient 修饰，这意味着在对象序列化时将忽略该字段
	 */
	private transient InputStream objectContent;

	/**
	 * 对象大小,单位字节
	 */
	private Long contentLength;
	/**
	 * 内容类型如：application/json 是一个MediaType
	 */
	private String contentType;
	/**
	 * 创建时间
	 */
	private LocalDateTime createAt;
	/**
	 * 最新修改时间
	 */
	private LocalDateTime lastUpdateAt;

	/**
	 * 原始对象
	 */
	private Object origin;

	@Override
	public void close() throws IOException {
		InputStream is = getObjectContent();
		if (is != null){
			is.close();
		}

	}


	public static GlobalOssObject create(String key, String bucketName, InputStream objectContent) {
		GlobalOssObject globalOssObject = new GlobalOssObject();
		globalOssObject.setKey(key);
		globalOssObject.setBucketName(bucketName);
		globalOssObject.setObjectContent(objectContent);
		return globalOssObject;
	}
}
