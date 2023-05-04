package com.particle.global.oss.client.ftp;

import cn.hutool.extra.ftp.Ftp;
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
public class FtpPool {

	private ObjectPool<Ftp> ftpPool;

	public FtpPool(GlobalFtpOssProperties globalFtpOssProperties) {
		FtpFactory ftpFactory = new FtpFactory(globalFtpOssProperties);
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setMaxTotal(globalFtpOssProperties.getPool().getMaxTotal());
		genericObjectPoolConfig.setMaxIdle(globalFtpOssProperties.getPool().getMaxIdle());
		genericObjectPoolConfig.setMinIdle(globalFtpOssProperties.getPool().getMinIdle());

		ftpPool = new GenericObjectPool<>(ftpFactory, genericObjectPoolConfig);
	}


	public void close() {
		log.info("closing {}",FtpPool.class.getSimpleName());
		ftpPool.close();
	}
	public Ftp borrowObject() throws Exception {
		return ftpPool.borrowObject();
	}

	@SneakyThrows
	public void returnObject(Ftp ftpClient) {
		ftpPool.returnObject(ftpClient);
	}
}
