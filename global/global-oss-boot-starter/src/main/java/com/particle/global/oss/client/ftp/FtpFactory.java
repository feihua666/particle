package com.particle.global.oss.client.ftp;

import cn.hutool.extra.ftp.Ftp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>
 * ftp 客户端工厂
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 11:21
 */
@Slf4j
public class FtpFactory extends BasePooledObjectFactory<Ftp> {

	private GlobalFtpOssProperties globalFtpOssProperties;

	public FtpFactory(GlobalFtpOssProperties globalFtpOssProperties) {
		this.globalFtpOssProperties = globalFtpOssProperties;
	}

	@Override
	public Ftp create() throws Exception {
		return GlobalFtpOssClient.initFtp(globalFtpOssProperties);
	}

	@Override
	public PooledObject<Ftp> wrap(Ftp ftp) {
		return new DefaultPooledObject<>(ftp);
	}

	@Override
	public void destroyObject(PooledObject<Ftp> p, DestroyMode destroyMode) throws Exception {
		log.info("destroy ftp destroyMode={}",destroyMode.name());
		p.getObject().close();
	}

	@Override
	public boolean validateObject(PooledObject<Ftp> p) {
		boolean r = p.getObject().getClient().isConnected();
		log.info("validate ftp result={}",r);
		return r;
	}
}
