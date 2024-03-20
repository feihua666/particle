package com.particle.openplatform.client.doc.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口与目录关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@Schema
public class OpenplatformDocApiDirRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;


    @Schema(description = "开放接口文档目录id")
    private Long openplatformDocDirId;









}
