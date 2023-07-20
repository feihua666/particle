package com.particle.global.oss.endpoint.dto;

import com.particle.global.dto.basic.Command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 文件上传指令
 * Created by yangwei
 * Created at 2023-04-27 22:23:43
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Schema(description = "文件上传指令")
public class UploadCommand extends Command {

    @NotNull(message = "请选择要上传的文件")
    @Schema(description = "上传的文件",requiredMode = Schema.RequiredMode.REQUIRED,type = "org.springframework.web.multipart.MultipartFile")
    private MultipartFile file;

    @Schema(description = "上传的路径，如：header/photo")
    private String path;
}
