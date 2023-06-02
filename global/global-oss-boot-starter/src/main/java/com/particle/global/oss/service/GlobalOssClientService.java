package com.particle.global.oss.service;

import com.particle.global.oss.dto.GlobalOssObject;

import java.io.InputStream;

/**
 * <p>
 * 全局对象存储客户端服务
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 20:18
 */
public interface GlobalOssClientService {

	/**
	 * 上传
	 * @param objectName 存储对象名称
	 * @param inputStream 上传的文件输入流
	 * @param client 表示使用哪个客户端，为空表示使用默认的客户端
	 * @return 存储对象可访问的 web 绝对地址
	 */
	String upload(String objectName, InputStream inputStream, String client,String contentType);

	/**
	 * 使用默认的客户端上传
	 * @param objectName 存储对象名称
	 * @param inputStream 上传的文件输入流
	 * @return 存储对象可访问的 web 绝对地址
	 */
	default String upload(String objectName, InputStream inputStream,String contentType){
		return upload(objectName, inputStream, null,contentType);
	}

	/**
	 * 下载
	 * @param objectName 存储对象名称
	 * @param client 表示使用哪个客户端，为空表示使用默认的客户端
	 * @return 下载的文件流
	 */
	GlobalOssObject download(String objectName, String client);

	/**
	 * 使用默认的客户端下载
	 * @param objectName 存储对象名称
	 * @return 下载的文件流
	 */
	default GlobalOssObject download(String objectName){
		return download(objectName, null);
	}

	/**
	 * 删除
	 * @param objectName 存储对象名称
	 * @param client 表示使用哪个客户端，为空表示使用默认的客户端
	 */
	void delete(String objectName, String client);
	/**
	 * 使用默认的客户端删除
	 * @param objectName 存储对象名称
	 */
	default void delete(String objectName) {
		delete(objectName,null);
	}

	/**
	 * 复制
	 * @param sourceObjectName 源存储对象名称
	 * @param sourceClient 表示使用哪个客户端，为空表示使用默认的客户端
	 * @param destObjectName 目的存储对象名称
	 * @param destClient 表示使用哪个客户端，为空表示使用默认的客户端
	 */
	void copy(String sourceObjectName, String sourceClient,String destObjectName,String destClient);

	/**
	 * 使用默认客户端复制
	 * @param sourceObjectName
	 * @param destObjectName
	 */
	default void copy(String sourceObjectName, String destObjectName) {
		copy(sourceObjectName,null,destObjectName,null);
	}


	public void closeClients();
}
