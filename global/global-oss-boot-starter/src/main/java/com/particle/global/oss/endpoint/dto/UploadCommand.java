package com.particle.global.oss.endpoint.dto;

import com.particle.global.dto.basic.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 文件上传指令
 * Created by yangwei
 * Created at 2023-04-27 22:23:43
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="文件上传指令")
public class UploadCommand extends Command {

    @NotNull(message = "请选择要上传的文件")
    @ApiModelProperty(value = "上传的文件",required = true,dataType = "org.springframework.web.multipart.MultipartFile")
    private MultipartFile file;

    @ApiModelProperty(value = "上传的路径，如：header/photo")
    private String path;
}
