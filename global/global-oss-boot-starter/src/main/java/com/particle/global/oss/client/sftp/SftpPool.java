package com.particle.global.oss.client.sftp;

import cn.hutool.extra.ssh.Sftp;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * <p>
 * ftp 客户端池
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 11:10
 */
@Slf4j
public class SftpPool {

	private ObjectPool<Sftp> ftpPool;

	public SftpPool(GlobalSftpOssProperties globalSftpOssProperties) {
		SftpFactory SFtpFactory = new SftpFactory(globalSftpOssProperties);
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxTotal(globalSftpOssProperties.getPool().getMaxTotal());
		genericObjectPoolConfig.setMaxIdle(globalSftpOssProperties.getPool().getMaxIdle());
		genericObjectPoolConfig.setMinIdle(globalSftpOssProperties.getPool().getMinIdle());

		ftpPool = new GenericObjectPool<>(SFtpFactory, genericObjectPoolConfig);
	}


	public void close() {
		log.info("closing {}",SftpPool.class.getSimpleName());
		ftpPool.close();
	}
	public Sftp borrowObject() throws Exception {
		return ftpPool.borrowObject();
	}

	@SneakyThrows
	public void returnObject(Sftp ftpClient) {
		ftpPool.returnObject(ftpClient);
	}
}
