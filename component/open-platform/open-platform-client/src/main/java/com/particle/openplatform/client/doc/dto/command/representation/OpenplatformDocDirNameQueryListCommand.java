package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口目录名称 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Data
@Schema
public class OpenplatformDocDirNameQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "编码，唯一")
	private String code;



    @Like
    @Schema(description = "名称")
    private String name;

}