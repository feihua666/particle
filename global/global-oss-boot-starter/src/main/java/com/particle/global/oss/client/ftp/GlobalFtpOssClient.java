package com.particle.global.oss.client.ftp;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpConfig;
import cn.hutool.extra.ftp.FtpException;
import com.particle.global.oss.client.AbstractGlobalOssClient;
import com.particle.global.oss.client.local.GlobalLocalOssClient;
import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.tool.str.NetPathTool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
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
public class GlobalFtpOssClient extends AbstractGlobalOssClient {


	private GlobalFtpOssProperties globalFtpOssProperties;

	private volatile Ftp ftp;

	private FtpPool ftpPool;

	public GlobalFtpOssClient(GlobalFtpOssProperties globalFtpOssProperties) {
		this.globalFtpOssProperties = globalFtpOssProperties;
		if (globalFtpOssProperties.getUsePool()) {
			this.ftpPool = new FtpPool(globalFtpOssProperties);
		}
	}

	@Override
	public void createBucket(String bucketName) {
		Ftp ftp = getFtp();
		try {
			String fullPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName);
			if (ftp.exist(fullPath)) {
				return;
			}
			ftp.mkdir(fullPath);
		} finally {

			returnFtp(ftp);
		}
	}

	@Override
	public List<GlobalOssBucket> getAllBuckets() {
		Ftp ftp = getFtp();

		try {
			List<FTPFile> ftpFiles = ftp.lsFiles(globalFtpOssProperties.getBasePath(), ftpFile -> ftpFile.isDirectory());
			if (ftpFiles == null) {
				return Collections.emptyList();
			}

			List<GlobalOssBucket> list = ftpFiles.stream()
					.map(f -> GlobalOssBucket.create(f.getName(), getFileInfo(f).getCreateAt(),f))
					.collect(Collectors.toList());

			return list;
		} finally {
			returnFtp(ftp);
		}
	}

	@Override
	public void removeBucket(String bucketName) {
		Ftp ftp = getFtp();
		try {
			String fullPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName);
			ensureDir(fullPath, bucketName,ftp);
			ftp.delDir(fullPath);
		} finally {
			returnFtp(ftp);
		}
	}

	@SneakyThrows
	@Override
	public void putObject(String bucketName, String objectName, InputStream inputStream) {
		Ftp ftp = getFtp();
		try {
			String objectParent = objectName.substring(0, objectName.lastIndexOf(NetPathTool.SLASH));
			String objectSimpleName = objectName.substring(objectName.lastIndexOf(NetPathTool.SLASH) + 1);
			String parentPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName,objectParent);

			ftp.upload(parentPath,objectSimpleName,inputStream);
			IoUtil.close(inputStream);
		} finally {
			returnFtp(ftp);
		}
	}

	@Override
	public GlobalOssObject getObject(String bucketName, String objectName) {
		Ftp ftp = getFtp();
		try {
			String objectParent = objectName.substring(0, objectName.lastIndexOf(NetPathTool.SLASH));
			String objectSimpleName = objectName.substring(objectName.lastIndexOf(NetPathTool.SLASH) + 1);
			String parentPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName,objectParent);
			String fullPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName,objectName);

			if (!ftp.exist(fullPath)) {
				return null;
			}
			ensureFile(fullPath, objectName,ftp);

			InputStream inputStream = download(parentPath, objectSimpleName, null,ftp);
			return GlobalOssObject.create(objectName, bucketName, inputStream);
		} finally {
			returnFtp(ftp);
		}
	}

	@Override
	public void removeObject(String bucketName, String objectName) {
		Ftp ftp = getFtp();
		try {
			String fullPath = NetPathTool.concat(globalFtpOssProperties.getBasePath(), bucketName,objectName);

			if (!ftp.exist(fullPath)) {
				return;
			}
			ensureFile(fullPath, objectName,ftp);
			ftp.delFile(fullPath);
		} finally {
			returnFtp(ftp);
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
		return getFtp();
	}

	@SneakyThrows
	@Override
	public void close() {
		if (globalFtpOssProperties.getUsePool()) {
			ftpPool.close();
			return;
		}
		if (ftp != null) {
			ftp.close();
		}
	}

	/**
	 * 断言是一个文件
	 * @param fileFullPath
	 * @param objectName
	 */
	private void ensureFile(String fileFullPath,String objectName,Ftp ftp) {
		// todo 远程操作 需要列表所以文件才能判断，耗费性能，暂不实现
	}

	/**
	 * 断言是一个目录
	 * @param fileFullPath
	 * @param dir
	 */
	private static void ensureDir(String fileFullPath,String dir,Ftp ftp) {
		// todo 远程操作 需要列表所以文件才能判断，耗费性能，暂不实现

	}

	/**
	 * 下载文件，返回输入流
	 * 拷贝自 {@link Ftp#download(java.lang.String, java.lang.String, java.io.OutputStream, java.nio.charset.Charset)} 并作了修改
	 *
	 * @param path            服务端的文件路径
	 * @param fileName        服务端的文件名
	 * @param fileNameCharset 文件名编码，通过此编码转换文件名编码为ISO8859-1
	 * @throws IORuntimeException IO异常
	 * @since 5.5.7
	 */
	public InputStream download(String path, String fileName, Charset fileNameCharset,Ftp ftp) throws IORuntimeException {
		String pwd = null;
		if (ftp.isBackToPwd()) {
			pwd = ftp.pwd();
		}

		if (false == ftp.cd(path)) {
			throw new FtpException("Change dir to [{}] error pwd={}, maybe dir not exist!", path,ftp.pwd());
		}

		if (null != fileNameCharset) {
			fileName = new String(fileName.getBytes(fileNameCharset), StandardCharsets.ISO_8859_1);
		}
		try {
			ftp.getClient().setFileType(FTPClient.BINARY_FILE_TYPE);
			InputStream inputStream = ftp.getClient().retrieveFileStream(fileName);
			ftp.getClient().completePendingCommand();
			return inputStream;
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			if (ftp.isBackToPwd()) {
				ftp.cd(pwd);
			}
		}
	}
	/**
	 * 获取 ftp 实例
	 * @return
	 */
	@SneakyThrows
	private Ftp getFtp() {

		if (globalFtpOssProperties.getUsePool()) {
			return ftpPool.borrowObject();
		}

		if (ftp == null) {
			synchronized(this) {
				if (ftp == null) {
					ftp = initFtp(globalFtpOssProperties);
				}
			}
		}
		return ftp;
	}

	/**
	 * 归还
	 * @param ftp
	 */
	private void returnFtp(Ftp ftp) {
		if (globalFtpOssProperties.getUsePool()) {
			ftpPool.returnObject(ftp);
		}
	}
	/**
	 * ftp实例
	 * @return
	 */
	public static Ftp initFtp(GlobalFtpOssProperties globalFtpOssProperties) {
		log.info("ftp initializing for host={},port={},bucketName={}",globalFtpOssProperties.getHost(),globalFtpOssProperties.getPort(),globalFtpOssProperties.getBucketName());

		FtpConfig ftpConfig = new FtpConfig(
				globalFtpOssProperties.getHost(),
				globalFtpOssProperties.getPort(),
				globalFtpOssProperties.getUser(),
				globalFtpOssProperties.getPassword(),
				Charset.forName(globalFtpOssProperties.getCharset()),
				null,
				null
		);
		ftpConfig.setConnectionTimeout(globalFtpOssProperties.getConnectionTimeout());
		ftpConfig.setSoTimeout(globalFtpOssProperties.getSocketTimeout());
		Ftp ftp = new Ftp(ftpConfig, globalFtpOssProperties.getMode());
		ftp.setBackToPwd(true);
		log.info("ftp initialized for host={},port={},bucketName={}",globalFtpOssProperties.getHost(),globalFtpOssProperties.getPort(),globalFtpOssProperties.getBucketName());

		return ftp;
	}

	public GlobalLocalOssClient.FileInfo getFileInfo(FTPFile file) {
		GlobalLocalOssClient.FileInfo ossInfo = null;

		Instant timestampInstant = file.getTimestampInstant();
		LocalDateTime lastModifiedAt = null;
		if (timestampInstant != null) {
			lastModifiedAt = LocalDateTimeUtil.of(timestampInstant);
		}

		if (file.isFile()) {
			ossInfo = GlobalLocalOssClient.FileInfo.createFile(lastModifiedAt,lastModifiedAt,file.getSize());
		} else {
			ossInfo = GlobalLocalOssClient.FileInfo.createDir(lastModifiedAt,lastModifiedAt);
		}

		return ossInfo;
	}

	public static GlobalFtpOssClient create(GlobalFtpOssProperties globalFtpOssProperties) {
		return new GlobalFtpOssClient(globalFtpOssProperties);
	}

}
