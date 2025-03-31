package com.particle.agi.client.rag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 知识存储原始文档 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "名称")
    private String name;


    @Schema(description = "文件名称")
    private String fileName;


    @Schema(description = "类型")
    private Long typeDictId;


    @Schema(description = "地址")
    private String url;


    @NotNull(message = "是否已嵌入 不能为空")
        @Schema(description = "是否已嵌入",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isEmbedded;


    @Schema(description = "描述")
    private String remark;









}
