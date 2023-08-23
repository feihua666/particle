package com.particle.openplatform.client.openapirecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口调用记录参数 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Data
@Schema
public class OpenplatformOpenapiRecordParamQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "调用记录id")
    private Long openplatformOpenapiRecordId;











}
