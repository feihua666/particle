package com.particle.global.oss.client.sftp;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.FtpConfig;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.particle.global.exception.Assert;
import com.particle.global.oss.client.AbstractGlobalOssClient;
import com.particle.global.oss.client.local.GlobalLocalOssClient;
import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.tool.str.NetPathTool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * ftp远程存储客户端
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 11:04
 */
@Slf4j
public class GlobalSftpOssClient extends AbstractGlobalOssClient {


	private GlobalSftpOssProperties globalSftpOssProperties;

	private volatile Sftp sftp;
	private SftpPool sftpPool;

	public GlobalSftpOssClient(GlobalSftpOssProperties globalSftpOssProperties) {
		this.globalSftpOssProperties = globalSftpOssProperties;
	}

	@Override
	public void createBucket(String bucketName) {
		Sftp sftp = getSftp();
		try {
			String fullPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName);
			if (sftp.exist(fullPath)) {
				return;
			}
			sftp.cd(globalSftpOssProperties.getBasePath());
			sftp.mkdir(bucketName);
		} finally {
			returnSftp(sftp);
		}
	}

	@Override
	public List<GlobalOssBucket> getAllBuckets() {
		Sftp sftp = getSftp();

		try {
			List<ChannelSftp.LsEntry> lsEntries = sftp.lsEntries(globalSftpOssProperties.getBasePath(), ftpFile -> ftpFile.getAttrs().isDir());
			if (lsEntries == null) {
				return Collections.emptyList();
			}

			List<GlobalOssBucket> list = lsEntries.stream()
					.map(f -> GlobalOssBucket.create(f.getFilename(), getFileInfo(f).getCreateAt(),f))
					.collect(Collectors.toList());

			return list;
		} finally {
			returnSftp(sftp);
		}
	}

	@Override
	public void removeBucket(String bucketName) {
		Sftp sftp = getSftp();
		try {
			String fullPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName);
			ensureDir(fullPath, bucketName,sftp);
			sftp.delDir(fullPath);
		} finally {
			returnSftp(sftp);
		}
	}

	@Override
	public void putObject(String bucketName, String objectName, InputStream inputStream) {
		Sftp sftp = getSftp();
		try {
			String objectParent = objectName.substring(0, objectName.lastIndexOf(NetPathTool.SLASH));
			String objectSimpleName = objectName.substring(objectName.lastIndexOf(NetPathTool.SLASH) + 1);
			String parentPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName,objectParent);

			upload(parentPath, objectSimpleName, inputStream, sftpProgressMonitor(bucketName,objectName,"putObject"),sftp);

			IoUtil.close(inputStream);
		} finally {
			returnSftp(sftp);
		}
	}

	@SneakyThrows
	@Override
	public GlobalOssObject getObject(String bucketName, String objectName) {
		Sftp sftp = getSftp();
		try {
			String objectParent = objectName.substring(0, objectName.lastIndexOf(NetPathTool.SLASH));
			String objectSimpleName = objectName.substring(objectName.lastIndexOf(NetPathTool.SLASH) + 1);
			String parentPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName,objectParent);
			String fullPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName,objectName);

			if (!sftp.exist(fullPath)) {
				return null;
			}
			ensureFile(fullPath, objectName,sftp);

			InputStream inputStream = download(parentPath, objectSimpleName, null,sftpProgressMonitor(bucketName,objectName,"getObject"),sftp);
			return GlobalOssObject.create(objectName, bucketName, inputStream);
		} finally {
			returnSftp(sftp);
		}
	}

	@Override
	public void removeObject(String bucketName, String objectName) {
		Sftp sftp = getSftp();
		try {
			String fullPath = NetPathTool.concat(globalSftpOssProperties.getBasePath(), bucketName,objectName);

			if (!sftp.exist(fullPath)) {
				return;
			}
			ensureFile(fullPath, objectName,sftp);
			sftp.delFile(fullPath);
		} finally {
			returnSftp(sftp);
		}
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectName, String destBucketName, String destObjectName) {
		GlobalOssObject globalOssObject = getObject(sourceBucketName, sourceObjectName);
		if (globalOssObject != null) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			IoUtil.copy(globalOssObject.getObjectContent(), byteArrayOutputStream);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

			putObject(destBucketName,destObjectName,byteArrayInputStream);

			IoUtil.close(globalOssObject.getObjectContent());
		}
	}

	@Override
	public Object getClientObject() {
		return getSftp();
	}

	@Override
	public void close() {
		if (globalSftpOssProperties.getUsePool()) {
			sftpPool.close();
			return;
		}
		if (sftp != null) {
			sftp.close();
		}
	}
	/**
	 * 归还
	 * @param sftp
	 */
	private void returnSftp(Sftp sftp) {
		if (globalSftpOssProperties.getUsePool()) {
			sftpPool.returnObject(sftp);
		}
	}
	private SftpProgressMonitor sftpProgressMonitor(String bucketName,String objectName,String hit){
		return new SftpProgressMonitor() {
			private long max = 0;
			private long count = 0;
			/**
			 *
			 * @param op 0 = 下载，1 = 上传
			 * @param src
			 * @param dest
			 * @param max
			 */
			@Override
			public void init(int op, String src, String dest, long max) {
				this.max = max;
				log.info("{} start,bucketName={},objectName={},op={},src={},dest={},max={}",
						hit, bucketName,objectName,op,src,dest,max);
			}

			@Override
			public boolean count(long count) {
				this.count += count;
				if (this.max == -1) {
					log.info("{} bucketName={},objectName={},transferred: {}" ,hit, bucketName,objectName, this.count);
				}else {
					log.info("{} bucketName={},objectName={},transferred: {}%" ,hit, bucketName,objectName, this.count / this.max);

				}				return true;
			}

			@Override
			public void end() {
				log.info("{} end,bucketName={},objectName={}",hit, bucketName,objectName);
			}
		};
	}
	/**
	 * 断言是一个文件
	 * @param fileFullPath
	 * @param objectName
	 */
	private void ensureFile(String fileFullPath,String objectName,Sftp sftp) {
		// todo 远程操作 需要列表所以文件才能判断，耗费性能，暂不实现
		boolean file = !sftp.isDir(fileFullPath);
		Assert.isTrue(file,objectName + " is not a file!");
	}

	/**
	 * 断言是一个目录
	 * @param fileFullPath
	 * @param dir
	 */
	private void ensureDir(String fileFullPath,String dir,Sftp sftp) {
		boolean file = sftp.isDir(fileFullPath);
		Assert.isTrue(file,dir + " is not a dir!");
	}

	/**
	 * 拷贝自 {@link Sftp#upload(java.lang.String, java.lang.String, java.io.InputStream)} 并添加了监控参数
	 * 上传文件到指定目录，可选：
	 *
	 * <pre>
	 * 1. path为null或""上传到当前路径
	 * 2. path为相对路径则相对于当前路径的子路径
	 * 3. path为绝对路径则上传到此路径
	 * </pre>
	 *
	 * @param destPath   服务端路径，可以为{@code null} 或者相对路径或绝对路径
	 * @param fileName   文件名
	 * @param inputStream 文件流
	 * @return 是否上传成功
	 * @since 5.7.16
	 */
	public boolean upload(String destPath, String fileName, InputStream inputStream, SftpProgressMonitor monitor,Sftp sftp) {
		String fullDestPath = StrUtil.addSuffixIfNot(destPath, StrUtil.SLASH) + StrUtil.removePrefix(fileName, StrUtil.SLASH);
		if (!sftp.exist(destPath)) {
			sftp.mkDirs(destPath);
		}
		sftp.put(inputStream, fullDestPath, monitor, Sftp.Mode.OVERWRITE);
		return true;
	}

	/**
	 * 下载文件，返回输入流
	 * @param path
	 * @param fileName
	 * @param fileNameCharset
	 * @return
	 * @throws SftpException
	 */
	public InputStream download(String path, String fileName, Charset fileNameCharset,SftpProgressMonitor sftpProgressMonitor,Sftp sftp) throws SftpException {

		if (false == sftp.cd(path)) {
			throw new SftpException(-1,StrUtil.format("Change dir to [{}] error, maybe dir not exist!", path));
		}

		if (null != fileNameCharset) {
			fileName = new String(fileName.getBytes(fileNameCharset), StandardCharsets.ISO_8859_1);
		}

		String fullPath = NetPathTool.concat(path, fileName);
		return sftp.getClient().get(fullPath,sftpProgressMonitor);
	}
	/**
	 * 获取 ftp 实例
	 * @return
	 */
	@SneakyThrows
	private Sftp getSftp() {
		if (globalSftpOssProperties.getUsePool()) {
			return sftpPool.borrowObject();
		}
		if (sftp == null) {
			synchronized(this) {
				if (sftp == null) {
					sftp = initSftp(globalSftpOssProperties);
				}
			}
		}
		return sftp;
	}
	/**
	 * ftp实例
	 * @return
	 */
	@SneakyThrows
	public static Sftp initSftp(GlobalSftpOssProperties globalSftpOssProperties) {
		log.info("sftp initializing for host={},port={},bucketName={}",globalSftpOssProperties.getHost(),globalSftpOssProperties.getPort(),globalSftpOssProperties.getBucketName());

		FtpConfig ftpConfig = new FtpConfig(
				globalSftpOssProperties.getHost(),
				globalSftpOssProperties.getPort(),
				globalSftpOssProperties.getUser(),
				globalSftpOssProperties.getPassword(),
				Charset.forName(globalSftpOssProperties.getCharset()),
				null,
				null
		);
		ftpConfig.setConnectionTimeout(globalSftpOssProperties.getConnectionTimeout());
		ftpConfig.setSoTimeout(globalSftpOssProperties.getSocketTimeout());
		Sftp sftp = new Sftp(ftpConfig, true);
		log.info("sftp initialized for host={},port={},bucketName={}",globalSftpOssProperties.getHost(),globalSftpOssProperties.getPort(),globalSftpOssProperties.getBucketName());
		log.info("sftp initialized home={}",sftp.getClient().getHome());
		return sftp;
	}

	public GlobalLocalOssClient.FileInfo getFileInfo(ChannelSftp.LsEntry file) {
		GlobalLocalOssClient.FileInfo ossInfo = null;

		int aTime = file.getAttrs().getATime();
		int mTime = file.getAttrs().getMTime();
		LocalDateTime createAt = LocalDateTimeUtil.of(aTime);
		LocalDateTime lastModifiedAt = LocalDateTimeUtil.of(mTime);

		if (!file.getAttrs().isDir()) {
			ossInfo = GlobalLocalOssClient.FileInfo.createFile(createAt,lastModifiedAt,file.getAttrs().getSize());
		} else {
			ossInfo = GlobalLocalOssClient.FileInfo.createDir(createAt,lastModifiedAt);
		}

		return ossInfo;
	}

	public static GlobalSftpOssClient create(GlobalSftpOssProperties globalSftpOssProperties) {
		return new GlobalSftpOssClient(globalSftpOssProperties);
	}
}
