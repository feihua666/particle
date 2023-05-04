package com.particle.global.oss.client.sftp;

import cn.hutool.extra.ssh.Sftp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>
 * sftp 客户端工厂
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 11:21
 */
@Slf4j
public class SftpFactory extends BasePooledObjectFactory<Sftp> {

	private GlobalSftpOssProperties globalSftpOssProperties;

	public SftpFactory(GlobalSftpOssProperties globalSftpOssProperties) {
		this.globalSftpOssProperties = globalSftpOssProperties;
	}

	@Override
	public Sftp create() throws Exception {
		return GlobalSftpOssClient.initSftp(globalSftpOssProperties);
	}

	@Override
	public PooledObject<Sftp> wrap(Sftp sftp) {
		return new DefaultPooledObject<>(sftp);
	}

	@Override
	public void destroyObject(PooledObject<Sftp> p, DestroyMode destroyMode) throws Exception {
		log.info("destroy sftp destroyMode={}",destroyMode.name());
		p.getObject().close();
	}

	@Override
	public boolean validateObject(PooledObject<Sftp> p) {
		boolean r = p.getObject().getClient().isConnected();
		log.info("validate sftp result={}",r);
		return r;	}
}
