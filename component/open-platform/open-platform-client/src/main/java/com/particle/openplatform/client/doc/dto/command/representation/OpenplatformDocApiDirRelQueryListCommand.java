package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口与目录关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@Schema
public class OpenplatformDocApiDirRelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;


    @Schema(description = "开放接口文档目录id")
    private Long openplatformDocDirId;









}
