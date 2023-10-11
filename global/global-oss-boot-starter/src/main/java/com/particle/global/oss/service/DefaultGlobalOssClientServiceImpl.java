package com.particle.global.oss.service;

import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.system.SystemException;
import com.particle.global.oss.GlobalOssProperties;
import com.particle.global.oss.client.GlobalOssClient;
import com.particle.global.oss.client.aws.GlobalAwsOssClient;
import com.particle.global.oss.client.aws.GlobalAwsOssProperties;
import com.particle.global.oss.client.ftp.GlobalFtpOssClient;
import com.particle.global.oss.client.ftp.GlobalFtpOssProperties;
import com.particle.global.oss.client.local.GlobalLocalOssClient;
import com.particle.global.oss.client.local.GlobalLocalOssProperties;
import com.particle.global.oss.client.sftp.GlobalSftpOssClient;
import com.particle.global.oss.client.sftp.GlobalSftpOssProperties;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.tool.str.NetPathTool;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 全局对象存储客户端服务默认实现
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 20:28
 */
@Slf4j
public class DefaultGlobalOssClientServiceImpl implements GlobalOssClientService{

	public static final String defaultClientKey = "default";

	private GlobalOssProperties globalOssProperties;

	private Map<String, GlobalOssClientWrapper> clientMap = new HashMap<>();

	public DefaultGlobalOssClientServiceImpl(GlobalOssProperties globalOssProperties) {
		this.globalOssProperties = globalOssProperties;
		init();
	}

	@Override
	public String upload(String objectName, InputStream inputStream, String client,String contentType,Boolean concatEndPoint) {
		GlobalOssClientWrapper globalOssClientWrapper = routeGlobalOssClient(client);
		globalOssClientWrapper.getGlobalOssClient().putObject(globalOssClientWrapper.getBucketName(),objectName,inputStream,contentType);
		if ((concatEndPoint != null && concatEndPoint) || (concatEndPoint == null && globalOssProperties.getConcatEndpoint())) {
			return NetPathTool.concat(globalOssClientWrapper.getEndpoint(),objectName);
		}
		return objectName;
	}

	@Override
	public GlobalOssObject download(String objectName, String client) {
		GlobalOssClientWrapper globalOssClientWrapper = routeGlobalOssClient(client);
		GlobalOssObject globalOssObject = globalOssClientWrapper.getGlobalOssClient().getObject(globalOssClientWrapper.getBucketName(), objectName);
		return globalOssObject;
	}

	@Override
	public void delete(String objectName, String client) {
		GlobalOssClientWrapper globalOssClientWrapper = routeGlobalOssClient(client);
		globalOssClientWrapper.getGlobalOssClient().removeObject(globalOssClientWrapper.getBucketName(), objectName);
	}

	@Override
	public void copy(String sourceObjectName, String sourceClient, String destObjectName, String destClient) {
		GlobalOssClientWrapper sourceGlobalOssClientWrapper = routeGlobalOssClient(sourceClient);

		if (StrUtil.equals(sourceClient,destClient)) {
			sourceGlobalOssClientWrapper.getGlobalOssClient().copyObject(
					sourceGlobalOssClientWrapper.getBucketName(),
					sourceObjectName,
					sourceGlobalOssClientWrapper.getBucketName(),
					destObjectName
					);
			return;
		}
		GlobalOssObject sourceGlobalOssObject = sourceGlobalOssClientWrapper.getGlobalOssClient().getObject(sourceGlobalOssClientWrapper.getBucketName(), sourceObjectName);

		GlobalOssClientWrapper destGlobalOssClientWrapper = routeGlobalOssClient(destClient);
		destGlobalOssClientWrapper.getGlobalOssClient().putObject(destGlobalOssClientWrapper.getBucketName(),destObjectName,sourceGlobalOssObject.getObjectContent(),sourceGlobalOssObject.getContentType());

	}

	private GlobalOssClientWrapper routeGlobalOssClient(String client) {
		if (StrUtil.isEmpty(client)) {
			client = globalOssProperties.getDefaultClient();
		}
		GlobalOssClientWrapper globalOssClient = clientMap.get(client);
		if (globalOssClient == null) {
			throw new SystemException("can not find globalOssClient by client=" + client);
		}
		return globalOssClient;
	}

	/**
	 * 初始化
	 */
	private void init() {
		initAws();
		initFtp();
		initSftp();
		initLocal();
	}

	/**
	 * aws配置初始化
	 */
	private void initAws() {
		Map<String, GlobalAwsOssProperties> aws = globalOssProperties.getAws();
		if (aws != null && !aws.isEmpty()) {
			for (Map.Entry<String, GlobalAwsOssProperties> stringGlobalAwsOssPropertiesEntry : aws.entrySet()) {
				String client = stringGlobalAwsOssPropertiesEntry.getKey();
				GlobalAwsOssProperties awsOssProperties = stringGlobalAwsOssPropertiesEntry.getValue();
				checkKeyExist(client);
				clientMap.put(client, GlobalOssClientWrapper.create(
						awsOssProperties.getEndpoint(),
						awsOssProperties.getBucketName(),
						GlobalAwsOssClient.create(awsOssProperties)
						)
				);
			}
		}

	}
	/**
	 * ftp 配置初始化
	 */
	private void initFtp() {
		Map<String, GlobalFtpOssProperties> ftp = globalOssProperties.getFtp();
		if (ftp != null && !ftp.isEmpty()) {
			for (Map.Entry<String, GlobalFtpOssProperties> stringGlobalFtpOssPropertiesEntry : ftp.entrySet()) {
				String client = stringGlobalFtpOssPropertiesEntry.getKey();
				GlobalFtpOssProperties ftpOssProperties = stringGlobalFtpOssPropertiesEntry.getValue();
				checkKeyExist(client);
				clientMap.put(client, GlobalOssClientWrapper.create(
						ftpOssProperties.getEndpoint(),
						ftpOssProperties.getBucketName(),
						GlobalFtpOssClient.create(ftpOssProperties)
						)
				);
			}
		}

	}

	/**
	 * sftp配置初始化
	 */
	private void initSftp() {
		Map<String, GlobalSftpOssProperties> sftp = globalOssProperties.getSftp();
		if (sftp != null && !sftp.isEmpty()) {
			for (Map.Entry<String, GlobalSftpOssProperties> stringGlobalSftpOssPropertiesEntry : sftp.entrySet()) {
				String client = stringGlobalSftpOssPropertiesEntry.getKey();
				GlobalSftpOssProperties sftpOssProperties = stringGlobalSftpOssPropertiesEntry.getValue();
				checkKeyExist(client);
				clientMap.put(client, GlobalOssClientWrapper.create(
						sftpOssProperties.getEndpoint(),
						sftpOssProperties.getBucketName(),
						GlobalSftpOssClient.create(sftpOssProperties)
						)
				);
			}
		}

	}

	/**
	 * local配置初始化
	 */
	private void initLocal() {
		Map<String, GlobalLocalOssProperties> local = globalOssProperties.getLocal();
		if (local != null && !local.isEmpty()) {
			for (Map.Entry<String, GlobalLocalOssProperties> stringGlobalLocalOssPropertiesEntry : local.entrySet()) {
				String client = stringGlobalLocalOssPropertiesEntry.getKey();
				GlobalLocalOssProperties localOssProperties = stringGlobalLocalOssPropertiesEntry.getValue();
				checkKeyExist(client);
				clientMap.put(client, GlobalOssClientWrapper.create(
						localOssProperties.getEndpoint(),
						localOssProperties.getBucketName(),
						GlobalLocalOssClient.create(localOssProperties)
						)
				);
			}
		}

	}

	/**
	 * 检查key是否存在，主要是检查客户端配置
	 * @param client
	 */
	private void checkKeyExist(String client) {
		if (clientMap.containsKey(client)) {
			throw new IllegalStateException("oss config client key has already exist for " + client);
		}
	}

	/**
	 * 关闭资源
	 */
	@Override
	public void closeClients(){
		if (clientMap != null) {
			for (Map.Entry<String, GlobalOssClientWrapper> stringGlobalOssClientWrapperEntry : clientMap.entrySet()) {
				log.info("closing oss client {}", stringGlobalOssClientWrapperEntry.getKey());
				try {
					stringGlobalOssClientWrapperEntry.getValue().getGlobalOssClient().close();
					log.info("closed oss client {}", stringGlobalOssClientWrapperEntry.getKey());
				} catch (Exception e) {
					log.error("oss client {} close error",stringGlobalOssClientWrapperEntry.getKey(),e);
				}
			}
		}
	}
	/**
	 * 客户端包装
	 */
	@Setter
	@Getter
	public static class GlobalOssClientWrapper {
		private String endpoint;
		private String bucketName;
		private GlobalOssClient globalOssClient;

		public static GlobalOssClientWrapper create(String endpoint, String bucketName, GlobalOssClient globalOssClient) {
			GlobalOssClientWrapper globalOssClientWrapper = new GlobalOssClientWrapper();
			globalOssClientWrapper.setEndpoint(endpoint);
			globalOssClientWrapper.setBucketName(bucketName);
			globalOssClientWrapper.setGlobalOssClient(globalOssClient);

			return globalOssClientWrapper;
		}
	}
}
