package com.particle.agi.client.rag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 知识存储原始文档 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentCreateCommand extends AbstractBaseCommand {

    @NotEmpty(message = "名称 不能为空")
    @Schema(description = "名称")
    private String name;

    @NotEmpty(message = "文件名称 不能为空")
    @Schema(description = "文件名称")
    private String fileName;

    @NotNull(message = "类型 不能为空")
    @Schema(description = "类型")
    private Long typeDictId;

    @NotEmpty(message = "地址 不能为空")
    @Schema(description = "地址")
    private String url;

    @Schema(description = "描述")
    private String remark;


}
