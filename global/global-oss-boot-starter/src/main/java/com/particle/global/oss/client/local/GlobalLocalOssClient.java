package com.particle.global.oss.client.local;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.global.oss.client.AbstractGlobalOssClient;
import com.particle.global.oss.dto.GlobalOssBucket;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.tool.str.NetPathTool;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 本地对象存储客户端
 * </p>
 *
 * @author yangwei
 * @since 2023-04-28 09:48
 */
public class GlobalLocalOssClient extends AbstractGlobalOssClient {

	private GlobalLocalOssProperties globalLocalOssProperties;

	public GlobalLocalOssClient(GlobalLocalOssProperties globalLocalOssProperties) {
		this.globalLocalOssProperties = globalLocalOssProperties;
	}

	@Override
	public void createBucket(String bucketName) {
		String fullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName);
		if (FileUtil.exist(fullPath)) {
			return;
		}
		FileUtil.mkdir(fullPath);

	}

	@Override
	public List<GlobalOssBucket> getAllBuckets() {
		File[] ls = FileUtil.ls(globalLocalOssProperties.getBasePath());
		if (ls == null) {
			return Collections.emptyList();
		}

		List<GlobalOssBucket> list = Arrays.stream(ls).filter(File::isDirectory)
				.map(f -> GlobalOssBucket.create(f.getName(), LocalDateTimeUtil.of(getFileInfo(f.getAbsolutePath()).getCreateAt()),f))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public void removeBucket(String bucketName) {
		String fullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName);
		ensureDir(fullPath, bucketName);
		FileUtil.del(fullPath);
	}

	@Override
	public void putObject(String bucketName, String objectName, InputStream inputStream,String contentType) {
		String fullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName,objectName);
		FileUtil.writeFromStream(inputStream, fullPath);

	}

	@Override
	public GlobalOssObject getObject(String bucketName, String objectName) {
		String fullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName,objectName);
		if (!FileUtil.exist(fullPath)) {
			return null;
		}
		ensureFile(fullPath, objectName);
		BufferedInputStream inputStream = FileUtil.getInputStream(fullPath);
		return GlobalOssObject.create(objectName, bucketName, inputStream);
	}

	@Override
	public void removeObject(String bucketName, String objectName) {
		String fullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName,objectName);
		if (!FileUtil.exist(fullPath)) {
			return;
		}
		ensureFile(fullPath, objectName);

		String parent = FileUtil.getParent(fullPath, 1);
		FileUtil.del(fullPath);
		// 如果目录下没有文件，将目录也删除，
		if (FileUtil.isDirEmpty(new File(parent))) {
			String bucketPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), bucketName);
			bucketPath = NetPathTool.ensureNotEndSlash(bucketPath);

			String objectParentPath = NetPathTool.ensureNotEndSlash(parent);
			// 判断一下，别把bucket也删除了
			if (!StrUtil.equals(bucketPath, objectParentPath)) {
				FileUtil.del(parent);
			}

		}
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceObjectName, String destBucketName, String destObjectName) {
		String sourceFullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), sourceBucketName,sourceObjectName);
		String destFullPath = NetPathTool.concat(globalLocalOssProperties.getBasePath(), destBucketName,destObjectName);
		ensureFile(sourceFullPath, sourceObjectName);

		FileUtil.copy(sourceFullPath, destFullPath, true);
	}

	@Override
	public Object getClientObject() {
		// 没有原始客户端
		return null;
	}

	@Override
	public void close() {

	}


	/**
	 * 断言是一个文件
	 * @param fileFullPath
	 * @param objectName
	 */
	private void ensureFile(String fileFullPath,String objectName) {
		boolean file = FileUtil.isFile(fileFullPath);
		Assert.isTrue(file,objectName + " is not a file!");
	}

	/**
	 * 断言是一个目录
	 * @param fileFullPath
	 * @param dir
	 */
	private void ensureDir(String fileFullPath,String dir) {
		boolean file = FileUtil.isDirectory(fileFullPath);
		Assert.isTrue(file,dir + " is not a dir!");
	}

	@SneakyThrows
	public FileInfo getFileInfo(String fileFullPath) {
		FileInfo ossInfo = null;

		Path path = Paths.get(fileFullPath);
		BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);

		FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();
		LocalDateTime lastModifiedAt = LocalDateTimeUtil.of(lastModifiedTime.toInstant());
		FileTime creationTime = basicFileAttributes.creationTime();
		LocalDateTime creationAt = LocalDateTimeUtil.of(creationTime.toInstant());
		long size = basicFileAttributes.size();
		//UserPrincipal owner = Files.getOwner(path);

		if (FileUtil.isFile(fileFullPath)) {
			ossInfo = FileInfo.createFile(creationAt,lastModifiedAt,size);
		} else {
			ossInfo = FileInfo.createDir(creationAt,lastModifiedAt);
		}

		return ossInfo;
	}

	@Data
	public static class FileInfo{
		/**
		 * 是否为文件或目录
		 * true = 文件，false = 目录
		 */
		private Boolean isFile;
		/**
		 * 创建时间
		 */
		private LocalDateTime createAt;
		/**
		 * 最后修改时间
		 */
		private LocalDateTime lastUpdateAt;

		/**
		 * 内容长度/大小 单位 字节
		 * 如果是目前为0
		 */
		private Long contentLength;


		public static FileInfo createFile(LocalDateTime createAt, LocalDateTime lastUpdateAt,Long contentLength) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setIsFile(true);
			fileInfo.setCreateAt(createAt);
			fileInfo.setLastUpdateAt(lastUpdateAt);
			fileInfo.setContentLength(contentLength);

			return fileInfo;
		}

		public static FileInfo createDir(LocalDateTime createAt, LocalDateTime lastUpdateAt) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setIsFile(false);
			fileInfo.setCreateAt(createAt);
			fileInfo.setLastUpdateAt(lastUpdateAt);
			fileInfo.setContentLength(0L);

			return fileInfo;
		}

	}

	public static GlobalLocalOssClient create(GlobalLocalOssProperties globalLocalOssProperties) {
		return new GlobalLocalOssClient(globalLocalOssProperties);
	}
}
