package com.particle.openplatform.client.doc.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 开放接口文档模板响应码 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiDocTemplateResponseCodePageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "编码,左前缀匹配")
    private String code;


    @Schema(description = "http响应码")
    private Integer httpCode;


    @Schema(description = "是否计费")
    private Boolean isCharge;


    @Like
        @Schema(description = "字段说明,左前缀匹配")
    private String explanation;


    @Schema(description = "开放接口文档模板id")
    private Long openplatformDocApiDocTemplateId;


    @Schema(description = "是否全局通用码")
    private Boolean isGlobal;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @Schema(description = "描述")
    private String remark;









}