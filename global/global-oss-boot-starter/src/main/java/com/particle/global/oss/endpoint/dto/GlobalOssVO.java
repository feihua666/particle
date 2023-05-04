package com.particle.global.oss.endpoint.dto;

import com.particle.global.dto.basic.VO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 对象存储响应对象
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 22:16
 */
@Data
@ApiModel(value="对象存储响应对象")
public class GlobalOssVO extends VO {

	@ApiModelProperty(value = "web可访问存储对象绝对地址")
	private String absoluteHttpUrl;

	@ApiModelProperty(value = "文件扩展名,带点")
	private String extension;

	@ApiModelProperty(value = "原始文件名称")
	private String originFileName;

	@ApiModelProperty(value = "文件大小，单位字节")
	private Long fileLength;

	public static GlobalOssVO create(String absoluteHttpUrl,String extension,String originFileName,Long fileLength){
		GlobalOssVO globalOssVO = new GlobalOssVO();
		globalOssVO.setAbsoluteHttpUrl(absoluteHttpUrl);
		globalOssVO.setExtension(extension);
		globalOssVO.setOriginFileName(originFileName);
		globalOssVO.setFileLength(fileLength);

		return globalOssVO;
	}
}
