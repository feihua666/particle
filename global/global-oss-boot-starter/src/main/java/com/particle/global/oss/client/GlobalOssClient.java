package com.particle.global.oss.client;

import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.oss.exception.BucketNotEmptyException;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 16:21
 */
public interface GlobalOssClient {

	/**
	 * 创建bucket,如果bucket已存在不作处理
	 * @param bucketName bucket名称
	 */
	void createBucket(String bucketName);

	/**
	 * 获取所有的bucket
	 * @return
	 */
	List<GlobalOssBucket> getAllBuckets();

	/**
	 * 通过bucket名称删除bucket，如果bucket下面有文件不能删除
	 * @param bucketName
	 * @throws BucketNotEmptyException
	 */
	void removeBucket(String bucketName);

	/**
	 * 上传文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @param inputStream 文件流，将会自动关闭流
	 * @throws Exception
	 */
	void putObject(String bucketName, String objectName, InputStream inputStream);

	/**
	 * 获取文件
	 * @param bucketName bucket名称
	 * @param objectName 文件名称
	 * @return 文件不存在将返回null
	 */
	GlobalOssObject getObject(String bucketName, String objectName);

	/**
	 * 通过bucketName和objectName删除对象
	 * 删除文件后，如果文件所在目录为空，自动删除目录
	 * @param bucketName
	 * @param objectName
	 */
	void removeObject(String bucketName, String objectName);

	/**
	 * 复制文件，默认覆盖
	 * @param sourceBucketName
	 * @param sourceObjectName
	 * @param destBucketName
	 * @param destObjectName
	 */
	void copyObject(String sourceBucketName, String sourceObjectName, String destBucketName, String destObjectName);

	/**
	 * 移动文件，默认覆盖
	 * @param sourceBucketName
	 * @param sourceObjectName
	 * @param destBucketName
	 * @param destObjectName
	 */
	default void moveObject(String sourceBucketName, String sourceObjectName, String destBucketName, String destObjectName) {
		copyObject(sourceBucketName, sourceObjectName, destBucketName, destObjectName);
		removeObject(sourceBucketName,sourceObjectName);
	}

	/**
	 * 获取标准的OSS客户端底层操作对象
	 * @return
	 */
	Object getClientObject();

	/**
	 * 关闭并释放资源
	 */
	void close();
}
