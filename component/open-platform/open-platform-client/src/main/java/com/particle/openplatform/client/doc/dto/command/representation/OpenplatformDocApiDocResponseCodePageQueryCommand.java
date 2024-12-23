package com.particle.openplatform.client.doc.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import com.particle.global.light.share.mybatis.anno.OrderBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档响应码 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@OrderBy("seq")
@Data
@Schema
public class OpenplatformDocApiDocResponseCodePageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
    @Schema(description = "编码")
    private String code;

    @Like
	@Schema(description = "业务状态码，码值")
	private String codeStatus;

	@Schema(description = "http响应码,如：200、500")
	private Integer httpCode;


    @Schema(description = "是否计费")
    private Boolean isCharge;

    @Like
    @Schema(description = "字段说明")
    private String explanation;


    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;


    @Schema(description = "开放接口文档id")
    private Long openplatformDocApiDocId;

	@Schema(description = "排序,默认按该字段升序排序")
	private Integer seq;


    @Schema(description = "是否全局通用码")
    private Boolean isGlobal;




}
