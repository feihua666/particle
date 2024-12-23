package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiPageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "开放接口id，这里只存储接口，不存储分组")
	private Long openplatformOpenapiId;



    @Like
    @Schema(description = "编码")
    private String code;

    @Like
    @Schema(description = "名称")
    private String name;

    @Like
    @Schema(description = "简称")
    private String nameSimple;

	@Schema(description = "图标地址")
	private String logoUrl;

    @Schema(description = "排序")
    private Integer seq;

}
