package com.particle.global.oss.endpoint;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.particle.global.oss.dto.GlobalOssObject;
import com.particle.global.oss.endpoint.dto.GlobalOssVO;
import com.particle.global.oss.endpoint.dto.UploadCommand;
import com.particle.global.oss.service.GlobalOssClientService;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.tool.file.FileTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * <p>
 * oss 对外 http 服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 09:33
 */
@Slf4j
@RestController
@RequestMapping(OssController.API_REQUEST_MAPPING)
@Api(tags = "对象存储上传下载接口")
public class OssController {
	public static final String API_REQUEST_MAPPING = "/oss";
	public static final String API_DOWNLOAD = "/download";
	public static final String API_DOWNLOAD_PREFIX = API_REQUEST_MAPPING + API_DOWNLOAD;

	@Autowired
	private GlobalOssClientService globalOssClientService;

	/**
	 * 文件上传 通用上传接口
	 *
	 * @param uploadCommand
	 * @return 返回文件的绝对路径
	 * @throws IOException
	 */
	@ApiOperation("文件上传")
	@PostMapping(value = "/upload",consumes = "multipart/*",headers = "content-type=multipart/form-data")
	@ResponseStatus(HttpStatus.CREATED)
	public GlobalOssVO upload(@Valid UploadCommand uploadCommand) throws IOException {

		MultipartFile file = uploadCommand.getFile();
		String originalFilename = file.getOriginalFilename();
		InputStream inputStream = file.getInputStream();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String newFileName = IdUtil.fastSimpleUUID()+ "--" + originalFilename;
		String objectName =  Optional.ofNullable(uploadCommand.getPath()).orElse("") + "/" + DateUtil.date().toDateStr() + "/" + newFileName;
		Long fileLength = file.getSize();
		log.info("文件上传开始，path={},originalFilename={},fileLength={},objectName={}",uploadCommand.getPath(),originalFilename,fileLength,objectName);

		String mimeType = FileTool.getMimeType(objectName);
		String upload = globalOssClientService.upload(objectName, inputStream,mimeType);
		IoUtil.close(inputStream);

		log.info("文件上传结束,uploadResult={}",upload);
		return GlobalOssVO.create(upload, extension, originalFilename, fileLength);
	}

	/**
	 * 下载
	 * @param request
	 */
	@ApiOperation("下载文件或预览文件")
	@GetMapping(API_DOWNLOAD + "/**")
	public void download(HttpServletRequest request, HttpServletResponse response, String objectName, String client) throws Throwable {
		String finalObjectName = getObjectName(request, objectName);

		GlobalOssObject globalOssObject = globalOssClientService.download(finalObjectName, client);
		if (globalOssObject.getObjectContent() != null) {
			if (Strings.isEmpty(globalOssObject.getContentType())) {
			//	尝试获取
				String mimeType = FileTool.getMimeType(finalObjectName);
				if (Strings.isNotEmpty(mimeType)) {
					response.setContentType(mimeType);
				}
			}
			OutputStream stream = response.getOutputStream();
			IoUtil.copy(globalOssObject.getObjectContent(),stream);
			IoUtil.close(globalOssObject.getObjectContent());
			IoUtil.close(stream);
		}
	}
	/**
	 * 删除是一个危险的操作，只有超级管理员可使用
	 * @param request
	 */
	@PreAuthorize("hasRole("+ LoginUser.super_admin_role +")")
	@DeleteMapping( API_DOWNLOAD + "/**")
	@ApiOperation("删除文件，只能超级管理员才能删除")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(HttpServletRequest request,String objectName, String client){
		String finalObjectName = getObjectName(request, objectName);
		globalOssClientService.delete(finalObjectName,client);
	}

	/**
	 * 获取文件对象名称
	 * @param request
	 * @param objectName
	 * @return
	 */
	private String getObjectName(HttpServletRequest request, String objectName) {
		String requestURI = request.getRequestURI();
		requestURI = URLUtil.decode(requestURI);
		String filePath = requestURI.replaceFirst(getPrefix(), "");
		if (StrUtil.equalsAny(filePath, "", "/", "/**")) {
			filePath = objectName;
			// 不能为空或直接拼接在请求地址上
			Assert.hasText(objectName, "objectName can not be null");
		}
		return filePath;
	}

	/**
	 * 获取下载前缀
	 * @return
	 */
	public String getPrefix(){
		return API_DOWNLOAD_PREFIX;
	}
}
