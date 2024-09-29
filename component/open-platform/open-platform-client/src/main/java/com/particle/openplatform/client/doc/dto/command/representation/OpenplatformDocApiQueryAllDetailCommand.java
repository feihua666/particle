package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开放接口文档详情接口 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiQueryAllDetailCommand extends IdCommand {

    /**
     * 可以根据开放接口查询接口文档详情
     */
    @Schema(description = "开放接口id")
    private Long openplatformOpenapiId;

}