package com.particle.agi.client.rag.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 知识存储原始文档 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Data
@Schema
public class AgiVectorStoreRawDocumentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "文件名称,左前缀匹配")
    private String fileName;


    @Schema(description = "类型")
    private Long typeDictId;



    @Schema(description = "是否已嵌入")
    private Boolean isEmbedded;










}
