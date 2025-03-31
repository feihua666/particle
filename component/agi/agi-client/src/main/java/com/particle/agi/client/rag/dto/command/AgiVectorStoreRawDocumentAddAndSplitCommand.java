package com.particle.agi.client.rag.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 知识存储原始文档 添加分拆分指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-09 15:56:19
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentAddAndSplitCommand extends AbstractBaseCommand {

    @Schema(description = "名称")
    private String name;


    @Schema(description = "文件名称")
    private String fileName;


    @Schema(description = "类型")
    private Long typeDictId;


    @Schema(description = "地址")
    private String url;

    @Schema(description = "描述")
    private String remark;

}
