package com.particle.global.tool.file;

import cn.hutool.core.io.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * <p>
 * 文件相关工具类
 * </p>
 *
 * @author yangwei
 * @since 2023-06-01 16:12
 */
public class FileTool {

	/**
	 * 根据文件名获取媒体类型
	 * @param filenameOrFilePath
	 * @return 如：application/json 文件名为xxxx.json
	 */
	public static String getMimeType(String filenameOrFilePath) {
		String result = null;

		Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(filenameOrFilePath);
		if (mediaType.isPresent()) {
			result = mediaType.get().toString();
		}
		if (result != null) {
			return result;
		}

		try {
			result = FileUtil.getMimeType(filenameOrFilePath);
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * 获取目录下的文件列表
	 * @param predicate 自定义检测条件
	 * @param parentAbsoluteDirPath 父目录绝对路径
	 * @param maxLevel 最大层级从 1 开始
	 * @param maxFileCount 最大找到的文件数量
	 * @return
	 */
	public static List<File> getFiles(Predicate<File> predicate,
									  String parentAbsoluteDirPath,
									  Integer maxLevel,
									  Integer maxFileCount) {
		AtomicInteger currentFileCount = new AtomicInteger(0);
		return getFiles(predicate, parentAbsoluteDirPath, maxLevel, maxFileCount, currentFileCount);
	}

	/**
	 * 获取目录下的文件列表
	 * @param predicate 自定义检测条件
	 * @param parentAbsoluteDirPath 父目录绝对路径
	 * @param maxLevel 最大层级从 1 开始
	 * @param maxFileCount 最大找到的文件数量
	 * @param currentFileCount 记录当前的已查找的文件数量
	 * @return
	 */
	private static List<File> getFiles(Predicate<File> predicate,
									   String parentAbsoluteDirPath,
									   Integer maxLevel,
									   Integer maxFileCount,
									   AtomicInteger currentFileCount) {
		List<File> result = new ArrayList<>();
		if (maxLevel <= 0) {
			return result;
		}
		File[] ls = FileUtil.ls(parentAbsoluteDirPath);
		for (File file : ls) {
			if (currentFileCount.get() >= maxFileCount) {
				break;
			}
			if (file.isDirectory()) {
				List<File> files = getFiles(predicate, file.getAbsolutePath(), maxLevel - 1,maxFileCount,currentFileCount);
				result.addAll(files);
			}else {
				if (predicate.test(file)) {
					currentFileCount.incrementAndGet();
					result.add(file);
				}
			}
		}
		return result;
	}
}
