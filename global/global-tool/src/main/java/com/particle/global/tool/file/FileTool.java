package com.particle.global.tool.file;

import cn.hutool.core.io.FileUtil;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.util.Optional;

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
}
