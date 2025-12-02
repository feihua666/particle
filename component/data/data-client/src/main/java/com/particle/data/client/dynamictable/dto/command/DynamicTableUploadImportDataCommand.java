package com.particle.data.client.dynamictable.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * 动态数据表格上传导入数据 创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-09 17:52:35
 */
@Data
@Schema
public class DynamicTableUploadImportDataCommand extends AbstractBaseCommand {

    @NotNull(message = "请选择要上传的文件")
    @Schema(description = "上传的文件",requiredMode = Schema.RequiredMode.REQUIRED,type = "org.springframework.web.multipart.MultipartFile")
    private MultipartFile file;

    @NotNull(message = "动态数据表格id 不能为空")
    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

}
