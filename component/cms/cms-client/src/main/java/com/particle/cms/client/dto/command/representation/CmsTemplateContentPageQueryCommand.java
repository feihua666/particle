package com.particle.cms.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 模板内容 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Data
@Schema
public class CmsTemplateContentPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "数据id")
    private Long dataId;


    @Schema(description = "模板类型")
    private String typeName;


    @Schema(description = "模板内容")
    private String content;









}
